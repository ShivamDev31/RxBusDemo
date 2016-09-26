package com.shivamdev.rxbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ActivityTwo extends AppCompatActivity {

    private static final String TAG = ActivityTwo.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        addFragment();
    }

    private void addFragment() {
        FragmentTwo fragmentTwo = FragmentTwo.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_fragment, fragmentTwo, TAG).commit();
    }
}