package com.bwie.android_day20.homefragment.presenter;

import com.bwie.android_day20.callback.ICallBack;
import com.bwie.android_day20.homefragment.bean.Banner;
import com.bwie.android_day20.homefragment.model.BannerModel;
import com.bwie.android_day20.homefragment.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public class BannerPresenter {

    private IView iv;

    private BannerModel model;

    public void attach(IView iv) {
        model = new BannerModel();
        this.iv = iv;
    }

    public void getData(String url) {
        Type type = new TypeToken<Banner>() {
        }.getType();
        model.getData(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                Banner banner = (Banner) obj;
                if (banner != null) {
                    iv.success(banner);
                }
            }

            @Override
            public void error(Exception e) {
                iv.error(e);
            }
        }, type);
    }

    public void decath() {
        if (iv != null) {
            iv = null;
        }
    }
}
