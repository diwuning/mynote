package com.h.mynote.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.greendao.greenBean.NewsCate;
import com.h.mynote.greendao.operateDao.NewsCateOperateDao;
import com.h.mynote.news.adpter.TuijianAdapter;
import com.h.mynote.news.bean.CateList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class TuijianActivity extends Activity {
    private static final String TAG = "TuijianActivity";

    @BindView(R.id.gv_tuijian)
    GridView gvTuijian;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_jump)
    TextView tvJump;
    @BindView(R.id.iv_newsCateBack)
    ImageView iv_newBack;
    List<NewsCate> newsCates;
    TuijianAdapter adapter;
    public static List<NewsCate> selCate = new ArrayList<NewsCate>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuijian);
        ButterKnife.bind(this);
//        initDate();
        List<NewsCate> localCate = NewsCateOperateDao.getAllData();
        if(localCate != null && localCate.size() != 0){
            newsCates = localCate;
            adapter = new TuijianAdapter(TuijianActivity.this,newsCates,"tuijian");
            gvTuijian.setAdapter(adapter);
        }else{
            initListData();
        }

    }

    public void initDate() {
        newsCates = new ArrayList<NewsCate>();
        String[] cateArray = {"头条", "推荐", "汽车", "娱乐", "体育", "财经", "科技", "搞笑", "精选", "奇趣", "明星", "竞技", "笑cry", "震惊", "八卦"};
        String[] valueArray = {"news_toutiao", "news_tuijian", "news_auto", "news_ent", "news_sports", "news_finance",
                "news_tech", "news_funny", "hdpic_toutiao", "hdpic_funny", "hdpic_pretty", "hdpic_story", "video_video", "video_highlights", "video_funny"};
        for (int i = 0; i < cateArray.length; i++) {
            NewsCate newsCate = new NewsCate();
//            newsCate.setId(String.valueOf(i));
            newsCate.setName(cateArray[i]);
            newsCate.setValue(valueArray[i]);
            newsCates.add(newsCate);
        }
        adapter = new TuijianAdapter(TuijianActivity.this, newsCates,"tuijian");
        gvTuijian.setAdapter(adapter);

    }

    public void initListData(){
        final List<NewsCate> newsCateList = new ArrayList<NewsCate>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://news.sina.com.cn/").get();
                    Elements divEl = doc.select("div#blk_cNav2_01 div.cNavLinks");
                    for(Element e1:divEl){
                        Log.e(TAG,"div="+e1.toString());
                        Log.e(TAG,"href="+e1.child(0).attr("href")+",title="+e1.child(0).text()+",size="+e1.children().size());
                        Elements childEls = e1.children();
                        for(Element child:childEls){
                            Log.e(TAG,"child="+child.toString());
                            if(!child.text().equals("")){
                                NewsCate newsCate = new NewsCate();
//                                newsCate.setId(child.id());
                                newsCate.setName(child.text());
                                newsCate.setValue(child.attr("href"));
                                newsCate.setIsSel("0");
                                newsCateList.add(newsCate);
                                NewsCateOperateDao.insertData(newsCate);
                            }
                        }

                    }
                    Message msg = new Message();
                    msg.what = 0;
                    msg.obj = newsCateList;
                    handler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    newsCates = (List<NewsCate>) msg.obj;
                    adapter = new TuijianAdapter(TuijianActivity.this, newsCates,"tuijian");
                    gvTuijian.setAdapter(adapter);
                    break;
            }
        }
    };

//    @OnItemClick(R.id.gv_tuijian)
//    public void cateOnItemClick(int position){
//
//    }

    @OnClick({R.id.iv_newsCateBack, R.id.tv_jump,R.id.tv_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_newsCateBack:
                finish();
                break;
            case R.id.tv_jump:
                Intent intent = new Intent(TuijianActivity.this,MyNewsActivity.class);
                CateList cateList = new CateList(newsCates);
                intent.putExtra("cate",cateList);
                startActivity(intent);
                break;
            case R.id.tv_finish:
                Intent selIntent = new Intent(TuijianActivity.this,MyNewsActivity.class);
                CateList selCates = new CateList(selCate);
                selIntent.putExtra("cate",selCates);
                startActivity(selIntent);
                break;
        }
    }
}
