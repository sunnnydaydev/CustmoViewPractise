package com.sunnyday.administrator.customviewpractise.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunnyday.administrator.customviewpractise.R;

/**
 * Create by SunnyDay on 2019/08/04
 */
public class WaveFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savInstanceState) {
        return inflater.inflate(R.layout.fragment_layout_wave,container,false);
    }
}
