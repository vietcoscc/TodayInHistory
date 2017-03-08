package com.example.vaio.todayinhistory.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vaio.todayinhistory.R;
import com.example.vaio.todayinhistory.activity.MainActivity;
import com.example.vaio.todayinhistory.adapter.EventRecyclerViewAdapter;
import com.example.vaio.todayinhistory.adapter.NumberPickerViewPagerAdapter;
import com.example.vaio.todayinhistory.model.CurrentTime;
import com.example.vaio.todayinhistory.model.Item;

import java.util.ArrayList;

/**
 * Created by vaio on 06/03/2017.
 */

@SuppressLint("ValidFragment")
public class ContentMainFragment extends Fragment implements View.OnClickListener, NumberPickerViewPagerAdapter.OnItemClick {

    // cen ->  mon -> year -> cen
    public static final String CENTURY = "Century";
    public static final String MONTH = "Month";
    public static final String YEAR = "Year";

    public static final int CENTURY_START_AT = 1;
    public static final int CENTURY_END_AT = 21;
    public static final int MONTH_START_AT = 1;
    public static final int MONTH_END_AT = 12;
    private RecyclerView recyclerView;
    private ImageView ivBack;
    private ImageView ivForward;
    private ViewPager viewPager;
    private TextView tvTitle;
    private ArrayList<Item> arrItem;
    private EventRecyclerViewAdapter eventRecyclerViewAdapter;
    private NumberPickerViewPagerAdapter numberPickerViewPagerAdapter;
    private String currentContent;
    private int centurySelected = 1;
    private int yearSelected = 0;
    private int monthSelected = 0;

    @SuppressLint("ValidFragment")
    public ContentMainFragment(ArrayList<Item> arrItem) {
        this.arrItem = arrItem;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_content_main, container, false);
        initViews(view);

        return view;
    }

    protected void initViews(View view) {
        try {
            currentContent = CENTURY;
            tvTitle = (TextView) view.findViewById(R.id.tvDate);
            tvTitle.setText(currentContent);
            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEvent);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            eventRecyclerViewAdapter = new EventRecyclerViewAdapter(arrItem);
            recyclerView.setAdapter(eventRecyclerViewAdapter);
            ivBack = (ImageView) view.findViewById(R.id.ivBack);
            ivForward = (ImageView) view.findViewById(R.id.ivFoward);

            ivBack.setOnClickListener(this);
            ivForward.setOnClickListener(this);

            viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            numberPickerViewPagerAdapter = new NumberPickerViewPagerAdapter(getFragmentManager(), CENTURY_START_AT, CENTURY_END_AT);
            numberPickerViewPagerAdapter.setOnItemClick(this);
            viewPager.setAdapter(numberPickerViewPagerAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initNumberPickerViewPagerAdapter(int from, int to) {
        numberPickerViewPagerAdapter =
                new NumberPickerViewPagerAdapter(getFragmentManager(), from, to);
        viewPager.setAdapter(numberPickerViewPagerAdapter);
        numberPickerViewPagerAdapter.setOnItemClick(this);
    }

    private void onImageViewBackPress() {
        switch (currentContent) {
            case CENTURY:
                initNumberPickerViewPagerAdapter(MONTH_START_AT, MONTH_END_AT);
                tvTitle.setText(MONTH);
                currentContent = MONTH;
                break;
            case YEAR:
                initNumberPickerViewPagerAdapter(CENTURY_START_AT, CENTURY_END_AT);
                tvTitle.setText(CENTURY);
                currentContent = CENTURY;

                break;
            case MONTH:
                int year_start_at = (centurySelected - 1) * 100;
                int year_end_at = centurySelected * 100;
                if (year_end_at > CurrentTime.getYear()) {
                    year_end_at = CurrentTime.getYear();
                }
                initNumberPickerViewPagerAdapter(year_start_at, year_end_at);
                tvTitle.setText(YEAR);
                currentContent = YEAR;
                break;
        }
//        Toast.makeText(getContext(), centurySelected + "-" + yearSelected + "-" + monthSelected, Toast.LENGTH_SHORT).show();
    }

    private void onImageViewForwardPress() {
        switch (currentContent) {
            case CENTURY:
                int year_start_at = (centurySelected - 1) * 100 + 1;
                int year_end_at = centurySelected * 100;
                if (year_end_at > CurrentTime.getYear()) {
                    year_end_at = CurrentTime.getYear();
                }
                initNumberPickerViewPagerAdapter(year_start_at, year_end_at);
                tvTitle.setText(YEAR);
                currentContent = YEAR;
                break;
            case YEAR:
                initNumberPickerViewPagerAdapter(MONTH_START_AT, MONTH_END_AT);
                tvTitle.setText(MONTH);
                currentContent = MONTH;
                break;
            case MONTH:
                tvTitle.setText(CENTURY);
                currentContent = CENTURY;
                initNumberPickerViewPagerAdapter(CENTURY_START_AT, CENTURY_END_AT);

                break;
        }
//        Toast.makeText(getContext(), centurySelected + "-" + yearSelected + "-" + monthSelected, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                onImageViewBackPress();
                break;
            case R.id.ivFoward:
                onImageViewForwardPress();
                break;
        }
    }

    public void notifyData() {
        if (eventRecyclerViewAdapter != null) {
            eventRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.tvNumber);
        switch (currentContent) {
            case CENTURY:
                centurySelected = Integer.valueOf(textView.getText().toString());
                yearSelected = 0;
                monthSelected = 0;
                break;
            case YEAR:
                yearSelected = centurySelected * 100 + position;
                break;
            case MONTH:
                monthSelected = Integer.valueOf(textView.getText().toString());
                break;
        }
        onImageViewForwardPress();
        MainActivity activity = (MainActivity) getActivity();
        try {
            activity.initToolbar("C " + centurySelected + " / Y " + yearSelected + " / M " + monthSelected + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Toast.makeText(getContext(), centurySelected + " / " + yearSelected + " / " + monthSelected, Toast.LENGTH_SHORT).show();
    }
}