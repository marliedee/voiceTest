package com.example.voicedumy;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class VoicesViewholder extends RecyclerView.ViewHolder {
    private ImageView play;
    private TextView name;
    private MediaPlayer player;
    private String filename;
    private boolean mStartPlaying = true;

    public VoicesViewholder(@NonNull View itemView) {
        super(itemView);
        play = itemView.findViewById(R.id.playbuttonInlist);
        name = itemView.findViewById(R.id.name);

    }

     void onBind(Audiomemos audio) {
        filename = audio.getFilename();
        name.setText(filename);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    play.setImageResource(R.drawable.ic_stop_black_24dp);
                } else {
                    play.setImageResource(R.drawable.play);
                }
                mStartPlaying = !mStartPlaying;
            }
        });

    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(filename);
            player.prepare();
        } catch (IOException e) {
            Log.e("TAG", "prepare() failed");
        }
        player.start();
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }
}
