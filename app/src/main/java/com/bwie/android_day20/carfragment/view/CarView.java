package com.bwie.android_day20.carfragment.view;

import com.bwie.android_day20.carfragment.bean.Message;
import com.bwie.android_day20.carfragment.bean.Product;
import com.bwie.android_day20.carfragment.bean.Shopper;

import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public interface CarView {

    void success(Message<List<Shopper<List<Product>>>> data);

    void error(Exception e);
}
