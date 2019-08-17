package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public abstract class GenericFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.word_list, container, false);
        this.mContext = getContext();
        this.mAudioManager = (AudioManager) this.mContext.getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> words = getWords();
        // TODO https://developer.android.com/guide/topics/ui/layout/recyclerview.html
        final ListView listView = root.findViewById(R.id.list);
        listView.setAdapter(new WordAdapter(this.mContext, words, getBackgroundColorId()));
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // this listener check all row not a single piece
            final Word word = words.get(position);
            releaseMediaPlayer();
            int res = this.mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                this.mMediaPlayer = MediaPlayer.create(this.mContext, word.getAudioResourceId());
                this.mMediaPlayer.start();
                this.mMediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
            }
        });
        return root;

    }

    protected abstract ArrayList<Word> getWords();

    protected abstract int getBackgroundColorId();

    public abstract int getTitleTabId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


    private class MyOnAudioFocusChangeListener implements AudioManager.OnAudioFocusChangeListener {
        @Override
        public void onAudioFocusChange(int focusChange) {
            // TODO https://medium.com/google-developers/how-to-properly-handle-audio-interruptions-3a13540d18fa#.jkibca8ml
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            }
        }
    }


    private Context mContext;
    private MediaPlayer mMediaPlayer;
    private final MyOnAudioFocusChangeListener mOnAudioFocusChangeListener = new MyOnAudioFocusChangeListener();
    // TODO https://www.tutorialspoint.com/android/android_audiomanager
    private AudioManager mAudioManager;


}
