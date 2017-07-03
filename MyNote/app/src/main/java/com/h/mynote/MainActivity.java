package com.h.mynote;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.h.mynote.countdown.CountDownDetailActivity;
import com.h.mynote.music.MpsPlayerActivity;
import com.h.mynote.news.TuijianActivity;
import com.h.mynote.recommend.RecommendActivity;
import com.h.mynote.remind.ContainFragment;
import com.h.mynote.video.SurfaceActivity;
import com.h.mynote.view.CircleMenuLayout;

public class MainActivity extends AppCompatActivity {
    private CircleMenuLayout mCircleMenuLayout;
    private String[] mItemTexts = new String[]{
            "新闻","我的视频","我的音乐","我的图片","随笔","提醒","记帐","倒计时"
    };

    private int[] mItemImgs = new int[]{
            R.drawable.icon_import,R.drawable.icon_cust,R.drawable.ring_title12_1,R.drawable.note,
            R.drawable.bianjitie,R.drawable.icon_countdown,R.drawable.icon_payment,R.drawable.countdown
    };

    int taskType=0;
    private FragmentTransaction tansation;
    int taskId;
    boolean isAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //开启推送服务
//        PushAgent mPushAgeng = PushAgent

        //将提醒列表包含在主页
        //getFragmentManager().beginTransaction().replace(R.id.listContent,new AlarmListFragment()).commit();
        tansation = getFragmentManager().beginTransaction();
        AlarmListFragment listFrage = new AlarmListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("taskType",taskType);
//        Toast.makeText(getApplicationContext(),taskType+"",Toast.LENGTH_SHORT).show();
        listFrage.setArguments(bundle);
        tansation.replace(R.id.listContent,listFrage).commit();

        mCircleMenuLayout = (CircleMenuLayout)findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(MainActivity.this, mItemTexts[pos] + "," + pos, Toast.LENGTH_SHORT).show();
                switch (pos) {
                    case 0://阅读
//                        //Intent importIntent = new Intent(MainActivity.this, AlarmDetailActivity.class);
                        Intent importIntent = new Intent(MainActivity.this, TuijianActivity.class);
                        importIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        importIntent.putExtra("taskType", 0);
                        taskType = 0;
                        startActivity(importIntent);
                        break;
                    case 1://我的视频
                        Intent payIntent = new Intent(MainActivity.this, SurfaceActivity.class);
                        payIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        payIntent.putExtra("taskType", 1);
                        taskType = 1;
                        startActivity(payIntent);
                        break;
                    case 2://我的音乐
                        Intent musicIntent = new Intent(MainActivity.this, MpsPlayerActivity.class);
                        musicIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        musicIntent.putExtra("taskType", 2);
                        taskType = 2;
                        startActivity(musicIntent);
                        break;
                    case 3://我的图片
//                        Intent customIntent = new Intent(MainActivity.this,RemindImportActivity.class);
//                        //Intent customIntent = new Intent(MainActivity.this, RemindImportActivity.class);
//                        customIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        customIntent.putExtra("taskType", 1);
//                        startActivity(customIntent);
//                        taskType = 3;
                        break;
                    case 4: //随笔
                        taskType = 4;
                        break;
                    case 5://提醒
                        Intent busIntent = new Intent(MainActivity.this, ContainFragment.class);
                        busIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        busIntent.putExtra("taskType", 5);
                        taskType = 5;
                        isAdd = true;
                        startActivity(busIntent);
                        break;
                    case 6://记帐
//                        Intent intent = new Intent(MainActivity.this, PreferenceDemoActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        taskType = 6;
//                        startActivity(intent);
                        break;
                    case 7://倒计时
                        Intent intent = new Intent(MainActivity.this, CountDownDetailActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Bundle bundle1 = new Bundle();
                        bundle1.putLong("id",-1);
                        intent.putExtra("id",bundle1);
                        taskType = 7;
                        startActivity(intent);
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void itemCenterClick(View view) {
                //Toast.makeText(MainActivity.this,"you can do something just like ccb ",Toast.LENGTH_SHORT).show();
                Intent centerIntent = new Intent(MainActivity.this,RecommendActivity.class);
                centerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(centerIntent);
            }
        });

    }


    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
//        isAdd = false;
    }
    public void onResume(){
        super.onResume();
        taskId = taskType;

//        Toast.makeText(getApplicationContext(),taskType+"=====onResume",Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
