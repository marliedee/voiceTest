package com.example.voicedumy;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ListViewExample extends AppCompatActivity{
    private RecyclerView recyclerView;
    private VoicesAdapter voicesAdapter;
    private List<Audiomemos> audiomemosList;
    private AudioDataBase audioDataBase;
    private MediaPlayer replay;
    private static String newFile;
    private boolean myStartPlaying = true;
    private TextView name;
    private ImageView playbuttonlist;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        playbuttonlist = findViewById(R.id.playbuttonInlist);
        name = findViewById(R.id.name);
        recyclerView = findViewById(R.id.recycler);
        audioDataBase = AudioDataBase.getInstance(getApplicationContext());
        audiomemosList = audioDataBase.audiomemosList();
        voicesAdapter = new VoicesAdapter(audiomemosList);

        recyclerView.setAdapter(voicesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}






