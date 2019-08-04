package com.sunnyday.administrator.customviewpractise;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sunnyday.administrator.customviewpractise.fragments.WaveFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction transaction;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();


    }


    private void replaceFragment(Fragment fragment) {
        transaction.replace(R.id.fl_container,fragment);
        transaction.commit();
    }

    public void WaveLine(View view) {
        replaceFragment(new WaveFragment());
    }
}
