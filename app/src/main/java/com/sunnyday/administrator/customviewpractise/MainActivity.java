package com.sunnyday.administrator.customviewpractise;

import android.annotation.SuppressLint;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sunnyday.administrator.customviewpractise.fragments.MiStopWatchFragment;
import com.sunnyday.administrator.customviewpractise.fragments.WaveFragment;


public class MainActivity extends AppCompatActivity {


    private FragmentManager manager;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();

    }


    public void WaveLine(View view) {
        FragmentTransaction transactionWave = manager.beginTransaction();
        transactionWave.replace(R.id.fl_container, new WaveFragment());
        transactionWave.commit();
    }

    public void XiaoMiStopWatch(View view) {
        FragmentTransaction transactionXiaomi = manager.beginTransaction();
        transactionXiaomi.replace(R.id.fl_container, new MiStopWatchFragment());
        transactionXiaomi.commit();
    }
}
