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
import com.hwangjr.rxbus.thread.EventThread;

/**
 * Created by shivam on 26/9/16.
 */

public class FragmentTwoDialog extends DialogFragment {

    private View contentView;
    private TextView textView;

    private String stringFromBus;

    public static FragmentTwoDialog newInstance() {
        FragmentTwoDialog fragment = new FragmentTwoDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_two_dialog, container, false);
            textView = (TextView) contentView.findViewById(R.id.tv_string_from_bus);
            RxBus.get().register(this);
        }
        return contentView;
    }

    @Subscribe(tags = {@Tag(MainActivity.EVENT_KEY)},
            thread = EventThread.MAIN_THREAD)
    public void stringFromBus(String s) {
        this.stringFromBus = s;
        if (!TextUtils.isEmpty(stringFromBus)) {
            textView.setText(stringFromBus);
        } else {
            textView.setText("No string received from bus");
        }
    }

    @Override
    public void onDestroy() {
        RxBus.get().unregister(this);
        super.onDestroy();
    }
}