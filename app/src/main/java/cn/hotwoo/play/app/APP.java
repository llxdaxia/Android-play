package cn.hotwoo.play.app;

import android.app.Application;

import alien95.cn.util.Utils;
import cn.hotwoo.play.BuildConfig;

/**
 * Created by linlongxin on 2016/2/28.
 */
public class App extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.initialize(this);
        if(BuildConfig.DEBUG){
            Utils.setDebug(true,"Debug");
        }
    }
}
