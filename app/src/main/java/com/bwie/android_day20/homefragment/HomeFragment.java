package com.bwie.android_day20.homefragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.android_day20.R;
import com.bwie.android_day20.homefragment.adapter.BannerAdapter;
import com.bwie.android_day20.homefragment.bean.Banner;
import com.bwie.android_day20.homefragment.presenter.BannerPresenter;
import com.bwie.android_day20.homefragment.view.IView;
import com.bwie.android_day20.homefragment.view.SeekActivity;
import com.bwie.android_day20.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public class HomeFragment extends Fragment implements IView {

    private EditText edSou;
    private ViewPager vpBanner;

    private List<String> banners;
    private BannerAdapter adapter;
    private BannerPresenter presenter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 123) {
                int currentItem = vpBanner.getCurrentItem();
                if (currentItem < banners.size() - 1) {
                    currentItem++;
                } else {
                    currentItem = 0;
                }
                vpBanner.setCurrentItem(currentItem);
                sendEmptyMessageDelayed(123, 2000);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        edSou = v.findViewById(R.id.ed_sou);
        vpBanner = v.findViewById(R.id.vp_banner);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        banners = new ArrayList<>();
        adapter = new BannerAdapter(getActivity(), banners);
        vpBanner.setAdapter(adapter);

        presenter = new BannerPresenter();
        presenter.attach(this);
        presenter.getData("http://www.zhaoapi.cn/ad/getAd");

        setListener();
    }

    private void setListener() {
        edSou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SeekActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.sendEmptyMessageDelayed(123, 2000);
    }

    @Override
    public void success(Banner banner) {
        List<Banner.DataBean> data = banner.getData();
        if (data != null) {
            banners.clear();
            for (Banner.DataBean bean : data) {
                String icon = bean.getIcon();
                banners.add(StringUtils.ReplceString(icon));
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
