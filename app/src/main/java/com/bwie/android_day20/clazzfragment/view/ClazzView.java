package com.bwie.android_day20.clazzfragment.view;

/**
 * Created by 夏威夷丶 on 2018/10/17.
 */

public interface ClazzView<T> {

    void success(T t);

    void error(Exception e);

    void rightsuccess(Object obj);
}
