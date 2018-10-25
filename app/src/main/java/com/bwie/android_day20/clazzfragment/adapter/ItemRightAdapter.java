package com.bwie.android_day20.clazzfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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

public class ItemRightAdapter extends RecyclerView.Adapter<ItemRightAdapter.ViewHolder> {

    private Context context;
    private List<RightClazz.DataBean.ListBean> list;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public ItemRightAdapter(Context context, List<RightClazz.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_adapter_right, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgItemRight);
        holder.txtItemNameRight.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtItemNameRight;
        private ImageView imgItemRight;

        public ViewHolder(View itemView) {
            super(itemView);
            txtItemNameRight = itemView.findViewById(R.id.txt_item_name_right);
            imgItemRight = itemView.findViewById(R.id.img_right);
        }
    }
}
