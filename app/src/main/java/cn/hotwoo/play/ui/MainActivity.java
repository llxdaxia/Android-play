package cn.hotwoo.play.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import alien95.cn.util.Utils;
import cn.hotwoo.play.R;

/**
 * Created by llxal on 2015/12/4.
 */
public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,new MainFragment());
        fragmentTransaction.commit();
    }

    class MyRunable implements Runnable {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        double num;

        @Override
        public void run() {
            num = Math.random() * 100;
            threadLocal.set(num + "");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Utils.Log("num:" + threadLocal.get());
        }
    }
}
