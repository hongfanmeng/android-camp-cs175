package com.example.recyclerviewdemo.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.TextActivity;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    @NonNull
    private List<String> mItems = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView searchItemText;
        LinearLayout searchItem;

        public ViewHolder(View view) {
            super(view);
            searchItemText = view.findViewById(R.id.search_item_text);
            searchItem = view.findViewById(R.id.search_item);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.searchItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TextActivity.class);
                intent.putExtra("text", holder.searchItemText.getText());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.searchItemText.setText(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItems(List<String> searchItemList) {
        mItems.clear();
        mItems.addAll(searchItemList);
        notifyDataSetChanged();
    }

}
