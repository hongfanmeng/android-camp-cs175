package com.example.recyclerviewdemo.search;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewdemo.R;

import java.util.List;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SearchLayout mSearchLayout;
    private SearchAdapter mSearchAdapter = new SearchAdapter();
    private List<String> mSearchItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchAdapter);


        for (int i = 0; i < 100; i++) {
            mSearchItemList.add("这是第 " + i + " 行");
        }
        mSearchAdapter.notifyItems(mSearchItemList);

        mSearchLayout = findViewById(R.id.search);
        mSearchLayout.setOnSearchTextChangedListener(new SearchLayout.OnSearchTextChangedListener() {
            @Override
            public void afterChanged(String text) {
                List<String> filters = new ArrayList<>();
                for (String item : mSearchItemList) {
                    if (item.contains(text)) {
                        filters.add(item);
                    }
                }
                mSearchAdapter.notifyItems(filters);
            }
        });

    }
}