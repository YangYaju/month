package com.bwie.android_day20.clazzfragment.model;

import com.bwie.android_day20.callback.ICallBack;
import com.bwie.android_day20.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by 夏威夷丶 on 2018/10/17.
 */

public class ClazzModel {

    public void getClazz(String url, ICallBack callBack, Type type) {
        HttpUtils.getInstance().getData(url, callBack, type);
    }
}
