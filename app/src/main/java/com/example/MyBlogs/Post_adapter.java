package com.example.MyBlogs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.MyBlogs.Blogs_data.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public class Post_adapter extends RecyclerView.Adapter<Post_adapter.PostVireHolder> {
    private Context context;    private List<Item> items;

    public Post_adapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PostVireHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.postlistview,parent,false);

        return new PostVireHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostVireHolder holder, int position) {

        final Item item=items.get(position);
        holder.tittle_tv.setText(item.getTitle());


        Document document= Jsoup.parse(item.getContent());
        holder.dec_tv.setText(document.text());

        Elements element=document.select("img");
        Glide.with(context).load(element.get(0).attr("src")).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Web_View.class);
                intent.putExtra("url",item.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostVireHolder extends RecyclerView.ViewHolder {
        TextView tittle_tv,dec_tv;
        ImageView imageView;
        public PostVireHolder(@NonNull View itemView) {
            super(itemView);

            tittle_tv=itemView.findViewById(R.id.tittle_tv);
            dec_tv=itemView.findViewById(R.id.dec_tv);
            imageView=itemView.findViewById(R.id.imageview);
        }
    }
}
