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
        import com.example.designer2.readerappjason.activities.AnimeActivity;
        import com.example.designer2.readerappjason.model.Anime;
        import com.example.designer2.readerappjason.R;

        import java.util.List;

public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<Anime> mData;
    RequestOptions option;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData) {
        this.mContext = mContext;
        this.mData = mData;




        ///Request option for glide


        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item,viewGroup,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(mContext,AnimeActivity.class);
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("anime_nb_episode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);

            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {


        myViewHolder.tv_name.setText(mData.get(position).getName());
        myViewHolder.tv_rating.setText(mData.get(position).getRating());
        myViewHolder.tv_studio.setText(mData.get(position).getStudio());
        myViewHolder.tv_category.setText(mData.get(position).getCategorie());



        ///load image from internet by using glibe

        Glide.with(mContext).load(mData.get(position).getImage_url()).apply(option).into(myViewHolder.img_thumbnail);








    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{



        TextView tv_name;
        TextView tv_rating;
        TextView tv_studio;
        TextView tv_category;

        ImageView img_thumbnail;
        LinearLayout view_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.animation_name);
            tv_rating = itemView.findViewById(R.id.rating_id);
            tv_studio = itemView.findViewById(R.id.studio_id);
            tv_category = itemView.findViewById(R.id.categorie_id);

            img_thumbnail = itemView.findViewById(R.id.thumbnail_id);

        }
    }
}
