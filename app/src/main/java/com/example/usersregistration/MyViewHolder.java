package com.example.usersregistration;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageview;
    ImageView editView;
    ImageView deleteView;
    TextView textView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageview = itemView.findViewById(R.id.imageview);
        editView = itemView.findViewById(R.id.editview);
        deleteView = itemView.findViewById(R.id.deleteview);
        textView = itemView.findViewById(R.id.name_view);
    }


}
