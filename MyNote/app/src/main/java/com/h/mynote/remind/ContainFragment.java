package com.h.mynote.remind;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.h.mynote.R;
import com.h.mynote.db.DBControl;
import com.h.mynote.db.RemindSet;
import com.h.mynote.remind.ConstantUtil;

import java.util.Calendar;
import java.util.Locale;


/**
 * Created by h on 2016/3/28 0028.
 */
public class ContainFragment extends Activity {
    ImageView btn_save,btn_close;
    DBControl dbControl;
    Context mContext;
    int taskType,rsId;
    private FragmentTransaction transaction;
    //时间
    Calendar myCalendar;
    int mYear,mMonth,mDay,mHour,mMin,mSecond;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.contain_fragment);

        getFragmentManager().beginTransaction().replace(R.id.ll_content,new AlarmDetailFragement()).commit();
        btn_save = (ImageView)findViewById(R.id.btn_save);

        openSqlite(mContext);


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemindSet rs = new RemindSet();
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                //日期
                myCalendar = Calendar.getInstance(Locale.CHINA);
                mYear = myCalendar.get(Calendar.YEAR);
                mMonth = myCalendar.get(Calendar.MONTH)+1;
                mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

                String dateStr;
                if(mMonth<10){
                    dateStr = mYear + "-" +"0"+ mMonth + "-" + mDay;
                }else{
                    dateStr = mYear+"-"+mMonth+"-"+mDay;
                }
                rs.date = sharedPreferences.getString(ConstantUtil.DATE_KEY, dateStr);

                //时间
                mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
                mMin = myCalendar.get(Calendar.MINUTE);
                mSecond = myCalendar.get(Calendar.SECOND);
                String timeStr = mHour + ":" + mMin;
                rs.time = sharedPreferences.getString(ConstantUtil.TIME_KEY, timeStr);
                rs.isPre = sharedPreferences.getString(ConstantUtil.PREMIND_KEY, "准时提醒");
                rs.isRepeat = sharedPreferences.getString(ConstantUtil.SETREPEAT_KEY, "不重复");
                rs.taskType = getIntent().getIntExtra("taskType", 3);
                rs.msg = sharedPreferences.getString(ConstantUtil.TITLE_KEY, "");
                rs.alert = sharedPreferences.getString(ConstantUtil.ALARM_KEY, "默认铃声");
                long len = dbControl.insert(rs);
                if (len == -1) {
                    Toast.makeText(ContainFragment.this, "添加失败", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });

        btn_close = (ImageView)findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void openSqlite(Context context){
        dbControl = new DBControl(context);
        dbControl.open();
    }
}
