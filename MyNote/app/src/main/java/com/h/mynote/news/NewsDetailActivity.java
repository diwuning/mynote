package com.h.mynote.news;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.h.mynote.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends Activity {
    private static final String TAG = "NewsDetailActivity";

    @BindView(R.id.back_btn)
    LinearLayout backBtn;
    @BindView(R.id.news_share)
    TextView newsShare;
    @BindView(R.id.news_title)
    RelativeLayout newsTitle;
    @BindView(R.id.myNewsDetail)
    WebView myNewsDetail;
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        if(getIntent().getStringExtra("url") != null){
            path = getIntent().getStringExtra("url");
        }
        initWebView(path);
    }

    public void initWebView(String path) {
        Log.d("TopicDetailActivity",path);
        WebSettings settings = myNewsDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置可以访问文件
//        settings.setAllowFileAccess(true);
        //设置支持缩放
        settings.setBuiltInZoomControls(true);
        //修改某些机型二维码显示不出来的问题
        //打开DOM储存API
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //修改WebView页面图片大小
        settings.setUseWideViewPort(true);//让webview读取网页设置的viewport，pc版网页
        settings.setLoadWithOverviewMode(true);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        //关闭硬件加速
        myNewsDetail.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ////加载需要显示的网页
        myNewsDetail.loadUrl(path);
        //设置Web视图
        myNewsDetail.setWebViewClient(new MyWebViewClient());
        myNewsDetail.setWebChromeClient(new MyWebChromeClient());
    }

    //Web视图
    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
//			Log.e(TAG, "onLoadResource: "+url);

            if(url != null && url.contains("deleteMySubject")){
//                new Handler().postDelayed(new Runnable(){
//                    public void run() {   //execute the task
//                        RxBus.getDefault().send(new DelSubject("delSubject","1"));
//                        finish();
//                    }
//                }, 500);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG,"url="+url);
            if (url != null && (url.startsWith("http:") || url.startsWith("https:"))) {
//                if (url.contains("quanquan/index")) {//详情
//                    Intent intent = new Intent(NewsDetailActivity.this, NewMainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//                    Uri uri = Uri.parse(url);
//                    intent.putExtra("sectionId",uri.getQueryParameter("sectionId"));
//                    startActivity(intent);
//                    return true;
//                }else if (url.contains("quanquan/login")) {//登陆
//                    Intent loginIntent = new Intent(NewsDetailActivity.this, NewLoginMainActivity.class);
//                    startActivity(loginIntent);
//                    return true;
//                }
            }
            view.loadUrl(url);
            return true;
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d("clinical ", consoleMessage.message() + "from line" + consoleMessage.lineNumber() + "of"
                    + consoleMessage.sourceId());
            if(consoleMessage.message() != null && consoleMessage.message().equals("isDelete.html")){
                new Handler().postDelayed(new Runnable(){
                    public void run() {   //execute the task
                        finish();
                    }
                }, 500);
            }
            return true;
        }

    }

    @OnClick({R.id.back_btn, R.id.news_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_btn:
                finish();
                break;
            case R.id.news_share:
                break;
        }
    }
}
