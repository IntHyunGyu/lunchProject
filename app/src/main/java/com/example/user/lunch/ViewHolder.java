package com.example.user.lunch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class ViewHolder extends RecyclerView.ViewHolder {
    public TextView menu;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        menu = itemView.findViewById(R.id.menu);
    }
}
