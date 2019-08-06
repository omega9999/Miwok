package com.example.android.miwok;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;

public class Word {
    public Word(@NonNull final String defaultTranslation, @NonNull  final String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
    }

    @NonNull
    @CheckResult
    public String getMiwokTranslation() {
        return this.mMiwokTranslation;
    }

    public void setMiwokTranslation(@NonNull final String miwokTranslation) {
        this.mMiwokTranslation = miwokTranslation;
    }

    @NonNull
    @CheckResult
    public String getDefaultTranslation() {
        return this.mDefaultTranslation;
    }

    public void setDefaultTranslation(@NonNull final String defaultTranslation) {
        this.mDefaultTranslation = defaultTranslation;
    }

    private String mMiwokTranslation;
    private String mDefaultTranslation;

    private static final String TAG = Word.class.getSimpleName();
}
