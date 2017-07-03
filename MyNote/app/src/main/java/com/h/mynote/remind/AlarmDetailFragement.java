package com.h.mynote.remind;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.h.mynote.R;
import com.h.mynote.view.AlarmPreference;
import com.h.mynote.view.AlertRingtonePreference;
import com.h.mynote.view.PreListPreference;
import com.h.mynote.db.PreferenceSaveUtil;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by h on 2016/3/28 0028.
 */
public class AlarmDetailFragement extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private EditTextPreference titlePre;
    private AlarmPreference datePre;
    private AlarmPreference timePre;
    //private AlarmPreference prePre;
    private PreListPreference prePre;
    private PreListPreference repeatPre;
    private AlertRingtonePreference alertPre;
    private int taskType;
    Calendar myCalendar;
    int mYear,mMonth,mDay;
    int mHour,mMin,mSecond;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        super.onCreate(savedInstanceState);
        this.addPreferencesFromResource(R.xml.alarm_detail);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);
        initAlarmPreference();
        Intent intent = getActivity().getIntent();
        taskType = intent.getIntExtra("taskType",3);
        if(taskType == 0){
            this.getPreferenceScreen().removePreference(repeatPre);
        }

    }

    public void onResume(){
        super.onResume();
        //share = this.getPreferenceScreen().getSharedPreferences();
        SharedPreferences share = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        Toast.makeText(getActivity(), share.contains(ConstantUtil.DATE_KEY) + "===", Toast.LENGTH_SHORT).show();
        initData(share);
    }

    private void initAlarmPreference(){
        titlePre = (EditTextPreference)this.findPreference(ConstantUtil.TITLE_KEY);
        titlePre.setOnPreferenceChangeListener(viewOnPreferenceChangeListener);

        datePre = (AlarmPreference)this.findPreference(ConstantUtil.DATE_KEY);
        datePre.setOnPreferenceClickListener(this);

        timePre = (AlarmPreference)this.findPreference(ConstantUtil.TIME_KEY);
        timePre.setOnPreferenceClickListener(this);

        prePre = (PreListPreference)this.findPreference(ConstantUtil.PREMIND_KEY);
        prePre.setOnPreferenceChangeListener(viewOnPreferenceChangeListener);

        repeatPre = (PreListPreference)this.findPreference(ConstantUtil.SETREPEAT_KEY);
        repeatPre.setEntries(getResources().getStringArray(R.array.selector_repeat));
        repeatPre.setEntryValues(getResources().getStringArray(R.array.selector_repeat));
        repeatPre.setOnPreferenceChangeListener(viewOnPreferenceChangeListener);

        alertPre = (AlertRingtonePreference)this.findPreference(ConstantUtil.ALARM_KEY);
        alertPre.setRingtoneType(RingtoneManager.TYPE_RINGTONE);
        alertPre.setOnPreferenceChangeListener(viewOnPreferenceChangeListener);

    }

    private void initData(SharedPreferences share) {
        titlePre.setSummary(share.getString(ConstantUtil.TITLE_KEY,"请输入描述文字"));
        //日期
        myCalendar = Calendar.getInstance(Locale.CHINA);
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH)+1;
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
//        String dateStr = mYear + "/" + (mMonth+1) + "/" + mDay;
        String dateStr;
        if(mMonth<10){
            dateStr = mYear + "-" +"0"+ mMonth + "-" + mDay;
        }else{
            dateStr = mYear+"-"+mMonth+"-"+mDay;
        }
        datePre.setSummary(share.getString(ConstantUtil.DATE_KEY,dateStr));

        //时间
        mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
        mMin = myCalendar.get(Calendar.MINUTE);
        mSecond = myCalendar.get(Calendar.SECOND);
        String timeStr = mHour + ":" + mMin;
        timePre.setSummary( share.getString(ConstantUtil.TIME_KEY,timeStr));

        //提前提醒
        prePre.setEntries(getResources().getStringArray(R.array.pre_task));
        prePre.setEntryValues(getResources().getStringArray(R.array.pre_task));
        prePre.setSummary(share.getString(ConstantUtil.PREMIND_KEY, "准时提醒"));

        //重复
        repeatPre.setSummary(share.getString(ConstantUtil.SETREPEAT_KEY,"不重复"));

        //铃声
        Uri defaultUri = RingtoneManager.getActualDefaultRingtoneUri(getActivity().getApplicationContext(),RingtoneManager.TYPE_RINGTONE);
        Uri shareUri = Uri.parse(share.getString(ConstantUtil.ALARM_KEY, "默认铃声"));
        Ringtone shareRing = RingtoneManager.getRingtone(getActivity().getApplicationContext(),shareUri);
        alertPre.setSummary(shareRing.getTitle(getActivity().getApplicationContext()));

    }

    // 当Preference的值发生改变时触发该事件，true则以新值更新控件的状态，false则do noting
    private Preference.OnPreferenceChangeListener viewOnPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            Toast.makeText(getActivity(),newValue.toString()+"===="+preference.toString(),Toast.LENGTH_SHORT).show();
            if(preference.getKey().equals(ConstantUtil.PREMIND_KEY)){
                prePre.setSummary(newValue.toString());
            }else if(preference.getKey().equals(ConstantUtil.SETREPEAT_KEY)){
                repeatPre.setSummary(newValue.toString());
            }else if(preference.getKey().equals(ConstantUtil.ALARM_KEY)){
                Uri uri = Uri.parse(newValue.toString());
                Ringtone myRing = RingtoneManager.getRingtone(getActivity().getApplicationContext(),uri);
                alertPre.setSummary(myRing.getTitle(getActivity().getApplicationContext()));
                //Toast.makeText(AlarmDetailActivity.this,myRing.toString()+"==33333=="+myRing.getTitle(getApplicationContext()),Toast.LENGTH_SHORT).show();
            }else if(preference.getKey().equals(ConstantUtil.DATE_KEY)){
                Log.i("alarm", newValue.toString() + "=====date");
                datePre.setSummary(newValue.toString());
            }else if(preference.getKey().equals(ConstantUtil.TITLE_KEY)){
                Log.i("alarm",newValue.toString());
                titlePre.setSummary(newValue.toString());
            }else if(preference.getKey().equals(ConstantUtil.TIME_KEY)){
                Log.i("alarm",newValue.toString());
                timePre.setSummary(newValue.toString()+"====time");
            }
            return true;
        }
    };


    @Override
    public boolean onPreferenceClick(Preference preference) {
        if(ConstantUtil.DATE_KEY.equals(preference.getKey())){
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),viewOnDateSetListener,mYear,mMonth-1,mDay);
            datePickerDialog.show();
        }else if(ConstantUtil.TIME_KEY.equals(preference.getKey())){
            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),viewOnTimeSetListener,mHour,mMin,true);
            timePickerDialog.show();
        }
        return false;
    }

    //日期设置对话框设置监听
    private DatePickerDialog.OnDateSetListener viewOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear+1;
            mDay = dayOfMonth;
            String dateSumm ;
            if(mMonth<10){
                dateSumm = mYear+"-"+"0"+mMonth+"-"+mDay;
            }else{
                dateSumm = mYear+"-"+mMonth+"-"+mDay;
            }
            datePre.setSummary(dateSumm);
            PreferenceSaveUtil.putString(datePre, ConstantUtil.DATE_KEY, dateSumm);
        }
    };
    //时间设置对话框设置监听
    private TimePickerDialog.OnTimeSetListener viewOnTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMin = minute;
            String timeStr = mHour+":"+mMin;
            timePre.setSummary(mHour+":"+mMin);
            PreferenceSaveUtil.putString(timePre,ConstantUtil.TIME_KEY,timeStr);
        }
    };
}
