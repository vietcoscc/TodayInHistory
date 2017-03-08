package com.example.vaio.todayinhistory.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vaio.todayinhistory.R;
import com.example.vaio.todayinhistory.activity.MainActivity;
import com.example.vaio.todayinhistory.adapter.NumberPickerRecyclerViewAdapter;

/**
 * Created by vaio on 06/03/2017.
 */

@SuppressLint("ValidFragment")
public class NumberPickerFragment extends Fragment {
    public static final int SPAN_COUNT = 3;
    private RecyclerView recyclerView;
    private int start;
    private int end;

    @SuppressLint("ValidFragment")
    public NumberPickerFragment(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_number_picker, container, false);
        try {
            initViews(view);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    private void initViews(View view) throws Exception {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNumberPicker);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), SPAN_COUNT, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        NumberPickerRecyclerViewAdapter numberPickerRecyclerViewAdapter = new NumberPickerRecyclerViewAdapter(start, end);
        recyclerView.setAdapter(numberPickerRecyclerViewAdapter);
        numberPickerRecyclerViewAdapter.setOnItemClick(new NumberPickerRecyclerViewAdapter.OnItemClick() {
            @Override
            public void onClick(View view, int position) {
                if (onItemClick != null) {
                    onItemClick.onClick(view, position);
                }
            }
        });
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClick(View view, int position);
    }

}
