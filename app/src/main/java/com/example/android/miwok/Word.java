package com.example.android.miwok;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

public class Word {

    public static int NO_IMAGE_ID = 0;

    public Word(@NonNull final String defaultTranslation, @NonNull  final String miwokTranslation) {
        this(defaultTranslation,miwokTranslation, NO_IMAGE_ID);
    }

    public Word(@NonNull final String defaultTranslation, @NonNull  final String miwokTranslation, final int imageResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageResourceId = imageResourceId;
    }

    @NonNull
    @CheckResult
    public String getMiwokTranslation() {
        return this.mMiwokTranslation;
    }

    @NonNull
    @CheckResult
    public Word setMiwokTranslation(@NonNull final String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
        return this;
    }

    @NonNull
    @CheckResult
    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    @NonNull
    @CheckResult
    public Word setDefaultTranslation(@NonNull final String defaultTranslation) {
        this.mDefaultTranslation = defaultTranslation;
        return this;
    }

    @CheckResult
    public int getImageResourceId() {
        return this.mImageResourceId;
    }

    @NonNull
    @CheckResult
    public Word setImageResourceId(final int imageResourceId) {
        this.mImageResourceId = imageResourceId;
        return this;
    }

    @CheckResult
    public boolean hasImage(){
        return getImageResourceId() != NO_IMAGE_ID;
    }

    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId;

    private static final String TAG = Word.class.getSimpleName();
}
