package com.example.designer2.readerappjason.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.designer2.readerappjason.R;
import com.example.designer2.readerappjason.activities.AnimeActivity;
import com.example.designer2.readerappjason.model.Anime;
import com.example.designer2.readerappjason.model.Category;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder>  {
    private Context mContext;
    private List<Category> mCateg;
    RequestOptions option;

    public VerticalAdapter(Context mContext, List<Category> mCateg) {
        this.mContext = mContext;
        this.mCateg = mCateg;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item,viewGroup,false);
        final VerticalAdapter.MyViewHolder myViewHolder = new VerticalAdapter.MyViewHolder(view);

        myViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(mContext,AnimeActivity.class);
                i.putExtra("anime_name",mCateg.get(myViewHolder.getAdapterPosition()).getName());

                i.putExtra("anime_img",mCateg.get(myViewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);

            }
        });


        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mCateg.get(i).getName());




        ///load image from internet by using glibe

        Glide.with(mContext).load(mCateg.get(i).getImage_url()).apply(option).into(myViewHolder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;



        CircleImageView thumbnail;
        LinearLayout container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            container = itemView.findViewById(R.id.container2);

            thumbnail = itemView.findViewById(R.id.image_view);

        }
    }


}
