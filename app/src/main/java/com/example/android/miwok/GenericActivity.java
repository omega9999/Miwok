package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public abstract class GenericActivity extends AppCompatActivity {

    protected abstract ArrayList<Word> getWords();

    protected abstract int getBackgroundColorId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        this.mContext = this;

        ArrayList<Word> words = getWords();

        // TODO https://developer.android.com/guide/topics/ui/layout/recyclerview.html

        ListView listView = findViewById(R.id.list);

        //listView.setAdapter(adapter);
        listView.setAdapter(new WordAdapter(this, words, getBackgroundColorId()));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            final Word word = words.get(position);
            releaseMediaPlayer();
            this.mMediaPlayer = MediaPlayer.create(this.mContext, word.getAudioResourceId());
            this.mMediaPlayer.start();
            this.mMediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private Context mContext;
    private MediaPlayer mMediaPlayer;

}
