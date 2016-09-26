package com.shivamdev.rxbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by shivam on 26/9/16.
 */

public class FragmentTwo extends Fragment {

    private static final String TAG = FragmentTwo.class.getSimpleName();

    public static FragmentTwo newInstance() {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bOpenDialog = (Button) view.findViewById(R.id.b_open_dialog);
        bOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTwoDialog dialog = FragmentTwoDialog.newInstance();
                dialog.show(getFragmentManager(), TAG);
            }
        });
    }
}