package com.bwie.android_day20.carfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.android_day20.R;
import com.bwie.android_day20.carfragment.bean.Product;
import com.bwie.android_day20.utils.StringUtils;

import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> list;

    public interface OnProductClickListener {
        void onProductClick(int position, boolean isChecked);
    }

    private OnProductClickListener productClickListener;

    public void setOnProductClickListener(OnProductClickListener productClickListener) {
        this.productClickListener = productClickListener;
    }

    public ProductAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_product_car, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Product product = list.get(position);
        String images = product.getImages();
        String[] s = images.split("\\|");
        if (s.length > 0) {
            Glide.with(context).load(StringUtils.ReplceString(s[0])).into(holder.imgProduct);
        }
        holder.txtProductName.setText(list.get(position).getTitle());
        holder.txtProductPrice.setText(String.valueOf(list.get(position).getPrice()));


        holder.cbProduct.setOnCheckedChangeListener(null);
        holder.cbProduct.setChecked(product.isIschecked());
        holder.cbProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                product.setIschecked(isChecked);
                if (productClickListener != null) {
                    productClickListener.onProductClick(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbProduct;
        private TextView txtProductName;
        private TextView txtProductPrice;
        private ImageView imgProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            cbProduct = itemView.findViewById(R.id.cb_product);
            txtProductName = itemView.findViewById(R.id.txt_product_name);
            txtProductPrice = itemView.findViewById(R.id.txt_product_price);
            imgProduct = itemView.findViewById(R.id.img_product);
        }
    }
}
