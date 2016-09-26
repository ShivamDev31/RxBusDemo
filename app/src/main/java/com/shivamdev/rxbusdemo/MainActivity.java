package com.shivamdev.rxbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    public static final String EVENT_KEY = "event_key";

    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        RecyclerView rvList = (RecyclerView) findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this);
        rvList.setAdapter(adapter);

        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        list.add("Five");
        list.add("Siz");
        list.add("Seven");
        list.add("Eight");

        adapter.setItems(list);

        adapter.getClickedItem()
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        RxBus.get().post(EVENT_KEY, s);
                        startActivity(new Intent(MainActivity.this, ActivityTwo.class));
                    }
                });
    }
}