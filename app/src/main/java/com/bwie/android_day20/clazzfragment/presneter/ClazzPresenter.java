package com.bwie.android_day20.clazzfragment.presneter;

import com.bwie.android_day20.callback.ICallBack;
import com.bwie.android_day20.clazzfragment.bean.LeftClazz;
import com.bwie.android_day20.clazzfragment.bean.RightClazz;
import com.bwie.android_day20.clazzfragment.model.ClazzModel;
import com.bwie.android_day20.clazzfragment.view.ClazzView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/17.
 */

public class ClazzPresenter {

    private ClazzView cv;

    private ClazzModel model;

    public void attach(ClazzView cv) {
        this.cv = cv;
        model = new ClazzModel();
    }

    public void dettach() {
        if (cv != null) {
            cv = null;
        }
    }

    public void getClazz(String url) {
        Type type = new TypeToken<LeftClazz>() {
        }.getType();

        model.getClazz(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                LeftClazz clazz = (LeftClazz) obj;
                if (clazz != null) {
                    cv.success(clazz);
                }
            }

            @Override
            public void error(Exception e) {
                cv.error(e);
            }
        }, type);
    }

    public void getrightClazz(String url, int cid) {
        url = url + "?cid=" + cid;
        Type type = new TypeToken<RightClazz>() {
        }.getType();

        model.getClazz(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                cv.rightsuccess(obj);
            }

            @Override
            public void error(Exception e) {

            }
        }, type);
    }
}
