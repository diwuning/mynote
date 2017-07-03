package com.h.mynote.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h.mynote.R;

/**
 * Created by h on 2016/3/25 0025.
 */
public class PreListPreference extends ListPreference {
    Context mContext;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PreListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public PreListPreference(Context context,AttributeSet attrs){
        super(context,attrs);
        this.mContext = context;
    }

    protected View onCreateView(ViewGroup group){
        View view = LayoutInflater.from(mContext).inflate(R.layout.alarm_item,group,false);
        return view;
    }
}
