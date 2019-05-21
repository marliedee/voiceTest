package com.example.voicedumy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements IntentListener {
    private static final String LOG_TAG = "AudioRecordTest";

    private Button playButton;
    private Button recordButton;
    private Button addtolistbutton;

    private MediaPlayer player;
    private MediaRecorder recorder;

    private boolean mStartPlaying = true;
    private boolean mStartRecording = true;

    private static String fileName;
    private EditText name;


    private Button viewlist;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
    String format = simpleDateFormat.format(new Date());


    Audiomemos audiomemo;
    private AudioDataBase audioDataBase;


    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.title_input);
        viewlist =findViewById(R.id.view_button);
        playButton = findViewById(R.id.play);
        recordButton = findViewById(R.id.record);
        addtolistbutton = findViewById(R.id.add_list);

        fileName = getExternalCacheDir().getAbsolutePath();

        if(name.getText() == null){
            fileName = "testString.3gp";
        }else if(name.getText() != null) {
            fileName += name.getText().toString() + format + ".3gp";
        }
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    playButton.setText("Stop playing");
                } else {
                    playButton.setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        });
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    recordButton.setText("Stop recording");
                } else {
                    recordButton.setText("Start recording");
                }

                mStartRecording = !mStartRecording;

            }

        });

        addtolistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtolist();
            }
        });

        viewlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToVoiceList();

            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionToRecordAccepted)
            finish();
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
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
            player.setDataSource(fileName);
            Log.e(MainActivity.class.getName(), "startPlaying: " + fileName );
            player.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        player.start();
    }
    private void stopPlaying() {
        player.release();
        player = null;
    }

    private void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(fileName);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        recorder.start();

    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }


    public void addtolist() {
        AudioDataBase audioDataBase = new AudioDataBase(this);
        audioDataBase.addAudio(new Audiomemos(fileName));
        Log.d("list", fileName);
    }

    @Override
    public void goToVoiceList() {
        Intent intent = new Intent(MainActivity.this, ListViewExample.class);
//        intent.putExtra("file", fileName+name.getText().toString() + format + ".3gp");
        startActivity(intent);
    }
}


