package com.android.shacan.gift.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.shacan.gift.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class WellChosenFragment extends Fragment {

    private View view;
    private Context mContext;
    @BindView(R.id.wellchosen_list)
    ExpandableListView expandableListView;

    //定义一个Map<key values>存放双层listView的数据
    private Map<String,List<String>> datas = new HashMap<>();
    //定义一个List<>链表 存放key的数据
    private List<String> groupNameDatas = new ArrayList<>();

    private MyExpandListViewAdapter myExpandListViewAdapter;

    public static WellChosenFragment newInstance(){
        WellChosenFragment fragment = new WellChosenFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_well_chose,container,false);
        ButterKnife.bind(this,view);
        //初始化ExpandableListView
        setUpExpandListView();
        return view;
    }

    /**
     * 初始化ExpandableListView
     */
    private void setUpExpandListView() {
        //1、创建数据源
        setupDatas();
        //2、创建适配器
        myExpandListViewAdapter = new MyExpandListViewAdapter();
        //3、关联适配器
        expandableListView.setAdapter(myExpandListViewAdapter);

        //设置ExpandableListView点击不收缩
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        //默认所有的Group全部展开
        for (int i = 0; i < groupNameDatas.size(); i++) {
            expandableListView.expandGroup(i);
        }

    }

    private void setupDatas() {
        for(int i = 0;i < 20 ; i++){
            String gropName = "GROUP"+i;
            groupNameDatas.add(gropName);
            //定义一个List<>链表 存放值的数据
            ArrayList<String> childListData = new ArrayList<>();
            datas.put(gropName,childListData);
            for(int j = 0;j < 10 ;j++){
                childListData.add("CHILD"+j);
                Log.i("1111111","datas"+childListData);
            }
        }

    }

    /**
     * 创建一个ExpandListVIew的适配器 继承与BaseExpandableListAdapter
     */
    class MyExpandListViewAdapter extends BaseExpandableListAdapter{

        private GroupViewHolder groupViewHolder;

        /**
         * 返回分组的数量
         * @return
         */
        @Override
        public int getGroupCount() {
            return groupNameDatas == null ? 0 :groupNameDatas.size();
        }

        /**
         * 返回每一个组中的item的个数
         * @param groupPosition
         * @return
         */
        @Override
        public int getChildrenCount(int groupPosition) {
            String key = groupNameDatas.get(groupPosition);
            List<String> list = datas.get(key);
            return list == null ? 0 : list.size();
        }

        /**
         * 获得当前位置的分组的名称
         * @param groupPosition
         * @return
         */
        @Override
        public Object getGroup(int groupPosition) {
            return groupNameDatas.get(groupPosition);
        }

        /**
         *
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return null;
        }

        /**
         *
         * @param groupPosition
         * @return
         */
        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        /**
         *
         * @param groupPosition
         * @param childPosition
         * @return
         */
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        /**
         *
         * @return
         */
        @Override
        public boolean hasStableIds() {
            return false;
        }

        /**
         * 创建分组的视图
         * @param groupPosition
         * @param isExpanded
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = convertView;
            GroupViewHolder groupViewHolder = null;
            if (view == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.group_view,null);
                groupViewHolder = new GroupViewHolder(view);
            }else {
                 groupViewHolder = (GroupViewHolder) view.getTag();
            }
            groupViewHolder.mLeftTxt.setText("2016-06-27 星期一");
            return view;
        }

        class GroupViewHolder{
            @BindView(R.id.group_left_tv)
            TextView mLeftTxt;
            @BindView(R.id.grou_right_tv)
            TextView mRightText;
            public GroupViewHolder(View view){
                view.setTag(this);
                ButterKnife.bind(this,view);
            }
        }

        /**
         * 创建分组中的Item的视图
         * @param groupPosition
         * @param childPosition
         * @param isLastChild
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = convertView;
            ChildViewHolder childViewHolder = null;
            if (view == null){
                view = LayoutInflater.from(mContext).inflate(R.layout.child_view,null);
                childViewHolder = new ChildViewHolder(view);
            }else {
                childViewHolder = (ChildViewHolder) view.getTag();
            }
            childViewHolder.mImageView.setImageResource(R.mipmap.gaolu_004);
            return view;
        }

        class ChildViewHolder{
            @BindView(R.id.child_image)
            ImageView mImageView;

            public ChildViewHolder(View view) {
                view.setTag(this);
                ButterKnife.bind(this,view);
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
