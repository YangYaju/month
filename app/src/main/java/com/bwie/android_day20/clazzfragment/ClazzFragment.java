package com.bwie.android_day20.clazzfragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.bwie.android_day20.R;
import com.bwie.android_day20.clazzfragment.adapter.LeftAdapter;
import com.bwie.android_day20.clazzfragment.adapter.RightAdapter;
import com.bwie.android_day20.clazzfragment.bean.LeftClazz;
import com.bwie.android_day20.clazzfragment.bean.RightClazz;
import com.bwie.android_day20.clazzfragment.presneter.ClazzPresenter;
import com.bwie.android_day20.clazzfragment.view.ClazzView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/12.
 */

public class ClazzFragment extends Fragment implements ClazzView<LeftClazz> {

    private RecyclerView recyclerLeft;
    private RecyclerView recyclerRight;

    private List<LeftClazz.DataBean> leftlist;
    private List<RightClazz.DataBean> rightlist;

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private ClazzPresenter presenter;

    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clazz, container, false);
        recyclerLeft = v.findViewById(R.id.recycler_left);
        recyclerRight = v.findViewById(R.id.recycler_right);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

        setListener();
    }

    private void setListener() {
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LeftClazz.DataBean dataBean = leftlist.get(position);
                sp.edit().putInt("cid", dataBean.getCid()).putString("url", dataBean.getIcon()).commit();
                showRight();
            }
        });
    }

    private void showRight() {
        int cid = sp.getInt("cid", 1);
        String url = sp.getString("url", "");
        if (url != null) {
            presenter.getrightClazz("http://www.zhaoapi.cn/product/getProductCatagory", cid);
        }
    }

    private void initData() {
        leftlist = new ArrayList<>();
        rightlist = new ArrayList<>();
        leftAdapter = new LeftAdapter(getActivity(), leftlist);
        rightAdapter = new RightAdapter(getActivity(), rightlist);

        sp = getActivity().getSharedPreferences("clazz", Context.MODE_PRIVATE);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerLeft.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerLeft.setLayoutManager(layoutManager);
        recyclerRight.setLayoutManager(manager);
        recyclerRight.setAdapter(rightAdapter);
        recyclerLeft.setAdapter(leftAdapter);

        presenter = new ClazzPresenter();
        presenter.attach(this);
        presenter.getClazz("http://www.zhaoapi.cn/product/getCatagory");
        presenter.getrightClazz("http://www.zhaoapi.cn/product/getProductCatagory", 1);
    }

    @Override
    public void success(LeftClazz clazz) {
        if (clazz != null) {
            List<LeftClazz.DataBean> data = clazz.getData();
            if (data != null) {
                leftlist.clear();
                leftlist.addAll(data);
                leftAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void error(Exception e) {

    }

    @Override
    public void rightsuccess(Object obj) {
        RightClazz rightClazz = (RightClazz) obj;
        if (rightClazz != null) {
            rightlist.clear();
            List<RightClazz.DataBean> data = rightClazz.getData();
            rightlist.addAll(data);
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettach();
        }
    }
}
