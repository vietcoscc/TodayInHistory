package com.example.vaio.todayinhistory.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaio.todayinhistory.R;
import com.example.vaio.todayinhistory.model.Item;

import java.util.ArrayList;

/**
 * Created by vaio on 06/03/2017.
 */

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Item> arrItem;

    public EventRecyclerViewAdapter(ArrayList<Item> arrItem) {
        this.arrItem = arrItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_recycler_view_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = arrItem.get(position);
        holder.tvDate.setText(item.getDate());
        holder.tvInfo.setText(item.getInfo());
        holder.tvType.setText(item.getType());
        if (position >= 0) {
            if (onCompleteLoading != null) {
                onCompleteLoading.onComplete();
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvInfo;
        TextView tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvInfo = (TextView) itemView.findViewById(R.id.tvInfo);
            tvType = (TextView) itemView.findViewById(R.id.tvType);
        }
    }

    public void setOnCompleteLoading(OnCompleteLoading onCompleteLoading) {
        this.onCompleteLoading = onCompleteLoading;
    }

    private OnCompleteLoading onCompleteLoading;

    public interface OnCompleteLoading {
        void onComplete();
    }
}
