package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PlaceholderFragment extends Fragment {

    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        Log.d("F", "OnCreateView");
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("F", "OnActivityCreated");

        final View view = getView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        ListView listView = view.findViewById(R.id.list);
        listView.setAdapter(adapter);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                View loading = view.findViewById(R.id.loading);
                View list = view.findViewById(R.id.list);

                ObjectAnimator loading_out = ObjectAnimator.ofFloat(loading, "alpha", 1, 0);
                ObjectAnimator list_in = ObjectAnimator.ofFloat(list, "alpha", 0, 1);

                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(loading_out, list_in);
                animatorSet.start();
            }
        }, 5000);
    }
}
