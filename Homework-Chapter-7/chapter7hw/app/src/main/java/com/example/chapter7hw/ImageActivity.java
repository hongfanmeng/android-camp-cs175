package com.example.chapter7hw;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.Transition;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ImageActivity extends AppCompatActivity {

    List<View> pages = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ViewPager viewPager = findViewById(R.id.view_pager);

        ViewAdapter adapter = new ViewAdapter();

        addImage("https://t7.baidu.com/it/u=1595072465,3644073269&fm=193&f=GIF");
        addImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ffile.digitaling.com%2FeImg%2Fuimages%2F20160523%2F1464002496441203.gif&refer=http%3A%2F%2Ffile.digitaling.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621078082&t=ae651b2f1e0edfe8ab6105b796cd4162");
        addImage("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b0d857b1a34d0000012e7e87f5eb.gif&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621078109&t=e8e3c8e5eef4e8c1b269edb9cb8bc4a0");

        adapter.setData(pages);
        viewPager.setAdapter(adapter);
    }

    private void addImage(String path) {
        ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_image_item, null);
        Glide.with(imageView)
                .load(path)
                .error(R.drawable.error)
                .into(imageView);
        pages.add(imageView);
    }
}