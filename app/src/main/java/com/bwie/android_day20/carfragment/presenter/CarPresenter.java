package com.bwie.android_day20.carfragment.presenter;

import com.bwie.android_day20.callback.ICallBack;
import com.bwie.android_day20.carfragment.bean.Message;
import com.bwie.android_day20.carfragment.bean.Product;
import com.bwie.android_day20.carfragment.bean.Shopper;
import com.bwie.android_day20.carfragment.model.CarModel;
import com.bwie.android_day20.carfragment.view.CarView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public class CarPresenter {

    private CarView cv;

    private CarModel model;

    public void attach(CarView cv) {
        model = new CarModel();
        this.cv = cv;
    }

    public void getCar(String url) {
        Type type = new TypeToken<Message<List<Shopper<List<Product>>>>>() {
        }.getType();

        model.getData(url, new ICallBack() {
            @Override
            public void success(Object obj) {
                Message<List<Shopper<List<Product>>>> data = (Message<List<Shopper<List<Product>>>>) obj;
                if (data != null) {
                    cv.success(data);
                }
            }

            @Override
            public void error(Exception e) {
                cv.error(e);
            }
        }, type);
    }

    public void decath() {
        if (cv != null) {
            cv = null;
        }
    }
}
