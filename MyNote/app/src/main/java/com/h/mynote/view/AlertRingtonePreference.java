package com.h.mynote.view;

import android.content.Context;
import android.preference.RingtonePreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h.mynote.R;

/**
 * Created by h on 2016/3/26 0026.
 */
public class AlertRingtonePreference extends RingtonePreference {
    Context mContext;
    public AlertRingtonePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public AlertRingtonePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    protected View onCreateView(ViewGroup group){
        View view = LayoutInflater.from(mContext).inflate(R.layout.alarm_item,group,false);
        return view;
    }


}
