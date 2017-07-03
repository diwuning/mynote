package com.h.mynote.news.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.greendao.greenBean.NewsCate;
import com.h.mynote.news.MyNewsActivity;
import com.h.mynote.news.bean.CateList;
import com.h.mynote.news.bean.NewsItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RollNewsFragment extends Fragment {
    private static final String TAG = "RollNewsFragment";

    @BindView(R.id.tv_bigCate)
    TextView tvBigCate;
    @BindView(R.id.tv_subCate)
    TextView tvSubCate;
    @BindView(R.id.tv_column)
    TextView tvColumn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_contentTitle)
    LinearLayout llContentTitle;
    @BindView(R.id.lv_newsList)
    ListView lvNewsList;
    NewsCate newsCate;
    Thread contentThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_roll_news, container, false);

        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        newsCate = (NewsCate) bundle.get("newsTitle");
        Log.e(TAG,"url="+newsCate.getValue());
        initNewsData(newsCate.getValue());
        return view;
    }

    public void initNewsData(final String url) {
        final String[] titleStr = {""};
        contentThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
                    //获取栏目名称
                    Elements titles = doc.select("h1#pL_Title");
                    for (Element title : titles) {
                        Log.e(TAG, "title=" + title.toString());
//                        tvBigCate.setText(newsCates.get(0).getName());
//                        tvSubCate.setText(title.text());
                        titleStr[0] = title.text();
                    }
                    Message message = new Message();
                    message.what = 0;
                    message.obj = titleStr;
                    handler.sendMessage(message);
                    //获取栏目内容

                    Elements contentEls = doc.select("div#pL_Main div.d_tit div");
                    if(contentEls != null && contentEls.size() != 0){
                        String[] contentArr=new String[contentEls.size()];
                        for(int i=0;i<contentEls.size();i++){
                            Element contentEl = contentEls.get(i);
                            Log.e(TAG,"contentEl="+contentEl.toString());
                            contentArr[i] = contentEl.text();
                        }
                        Elements els = doc.select("div#d_list ul li");
                        List<NewsItem> newsItems = new ArrayList<NewsItem>();
                        for(Element el:els){
                            Log.e(TAG,"el="+el.toString());
                            Log.e(TAG,"value="+el.child(0).child(0).attr("href")+","+el.child(1).text());
                            NewsItem newsItem = new NewsItem();
                            newsItem.setUrl(el.child(0).child(0).attr("href"));
                            newsItem.setTitle(el.child(0).text());
                            newsItem.setTime(el.child(1).text());
                            newsItems.add(newsItem);
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = contentArr;
                        Bundle bundle = new Bundle();
                        CateList<List<NewsItem>> items = new CateList<List<NewsItem>>(newsItems);
                        bundle.putSerializable("items",items);
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
//                    Toast.makeText(getActivity(),"网络连接超时，请稍候再试",Toast.LENGTH_SHORT);
                }
            }
        });
        contentThread.start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case 0:
                    String[] titles = (String[]) msg.obj;
//                    tvBigCate.setText(newsCate.getName());
//                    tvSubCate.setText(titles[0]);
                    MyNewsActivity activity = (MyNewsActivity) getActivity();
                    activity.tvBigTile.setText(titles[0]);
                    break;
                case 1:
                    String[] contentTitle = (String[]) msg.obj;
                    tvColumn.setText(contentTitle[0]);
                    tvTitle.setText(contentTitle[1]);
                    tvTime.setText(contentTitle[2]);
                    List<NewsItem> newsItemList = (List<NewsItem>) msg.getData().getSerializable("items");

                    break;
            }
        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(contentThread != null){
            contentThread.interrupt();
        }
    }
}

