package com.bwie.android_day20.carfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.android_day20.R;
import com.bwie.android_day20.carfragment.bean.Product;
import com.bwie.android_day20.carfragment.bean.Shopper;

import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public class ShopperAdapter extends RecyclerView.Adapter<ShopperAdapter.ViewHolder> {
    private Context context;
    private List<Shopper<List<Product>>> list;

    public interface OnShopperLinsenter {
        void onSopperLinsenter(int position, boolean isCheck);
    }

    private OnShopperLinsenter shopperLinsenter;

    public void setOnShopperLinsenter(OnShopperLinsenter shopperLinsenter) {
        this.shopperLinsenter = shopperLinsenter;
    }

    public ShopperAdapter(Context context, List<Shopper<List<Product>>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_shopper_car, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Shopper<List<Product>> shopper = list.get(position);

        holder.txtSellerName.setText(shopper.getSellerName());

        final ProductAdapter adapter = new ProductAdapter(context, shopper.getList());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(context);
        holder.recyclerProduct.setLayoutManager(manager);
        // 商品的点击事件
        adapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                // 判断商品是否选中，如果有一条商品未选中，商家就不选中
                if (!isChecked) {
                    // 改变复选框的值
                    holder.cbShopper.setChecked(false);
                    // 改变shopper的状态值
                    shopper.setChecked(false);
                } else {
                    // 全部选中的情况下
                    // 循环遍历出所有的商品是否被选中，只要有一个商品没被选中，商家就不选中，标志位改变为false
                    boolean isAllProduct = true;
                    for (Product product : shopper.getList()) {
                        if (!product.isIschecked()) {
                            isAllProduct = false;
                            break;
                        }
                    }
                    holder.cbShopper.setChecked(isAllProduct);
                    shopper.setChecked(isAllProduct);
                }
            }
        });
        holder.recyclerProduct.setAdapter(adapter);

        // 先取消掉之前的点击变化监听
        holder.cbShopper.setOnCheckedChangeListener(null);
        // 设置好初始值的状态
        holder.cbShopper.setChecked(shopper.isChecked());
        // 设置我们自己的监听状态
        holder.cbShopper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                shopper.setChecked(isChecked);
                // 商家选中的时候，子类的所有商品所有被选中
                List<Product> products = shopper.getList();
                for (Product product : products) {
                    product.setIschecked(isChecked);
                }
                // 刷新子类的适配器
                adapter.notifyDataSetChanged();

                // 调用接口回调，当商家全部选中的时候，全选按钮改变状态
                if (shopperLinsenter != null) {
                    shopperLinsenter.onSopperLinsenter(position, isChecked);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cbShopper;
        private TextView txtSellerName;
        private RecyclerView recyclerProduct;

        public ViewHolder(View itemView) {
            super(itemView);

            cbShopper = itemView.findViewById(R.id.cb_shopper);
            txtSellerName = itemView.findViewById(R.id.txt_seller_name);
            recyclerProduct = itemView.findViewById(R.id.recycler_product);
        }
    }
}
