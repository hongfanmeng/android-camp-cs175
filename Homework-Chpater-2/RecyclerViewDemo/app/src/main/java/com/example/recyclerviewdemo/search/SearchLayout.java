package com.example.recyclerviewdemo.search;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.recyclerviewdemo.R;

public class SearchLayout extends LinearLayout {

    private static final String TAG = "SearchLayout";

    private OnSearchTextChangedListener mListener;

    public SearchLayout(Context context) {
        super(context);
        initView();
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SearchLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_search, this);

        final EditText mEditView = findViewById(R.id.edit);
        final TextView mCancel = findViewById(R.id.cancel);
        final ImageView mImageView = findViewById(R.id.image);
        final ImageView mCross = findViewById(R.id.cross);

        mCross.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditView.setText("");
            }
        });

        mImageView.setImageResource(R.drawable.icon_search);

        mEditView.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mListener != null) {
                    Log.d(TAG, "Text:" + s.toString());
                    mListener.afterChanged(s.toString());
                    if (s.toString().equals("")) mCross.setVisibility(INVISIBLE);
                    else mCross.setVisibility(VISIBLE);
                }
            }
        });

        mCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });

    }

    public void setOnSearchTextChangedListener(OnSearchTextChangedListener listener) {
        mListener = listener;
    }

    public interface OnSearchTextChangedListener {
        void afterChanged(String text);
    }

}
