package com.example.city_demo.xxx;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.city_demo.City;
import com.example.city_demo.R;

import java.util.List;

public class XXXAdapter extends BaseMultiAdapter {

    public XXXAdapter(Context context) {
        super(context);
    }

    private static final String TAG = "XXXAdapter";
    @Override
    protected void attchDataByViewHolder(BaseViewHolder holder, Integer type, List<Object> value, int position,int offset) {

        if (type == 0){


            TextView viewById = (TextView) holder.getViewById(R.id.city);
            Log.d(TAG, "attchDataByViewHolder: position = "+position+"   offset="+offset);
            City city = (City) value.get(position-offset);
            viewById.setText(city.getName());
        }else{

            TextView viewById1 = (TextView) holder.getViewById(R.id.one);
            TextView viewById2 = (TextView) holder.getViewById(R.id.two);
            Log.d(TAG, "attchDataByViewHolder: position = "+position+"   offset="+offset);
            City city = (City) value.get(position-offset);
            viewById1.setText(city.getName());
            viewById2.setText(city.getPinyin());
        }

    }
}
