package com.bwie.android_day20.clazzfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android_day20.R;
import com.bwie.android_day20.clazzfragment.bean.RightClazz;

import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/17.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

    private Context context;
    private List<RightClazz.DataBean> list;

    public RightAdapter(Context context, List<RightClazz.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_data_right, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<RightClazz.DataBean.ListBean> beans = this.list.get(position).getList();
        if (beans != null) {
            ItemRightAdapter adapter = new ItemRightAdapter(context, beans);
            holder.txtNameRight.setText(list.get(position).getName());
            RecyclerView.LayoutManager manager = new GridLayoutManager(context, 3);
            holder.recyclerViewRight.setLayoutManager(manager);
            holder.recyclerViewRight.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {

        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerViewRight;
        private TextView txtNameRight;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerViewRight = itemView.findViewById(R.id.recyclerview_fight);
            txtNameRight = itemView.findViewById(R.id.txt_name_right);
        }
    }
}
