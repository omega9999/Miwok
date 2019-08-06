package com.example.android.miwok;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(@NonNull final Context context, @NonNull final List<Word> objects) {
        super(context, LIST_ITEM, objects);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            // warning: attachToRoot must be false
            Log.d(TAG, String.format("LayoutInflater for position %1$s", position));
            view = LayoutInflater.from(getContext()).inflate(LIST_ITEM, parent, false);
        }
        TextView miwokWord = view.findViewById(R.id.miwok_text_view);
        TextView defaultWord = view.findViewById(R.id.default_text_view);


        final Word word = getItem(position);
        if (word != null) {
            defaultWord.setText(word.getDefaultTranslation());
            miwokWord.setText(word.getMiwokTranslation());
        }
        //view.setBackgroundColor(position % 2 == 0 ? Color.LTGRAY : Color.WHITE);

        return view;
    }

    private static final int LIST_ITEM = R.layout.list_item;

    private static final String TAG = WordAdapter.class.getSimpleName();
}
