package com.flowerworld.items.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flowerworld.MainActivity;
import com.flowerworld.R;
import com.flowerworld.items.NewsItem;

import java.util.ArrayList;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.NewsItemHolder> {
    private ArrayList<NewsItem> newsItems;
    private ViewPager2 viewPager2;

    public NewsItemAdapter(ArrayList<NewsItem> newsItems, ViewPager2 viewPager2) {
        this.newsItems = newsItems;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public NewsItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new NewsItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsItemHolder holder, final int position) {
        holder.setImage(newsItems.get(position));
        holder.setTitle(newsItems.get(position));
        holder.newImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)holder.newImage.getContext())
                        .getApp()
                        .getRouter()
                        .addFragment("gridFragment",newsItems.get(position).getIds());
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    class NewsItemHolder extends RecyclerView.ViewHolder{
        TextView newsTitleText;
        SimpleDraweeView newImage;
        public NewsItemHolder(@NonNull View itemView) {
            super(itemView);
            newsTitleText=itemView.findViewById(R.id.newsTitleText);
            newImage=itemView.findViewById(R.id.newsImage);
        }
        void setImage(NewsItem newsItem){
            String uri = newsItem.getUrlImage();
            newImage.setImageURI(Uri.parse(uri));
        }
        void setTitle(NewsItem newsItem){
            newsTitleText.setText(newsItem.getTitleNews());
        }
    }
}
