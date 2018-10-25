package com.bwie.android_day20.carfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.android_day20.R;
import com.bwie.android_day20.carfragment.adapter.ShopperAdapter;
import com.bwie.android_day20.carfragment.bean.Message;
import com.bwie.android_day20.carfragment.bean.Product;
import com.bwie.android_day20.carfragment.bean.Shopper;
import com.bwie.android_day20.carfragment.presenter.CarPresenter;
import com.bwie.android_day20.carfragment.view.CarView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public class CarFragment extends Fragment implements CarView {

    private RecyclerView recyclerShopper;
    private CheckBox cbAll;
    private TextView txtAllPrice;
    private Button btnClose;

    private List<Shopper<List<Product>>> list;
    private ShopperAdapter shopperAdapter;
    private CarPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_car, container, false);
        recyclerShopper = v.findViewById(R.id.recycler_shopper);
        cbAll = v.findViewById(R.id.cb_all);
        txtAllPrice = v.findViewById(R.id.txt_all_price);
        btnClose = v.findViewById(R.id.btn_close);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        shopperAdapter = new ShopperAdapter(getActivity(), list);
        // 商家checkbox选中的监听
        shopperAdapter.setOnShopperLinsenter(new ShopperAdapter.OnShopperLinsenter() {
            @Override
            public void onSopperLinsenter(int position, boolean isCheck) {
                if (!isCheck) {
                    cbAll.setChecked(false);
                } else {
                    // 定义临时变量，判断商家的选中状况
                    boolean isAllShopperChecked = true;
                    // 循环遍历所有商家的选中状态
                    for (Shopper<List<Product>> listShopper : list) {
                        // 如果有一个商家未选中，全选按钮的值就改为未选中状态，并且结束循环
                        if (!listShopper.isChecked()) {
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbAll.setChecked(isAllShopperChecked);
                }
            }
        });
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerShopper.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerShopper.setLayoutManager(manager);
        recyclerShopper.setAdapter(shopperAdapter);

        presenter = new CarPresenter();
        presenter.attach(this);
        presenter.getCar("http://www.zhaoapi.cn/product/getCarts?uid=18911&token=15DF32CA330991CB49B1D3075083417B");
    }

    @Override
    public void success(Message<List<Shopper<List<Product>>>> data) {
        List<Shopper<List<Product>>> shoppers = data.getData();
        if (shoppers != null) {
            list.clear();
            list.addAll(shoppers);
            shopperAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.decath();
        }
    }
}
