package com.shivamdev.rxbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

/**
 * Created by shivam on 26/9/16.
 */

public class FragmentTwoDialog extends DialogFragment {

    private String stringFromBus;

    public static FragmentTwoDialog newInstance() {
        FragmentTwoDialog fragment = new FragmentTwoDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_dialog, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvText = (TextView) view.findViewById(R.id.tv_string_from_bus);
        if (!TextUtils.isEmpty(stringFromBus)) {
            tvText.setText(stringFromBus);
        } else {
            tvText.setText("No string received from bus");
        }
    }

    @Subscribe(tags = {@Tag(MainActivity.EVENT_KEY)})
    public void stringFromBus(String s) {
        this.stringFromBus = s;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(this);
    }
}