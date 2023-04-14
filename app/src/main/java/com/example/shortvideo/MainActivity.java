package com.example.shortvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private PagerSnapHelper pagerSnapHelper;
    private ShortVideoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }


    private void initUi() {
        recyclerView = findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);

        adapter = new ShortVideoAdapter(this);
        recyclerView.setAdapter(adapter);
        bindUiListener();
        bindData();
    }


    private void bindUiListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    autoStopPreVideo();
                    autoPlayCurrentVideo();
                }
            }

        });

    }

    private void bindData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        adapter.addData(list);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    autoStopPreVideo();
                    autoPlayCurrentVideo();
                }
            }
        }, 300);
    }

    private void autoPlayCurrentVideo() {
        View view = pagerSnapHelper.findSnapView(manager);
        Log.i("autoPlayCurrentVideo", "View=" + view);
        if (view == null) return;
        ShortVideoAdapter.ShortVideoHolder holder = (ShortVideoAdapter.ShortVideoHolder) recyclerView.findContainingViewHolder(view);
        Log.i("autoPlayCurrentVideo", "holder=" + holder);
        if (holder == null) return;
        if (!holder.videoView.isPlaying()) {
            holder.videoView.start();
        } else {
            holder.videoView.resume();
        }
        recyclerView.setTag(view);
    }

    private void autoStopPreVideo() {
        Object object = recyclerView.getTag();
        if (object == null) return;
        boolean result = object instanceof View;
        if (!result) return;
        View preView = (View) object;
        ShortVideoAdapter.ShortVideoHolder holder = (ShortVideoAdapter.ShortVideoHolder) recyclerView.findContainingViewHolder(preView);
        if (holder == null) return;
        if (holder.videoView.isPlaying()) {
            holder.videoView.pause();
        } else {
            holder.videoView.stopPlayback();
        }
    }
}
