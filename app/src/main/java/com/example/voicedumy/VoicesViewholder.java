package com.example.voicedumy;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VoicesViewholder extends RecyclerView.ViewHolder {
        private Button play;
        private TextView name;


        public VoicesViewholder(@NonNull View itemView) {
            super(itemView);

            play=itemView.findViewById(R.id.play);
            name =itemView.findViewById(R.id.name);
        }
        public void onBind(Audiomemos audio){
            name.setText(audio.getFilename());
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });



        }
    }
