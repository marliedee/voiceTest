package com.example.voicedumy;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewExample extends AppCompatActivity {
    RecyclerView recyclerView;
    VoicesAdapter voicesAdapter;
    List<Audiomemos> audiomemosList = new ArrayList<>();

    AudioDataBase audioDataBase;

    private Button play;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        play= findViewById(R.id.play);
        name = findViewById(R.id.name);

        recyclerView = findViewById(R.id.recycler);
        voicesAdapter = new VoicesAdapter(audiomemosList);

        recyclerView.setAdapter(voicesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}






