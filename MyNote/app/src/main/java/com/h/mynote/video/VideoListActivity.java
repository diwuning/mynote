package com.h.mynote.video;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.h.mynote.R;
import com.h.mynote.common.MediaUtil;

import java.util.List;

public class VideoListActivity extends AppCompatActivity {
    private TextView tv_cate;
    private GridView gv_video;
    private List<VideoInfo> videoList;
    Context mContext;
    VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        initView();
        initData();
    }

    private void initView(){
        tv_cate = (TextView)findViewById(R.id.tv_cate);
        gv_video = (GridView)findViewById(R.id.gv_videoList);
    }

    private void initData(){
        mContext = getApplicationContext();
        videoList = MediaUtil.getVideoList(mContext);


    }

    public class VideoAdapter extends BaseAdapter{
        private List<VideoInfo> videoInfos;
        Context mmContext;

        public VideoAdapter(Context _context,List<VideoInfo> list1){
            this.mmContext = _context;
            this.videoInfos = list1;
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
