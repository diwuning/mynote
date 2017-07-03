package com.h.mynote.news;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.greendao.greenBean.NewsCate;
import com.h.mynote.news.adpter.NewsCateAdapter;
import com.h.mynote.news.bean.CateList;
import com.h.mynote.news.fragment.RollNewsFragment;

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

public class MyNewsActivity extends Activity {
    private static final String TAG = "MyNewsActivity";

    @BindView(R.id.iv_Back)
    ImageView ivBack;
    @BindView(R.id.gv_newsCate)
    GridView gvNewsCate;
    List<NewsCate> newsCates = new ArrayList<NewsCate>();
    NewsCateAdapter cateAdapter;//新闻分类
    Context mContext;
    @BindView(R.id.myNewsPager)
    ViewPager myNewsPager;
    @BindView(R.id.tv_bigCate)
    TextView tvBigCate;
    @BindView(R.id.tv_subCate)
    TextView tvSubCate;
    @BindView(R.id.lv_newsList)
    ListView lvNewsList;
    FragmentTransaction transaction;
    RollNewsFragment rollNewsFragment;
    @BindView(R.id.tv_bigTile)
    public TextView tvBigTile;
    @BindView(R.id.tv_jump)
    TextView tvJump;
    @BindView(R.id.ll_newsContent)
    LinearLayout llNewsContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_news);
        ButterKnife.bind(this);
        mContext = MyNewsActivity.this;
        initData();
    }

    int cateSize = 0;

    public void initData() {
        if (getIntent().getSerializableExtra("cate") != null) {
            CateList<List<NewsCate>> cateList = (CateList<List<NewsCate>>) getIntent().getSerializableExtra("cate");
            newsCates = cateList.getNewsCates();
        }
        if (newsCates != null && newsCates.size() != 0) {
            cateSize = newsCates.size();
        }
        int length = 50;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int gridviewWidth = (int) (cateSize * (length + 4) * density);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, ViewGroup.LayoutParams.FILL_PARENT);
        gvNewsCate.setLayoutParams(params);
        gvNewsCate.setHorizontalSpacing(4);
        gvNewsCate.setColumnWidth(50);
        gvNewsCate.setNumColumns(cateSize);
        cateAdapter = new NewsCateAdapter(mContext, newsCates);
        gvNewsCate.setAdapter(cateAdapter);
        //嵌套fragment
        transaction = getFragmentManager().beginTransaction();
        rollNewsFragment = new RollNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("newsTitle", newsCates.get(0));
        rollNewsFragment.setArguments(bundle);
        transaction.add(R.id.ll_newsContent, rollNewsFragment).commit();
//        initNewsData(newsCates.get(0).getValue());
    }

    public void initNewsData(final String url) {
        final String[] titleStr = {""};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(url).get();
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

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String[] titles = (String[]) msg.obj;
                    tvBigCate.setText(newsCates.get(0).getName());
                    tvSubCate.setText(titles[0]);
                    break;
            }
        }
    };

    @OnClick(R.id.iv_Back)
    public void onClick() {
        finish();
    }
}
