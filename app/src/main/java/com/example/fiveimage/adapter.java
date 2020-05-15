package com.example.fiveimage;

import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {


    Interposition interposition;
    private final int limit = 5;

    ArrayList<String> listData2;

    public adapter ( ArrayList<String> listData2 , Interposition interposition){
        this.listData2=listData2 ;
        this.interposition=interposition;
    }


    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem =layoutInflater.inflate(R.layout.cards_layout, parent ,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        String imageuri=listData2.get(position);
        holder.imageView.setImageURI(Uri.parse(imageuri));


    }







    @Override
    public int getItemCount() {
        if(listData2.size() > limit){
            return limit;
        }
        else
        {
            return listData2.size();
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView=(ImageView)itemView.findViewById(R.id.imageView1);
            this.relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativrlyt);
            cardView=itemView.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    interposition.getposition(getAdapterPosition());
                }
            });

    }}

}
