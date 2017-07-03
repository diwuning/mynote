package com.h.mynote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.h.mynote.countdown.CountDownAdapter;
import com.h.mynote.db.DBControl;
import com.h.mynote.db.RemindSet;
import com.h.mynote.db.countdown.CountDownDB;
import com.h.mynote.db.countdown.CountDownSet;
import com.h.mynote.remind.ConstantUtil;
import com.h.mynote.view.CircleMenuLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmListFragment extends Fragment {
    View mView;
    Context mContext;
    DBControl dbControl;
    String[] grouplist;
    public List<String> group_list;
//    public List<RemindSet> itemList;
    public List<RemindSet> listToday,listTomorrow,listWeek,listMonth,listMoreMonth;
    private List<List<RemindSet>> itemList_list;
    List<Integer> itemImgList;
    private List<List<Integer>> itemImgList_list;
    List<Integer> groupImgList;
    private List<List<Integer>> groupImgList_list;
    private ExpandableListView elView;
    private MyExpandableListViewAdapter adapter;
    int task_id;
    int circleHeight,windowHeight,centerHeight;//环形菜单的高度，窗口高度，环形菜单单个图标的高度
    private CircleMenuLayout cml_menu;
    LinearLayout rl_content;
    ImageView centerImg,topBottomImg,dengImg;
    RelativeLayout rl_deng;//环形菜单所在布局

    private boolean isSlide = false;//是否折叠
    Calendar myCalendar;
    int count = 0;
    boolean isClickAdd = false;
    //倒计时
    ListView lv_countdown;
    CountDownAdapter countDownAdapter;
    CountDownDB countDownDB;
    private List<CountDownSet> countDownList;

    RelativeLayout rl_top;
    RelativeLayout rl_list;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.activity_alarm_list, container, false);
        mContext = getActivity().getApplicationContext();
        openSqlite(mContext);
        rl_top = (RelativeLayout) mView.findViewById(R.id.rl_top);
        rl_list = (RelativeLayout) mView.findViewById(R.id.rl_list);
        //初始化expandedView(可展开的列表)
        initExpandedView();
        //倒计时
        initListView();

        if((countDownList == null || countDownList.size() == 0)&&(group_list == null || group_list.size() ==0)){
            rl_top.setVisibility(View.GONE);
            rl_list.setVisibility(View.GONE);
        }

        //int height = getViewHeight(elView);
        MainActivity mainActivity = (MainActivity)getActivity();
        cml_menu = (CircleMenuLayout)mainActivity.findViewById(R.id.id_menulayout);
        rl_content = (LinearLayout)mainActivity.findViewById(R.id.listContent);
        centerImg = (ImageView)mainActivity.findViewById(R.id.id_circle_menu_item_image);
        dengImg = (ImageView)mainActivity.findViewById(R.id.iv_deng);

        windowHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        //保存环形菜单的原始高度
        ViewGroup.LayoutParams params = cml_menu.getLayoutParams();
        circleHeight = params.height;
        //保存环形菜单单个图标的原始高度
        ViewGroup.LayoutParams centerParams = centerImg.getLayoutParams();
        centerHeight = centerParams.height;

        topBottomImg = (ImageView)mView.findViewById(R.id.iv_top);
        topBottomImg.setOnClickListener(viewOnClickListener);
        rl_deng = (RelativeLayout)mainActivity.findViewById(R.id.rl_circle);

        return mView;
    }

    private void initExpandedView(){
        group_list = new ArrayList<String>();
        groupImgList = new ArrayList<Integer>();
        grouplist = getResources().getStringArray(R.array.remind_item_list);

//        itemList = new ArrayList<RemindSet>();
        listToday = new ArrayList<RemindSet>();
        listTomorrow = new ArrayList<RemindSet>();
        listWeek = new ArrayList<RemindSet>();
        listMonth = new ArrayList<RemindSet>();
        listMoreMonth = new ArrayList<RemindSet>();

        itemList_list = new ArrayList<List<RemindSet>>();
        itemImgList_list = new ArrayList<List<Integer>>();
        itemImgList = new ArrayList<Integer>();

        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long curDate = System.currentTimeMillis();
        myCalendar = Calendar.getInstance();
        myCalendar.setTimeInMillis(curDate);
        myCalendar.set(Calendar.HOUR_OF_DAY, 0);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 0);
        myCalendar.set(Calendar.MILLISECOND, 0);
        //获得当天零点的时间戳
        long curTimeZero = myCalendar.getTimeInMillis();

        List<RemindSet> totalList = dbControl.queryCateData(ConstantUtil.REMIND_TASKID);
        if(totalList != null){
            for(int i=0;i<totalList.size();i++){
                RemindSet rs = totalList.get(i);
                try {
                    //将日期转换成时间戳
                    Date rsDate = simpleDateFormat.parse(rs.date+" "+rs.time+":00");
                    long days = (rsDate.getTime()-curTimeZero)/ConstantUtil.TIME_DAY;
//                    Toast.makeText(mContext,String.valueOf(days)+"=====rsDate.getTime()="+rsDate,Toast.LENGTH_SHORT).show();
                    if(days >= 0 && days < 1){
                        listToday.add(rs);
                    } else if(days >= 1 && days < 2){
                        listTomorrow.add(rs);
                    }else if(days >= 2 && days < 7){
                        listWeek.add(rs);
                    }else if(days >=7 && days < 31){
                        listMonth.add(rs);
                    }else if(days > 31){
                        listMoreMonth.add(rs);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        if(listToday.size() > 0){
            itemList_list.add(listToday);
            group_list.add(grouplist[0]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listTomorrow.size() > 0){
            itemList_list.add(listTomorrow);
            group_list.add(grouplist[1]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listWeek.size() > 0){
            itemList_list.add(listWeek);
            group_list.add(grouplist[2]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listMonth.size() > 0){
            itemList_list.add(listMonth);
            group_list.add(grouplist[3]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listMoreMonth.size() > 0){
            itemList_list.add(listMoreMonth);
            group_list.add(grouplist[4]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        elView = (ExpandableListView)mView.findViewById(R.id.expandList);
        elView.setGroupIndicator(null);
        adapter = new MyExpandableListViewAdapter(mContext);
        elView.setAdapter(adapter);

        elView.setOnItemLongClickListener(viewOnItemLongClickListener);
    }
    //初始化倒计时列表
    private void initListView(){
        lv_countdown = (ListView)mView.findViewById(R.id.lv_countdown);
        countDownList = countDownDB.queryAllData();
        countDownAdapter = new CountDownAdapter(mContext,countDownList);
        lv_countdown.setAdapter(countDownAdapter);
    }

    public void onResume(){
        super.onResume();
        MainActivity mainActivity = (MainActivity)getActivity();
        isClickAdd = mainActivity.isAdd;
        updateAllList(task_id);
        //首次进入页面时，不折叠菜单
        if(count != 0){
            //没有点击过添加时，屏幕暗了之后再亮，不折叠环形菜单，只有点击过添加才会折叠
//            if(isClickAdd){
//                isSlideCircle();
//                isClickAdd = false;
//            }

        }
        count++;
    }

    public void onDestory(){
        super.onDestroy();
        count = 0;
    }

    //环形菜单的折叠或展开
    View.OnClickListener viewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isSlideCircle();
        }
    };
    //折叠或展开环形菜单
    private void isSlideCircle(){
        if(!isSlide){
            for(int i=0;i<cml_menu.getChildCount();i++){
                cml_menu.getChildAt(i).setVisibility(View.INVISIBLE);
            }
            ViewGroup.LayoutParams params = rl_deng.getLayoutParams();
            params.height = centerHeight+40;
            rl_deng.setLayoutParams(params);
            rl_deng.requestLayout();
            dengImg.setVisibility(View.VISIBLE);

            isSlide = true;
        }else{
            //topBottomImg.setBaselineAlignBottom(true);
            ViewGroup.LayoutParams params = rl_deng.getLayoutParams();
            params.height = circleHeight;
            rl_deng.setLayoutParams(params);
            rl_deng.requestLayout();
            for(int j=0;j<cml_menu.getChildCount();j++){
                cml_menu.getChildAt(j).setVisibility(View.VISIBLE);
            }
            dengImg.setVisibility(View.INVISIBLE);
            isSlide = false;
        }
    }

    //长按删除
    private AdapterView.OnItemLongClickListener viewOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(mContext,id+"",Toast.LENGTH_SHORT).show();
            RemindSet rs = (RemindSet)parent.getAdapter().getItem(position);
            delDialog(rs.id);
            return false;
        }
    };
    //提示删除数据对话框
    private void delDialog(final int id){

        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case Dialog.BUTTON_POSITIVE:
                        RemindSet rs = dbControl.queryOneData(id);
                        int taskId = rs.taskType;
                        dbControl.deleteOneData(id);
                        updateAllList(taskId);
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示");
        builder.setMessage("是否删除提醒数据？");
        builder.setIcon(R.drawable.ic_launcher);
        builder.setPositiveButton("确定", dialogListener);
        builder.setNegativeButton("取消", dialogListener);
        builder.create().show();

    }
    //更新expandableListView数据
    public void updateAllList(int tasktype){
        List<RemindSet> rsLists = dbControl.queryAllData();

        itemList_list.clear();
        itemImgList_list.clear();
        group_list.clear();
        groupImgList.clear();
//        itemImgList.clear();
        listToday.clear();
        listTomorrow.clear();
        listWeek.clear();
        listMonth.clear();
        listMoreMonth.clear();
        //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long curDate = System.currentTimeMillis();
        myCalendar = Calendar.getInstance();
        myCalendar.setTimeInMillis(curDate);
        myCalendar.set(Calendar.HOUR_OF_DAY, 0);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 0);
        myCalendar.set(Calendar.MILLISECOND, 0);
        //获得当天零点的时间戳
        long curTimeZero = myCalendar.getTimeInMillis();

        List<RemindSet> totalList = dbControl.queryCateData(ConstantUtil.REMIND_TASKID);
        if(totalList != null){
            for(int i=0;i<totalList.size();i++){
                RemindSet rs = totalList.get(i);
                try {
                    //将时间格式转换成时间戳，时分秒必须都有。
                    Date rsDate = simpleDateFormat.parse(rs.date+" "+rs.time+":00");
                    long days = (rsDate.getTime()-curTimeZero)/ConstantUtil.TIME_DAY;
//                    Toast.makeText(mContext,String.valueOf(days)+"=====rsDate.getTime()="+rsDate,Toast.LENGTH_SHORT).show();
                    if(days >= 0 && days < 1){
                        listToday.add(rs);
                    } else if(days >= 1 && days < 2){
                        listTomorrow.add(rs);
                    }else if(days >= 2 && days < 7){
                        listWeek.add(rs);
                    }else if(days >=7 && days < 31){
                        listMonth.add(rs);
                    }else if(days > 31){
                        listMoreMonth.add(rs);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if(listToday.size() > 0){
            itemList_list.add(listToday);
            group_list.add(grouplist[0]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listTomorrow.size() > 0){
            itemList_list.add(listTomorrow);
            group_list.add(grouplist[1]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listWeek.size() > 0){
            itemList_list.add(listWeek);
            group_list.add(grouplist[2]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listMonth.size() > 0){
            itemList_list.add(listMonth);
            group_list.add(grouplist[3]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        if(listMoreMonth.size() > 0){
            itemList_list.add(listMoreMonth);
            group_list.add(grouplist[4]);
            groupImgList.add(R.drawable.haier_ic_arrow_right);
        }

        elView.setAdapter(adapter);
        for(int i=0;i<group_list.size();i++){
            elView.expandGroup(i);
        }
    }

    //获取expandableListView的实时高度
    private int getViewHeight(ExpandableListView exListView){
        ListAdapter listAdapter = exListView.getAdapter();
        int totalHeight = 0;
        //int viewHeight = exListView.getMeasuredHeight();
        for(int i =0;i<listAdapter.getCount();i++){
            View view = listAdapter.getView(i,null,exListView);
            view.measure(0,0);
            totalHeight += view.getMeasuredHeight();
        }
        totalHeight = totalHeight+(exListView.getDividerHeight()*(listAdapter.getCount()-1));
//        Toast.makeText(mContext,totalHeight+"",Toast.LENGTH_SHORT).show();
        return totalHeight;
    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter{
        private Context context;

        public MyExpandableListViewAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return group_list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            if(itemList_list.get(groupPosition) == null){
                return 0;
            }
            return itemList_list.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            if(groupPosition<group_list.size()){
                return itemList_list.get(groupPosition);
            }else
                return groupPosition;
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return itemList_list.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.groups,null);
                groupHolder = new GroupHolder();
                groupHolder.img = (ImageView)convertView.findViewById(R.id.img);
                groupHolder.txt = (TextView)convertView.findViewById(R.id.txt);
                convertView.setTag(groupHolder);
            }else {
                groupHolder = (GroupHolder)convertView.getTag();
            }

            if(!isExpanded){
                groupHolder.img.setImageResource(R.drawable.haier_ic_arrow_right);
            }else{
                groupHolder.img.setImageResource(R.drawable.haier_ic_arrow_down);
            }
//
            groupHolder.txt.setText(group_list.get(groupPosition));
            //Toast.makeText(context,groupPosition+"",Toast.LENGTH_SHORT).show();
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder childHolder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.childs,null);
                childHolder = new ChildHolder();
                childHolder.img = (ImageView)convertView.findViewById(R.id.img);
                childHolder.txt1 = (TextView)convertView.findViewById(R.id.txt);
                childHolder.txt2 = (TextView)convertView.findViewById(R.id.txt2);
                childHolder.hideTxt = (TextView)convertView.findViewById(R.id.hidetxt);
                convertView.setTag(childHolder);
            }else{
                childHolder = (ChildHolder)convertView.getTag();
            }
            String dateStr = itemList_list.get(groupPosition).get(childPosition).date+" "
                    +itemList_list.get(groupPosition).get(childPosition).time;
            childHolder.txt1.setText(dateStr);
            childHolder.txt2.setText(itemList_list.get(groupPosition).get(childPosition).msg);
//            childHolder.img.setImageResource(itemImgList_list.get(groupPosition).get(childPosition));
            childHolder.hideTxt.setText(String.valueOf(itemList_list.get(groupPosition).get(childPosition).id));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    class GroupHolder{
        ImageView img;
        TextView txt;
    }
    class ChildHolder{
        ImageView img;
        TextView txt1;
        TextView txt2;
        TextView hideTxt;
    }

    private void openSqlite(Context context){
        dbControl = new DBControl(context);
        dbControl.open();
        countDownDB = new CountDownDB(context);
        countDownDB.open();
    }
}
