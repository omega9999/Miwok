package com.example.android.miwok;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

// TODO see https://pttrns.com/android-patterns?scid=31
// TODO see global action https://developer.android.com/guide/navigation/navigation-design-graph
// TODO see https://developer.android.com/guide/navigation
// TODO see https://www.youtube.com/watch?v=Y0Cs2MQxyIs
// TODO see tabs and ViewPager https://www.youtube.com/watch?v=zQekzaAgIlQ
// TODO see Android Development Patterns https://www.youtube.com/playlist?list=PLWz5rJ2EKKc-lJo_RGGXL2Psr8vVCTWjM
// TODO see fragment guide http://developer.android.com/guide/components/fragments.html
// TODO see fragment jetpack (build a dynamic UI with Fragments) https://developer.android.com/guide/components/fragments
// TODO see guide for tabs https://guides.codepath.com/android/google-play-style-tabs-using-tablayout#sliding-tabs-layout

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewPager viewPager = findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        final CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        final TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    private class CategoryAdapter extends FragmentPagerAdapter {
        CategoryAdapter(@NonNull final Context context, @NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            this.mContext = context;
            this.mFragments = new ArrayList<>();
            this.mFragments.add(new NumbersFragment());
            this.mFragments.add(new ColorsFragment());
            this.mFragments.add(new FamilyFragment());
            this.mFragments.add(new PhrasesFragment());
        }


        @Override
        public CharSequence getPageTitle(int position) {
            GenericFragment fragment = this.mFragments.get(position);
            return this.mContext.getString(fragment.getTitleTabId()).toUpperCase();
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return this.mFragments.get(position);
        }

        @Override
        public int getCount() {
            return this.mFragments.size();
        }

        private Context mContext;
        private final ArrayList<GenericFragment> mFragments;
    }

    private static final String TAG = MainActivity.class.getSimpleName();
}
