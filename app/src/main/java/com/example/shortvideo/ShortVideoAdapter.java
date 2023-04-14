package com.example.shortvideo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author : mac
 * date   : 2023/4/14
 * e-mail : taolei@51cashbox.com
 */
public class ShortVideoAdapter extends RecyclerView.Adapter<ShortVideoAdapter.ShortVideoHolder> {

    private Context mContext;
    private LayoutInflater inflater;
    private List<String> data;


    public ShortVideoAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        data = new ArrayList<>();
    }

    public void addData(List<String> list) {
        this.data.addAll(list);
        notifyItemRangeChanged(0, getItemCount());
    }

    @NonNull
    @Override
    public ShortVideoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.item_short_video, viewGroup, false);

        return new ShortVideoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortVideoHolder shortVideoHolder, int i) {
        String item = data.get(i);
        shortVideoHolder.videoView.setVideoURI(Uri.parse(item));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ShortVideoHolder extends RecyclerView.ViewHolder {

        final VideoView videoView;

        public ShortVideoHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
        }
    }
}
