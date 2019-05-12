package com.example.voicedumy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class VoicesAdapter extends RecyclerView.Adapter<VoicesViewholder>{

    List<Audiomemos> audioList;

    public VoicesAdapter(List<Audiomemos> audioList) {
        this.audioList = audioList;
    }

    @NonNull
    @Override
    public VoicesViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);
        return new VoicesViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VoicesViewholder carbViewholder, int i) {
        carbViewholder.onBind(audioList.get(i));
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }
}

