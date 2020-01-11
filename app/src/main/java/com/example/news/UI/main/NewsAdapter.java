package com.example.news.UI.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.pojo.Article;
import com.squareup.picasso.Picasso;

import java.util.List;
// PICASSO FOR DISPLAY image

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    List<Article> list ;


    public NewsAdapter(List<Article> list) {
        this.list=list;

    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_layout,parent,false);

        NewsHolder hold=new NewsHolder(view);
        return hold;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        // بربط الداتا باليو اي بتاعي هنا
        Article current= list.get(position);

        holder.textView.setText(current.getTitle());
        Picasso.get().load(current.getUrlToImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }




      ///////////////////////////NewsHolder////////////////////////////////////////////////
    class NewsHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView img;


        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textView);
            img=itemView.findViewById(R.id.imageView);
        }
    }

}