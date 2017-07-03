package com.h.mynote.view;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h.mynote.R;

/**
 * Created by h on 2016/3/24 0024.
 */
public class AlarmPreference extends Preference {
    Context mContext;

    public AlarmPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }
    public AlarmPreference(Context context,AttributeSet attrs){
        super(context,attrs);
        mContext = context;
    }

    protected View onCreateView(ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.alarm_item,parent,false);
        return view;
    }

}
