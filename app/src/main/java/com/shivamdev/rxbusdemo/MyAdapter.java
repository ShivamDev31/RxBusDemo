package com.shivamdev.rxbusdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by shivam on 26/9/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private PublishSubject<String> mClickSubject;
    private Context mContext;
    private List<String> myItems;

    public MyAdapter(Context context) {
        this.mContext = context;
        myItems = new ArrayList<>();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tvItem.setText(myItems.get(position));
    }

    @Override
    public int getItemCount() {
        return myItems.size();
    }

    public void setItems(List<String> items) {
        myItems.clear();
        myItems.addAll(items);
        notifyDataSetChanged();
    }

    public Observable<String> getClickedItem() {
        return mClickSubject;
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        MyHolder(View view) {
            super(view);
            tvItem = (TextView) view.findViewById(R.id.tv_item_text);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickSubject.onNext(myItems.get(getLayoutPosition()));
                }
            });
        }
    }
}
