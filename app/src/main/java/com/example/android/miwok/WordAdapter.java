package com.example.android.miwok;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
        if (view == null) {
            // warning: attachToRoot must be false
            Log.d(TAG, String.format("LayoutInflater for position %1$s", position));
            view = LayoutInflater.from(getContext()).inflate(LIST_ITEM, parent, false);
        }
        else{
            clearView(view);
        }
        TextView miwokWord = view.findViewById(R.id.miwok_text_view);
        TextView defaultWord = view.findViewById(R.id.default_text_view);

        final Word word = getItem(position);
        if (word != null) {
            defaultWord.setText(word.getDefaultTranslation());
            miwokWord.setText(word.getMiwokTranslation());
        }

        /*
        * put a dark rectangle behind the view and set the view's opacity. This saves painting the rectangle when the view is 100% opaque.
        * imageView.setColorFilter(Color.rgb(123, 123, 123), android.graphics.PorterDuff.Mode.MULTIPLY);
        * view.getBackground().setColorFilter(color, PorterDuff.Mode.DARKEN);
        */
        if (Boolean.FALSE) {// colori delle righe con varianti chiare/scure
            int color = position % 2 == 0 ? Color.rgb(255, 0, 0) : Color.rgb(0, 255, 0);
            view.setBackgroundColor(color);
            defaultWord.setBackgroundColor(color);
            miwokWord.setBackgroundColor(color);
            defaultWord.getBackground().setColorFilter(Color.argb(20, 0, 0, 0), PorterDuff.Mode.DARKEN);
            miwokWord.getBackground().setColorFilter(Color.argb(20, 0, 0, 0), PorterDuff.Mode.LIGHTEN);
        }

        return view;
    }

    private void clearView(@NonNull final View view){
        TextView miwokWord = view.findViewById(R.id.miwok_text_view);
        TextView defaultWord = view.findViewById(R.id.default_text_view);

        miwokWord.setText(null);
        defaultWord.setText(null);
    }

    private static final int LIST_ITEM = R.layout.list_item;

    private static final String TAG = WordAdapter.class.getSimpleName();
}
