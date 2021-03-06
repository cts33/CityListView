package com.example.city_demo_2.flow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.city_demo_2.R;

import java.util.ArrayList;
import java.util.List;


public abstract class CommonFlowAdapter<T> extends FlowBaseAdapter<T> {
    protected List<T> mDatas = new ArrayList<>();
    private int mLayoutId = R.layout.flow_item;
    private Context mContext;
    private FlowHolder holder;
    private int strokeWidth = 1;
    private float radius = 15;

    public CommonFlowAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas.clear();
        this.mDatas.addAll(datas);


    }

    public CommonFlowAdapter(Context context) {
        this.mContext = context;

    }

    public void addItems(List<T> datas) {
        this.mDatas.addAll(datas);
        notifyDataSetChanged();

    }

    private GradientDrawable getDrawable() {
        GradientDrawable   radiusBg = new GradientDrawable();
        //设置Shape类型
        radiusBg.setShape(GradientDrawable.RECTANGLE);
        //设置填充颜色
//        radiusBg.setColor(Color.WHITE);
        //设置线条粗心和颜色,px
        radiusBg.setStroke(strokeWidth, Color.GRAY);
        //设置圆角角度,如果每个角度都一样,则使用此方法
        radiusBg.setCornerRadius(radius);

        radiusBg.setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return radiusBg;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public View getView(FlowingLayout flowingLayout, int i, T item) {
        holder = new FlowHolder(mContext, flowingLayout, mLayoutId);


        getTextView().setBackground(getDrawable());
        getTextView().setDuplicateParentStateEnabled(true);

        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iOnItemClickListener!=null){
                    iOnItemClickListener.onClickItem(item);
                }
            }
        });

        convert(holder, mDatas.get(i), i);
        return holder.getConvertView();
    }

    public TextView getTextView() {
        return holder.getConvertView().findViewById(R.id.title);
    }


    public abstract void convert(FlowHolder holder, T item, int position);


    private IOnItemClickListener iOnItemClickListener;

    public void setIOnItemClickListener(IOnItemClickListener iOnItemClickListener) {
        this.iOnItemClickListener = iOnItemClickListener;
    }

    public interface IOnItemClickListener<T>{
        void onClickItem(T t);
    }

    public class FlowHolder {
        private SparseArray<View> mViews;
        private View mConvertView;

        int default_tv_id = R.id.title;

        public FlowHolder(Context context, ViewGroup parent, int layoutId) {
            this.mViews = new SparseArray<View>();
            mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }

        public FlowHolder setText(CharSequence text) {
            TextView tv = getView(default_tv_id);
            tv.setText(text);
            return this;
        }

        public <V extends View> V getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (V) view;
        }


        public FlowHolder setOnClickListener(int viewId,
                                             View.OnClickListener clickListener) {
            getView(viewId).setOnClickListener(clickListener);
            return this;
        }

        public FlowHolder setItemClick(View.OnClickListener clickListener) {
            mConvertView.setOnClickListener(clickListener);
            return this;
        }

        public View getConvertView() {
            return mConvertView;
        }
    }

}