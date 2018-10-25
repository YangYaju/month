package com.bwie.android_day20.homefragment.view;

import com.bwie.android_day20.homefragment.bean.Banner;

/**
 * Created by 夏威夷丶 on 2018/10/25.
 */

public interface IView {

    void success(Banner banner);

    void error(Exception e);
}
