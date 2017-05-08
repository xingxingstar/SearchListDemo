package com.example.zhuwojia.searchlistdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhuwojia.searchlistdemo.R;
import com.example.zhuwojia.searchlistdemo.inter.StringCallBack;

import java.util.List;

/**
 * author：shixinxin on 2017/5/8
 * version：v1.0
 */

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> list;
    private StringCallBack callBack;

    public SearchListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setCallBack(StringCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_search_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (list != null) {
            holder.tv_city.setText(list.get(position));
            holder.tv_city.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.typeChangeListener(list.get(position));
                }
            });
        } else {
            holder.tv_city.setText("抱歉,未找到相关位置，可尝试修改后重试");
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_city;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_city = (TextView) itemView.findViewById(R.id.tv_city);
        }

    }
}
