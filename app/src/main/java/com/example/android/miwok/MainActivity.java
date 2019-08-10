package com.example.android.miwok;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

// TODO see https://pttrns.com/android-patterns?scid=31
// TODO see global action https://developer.android.com/guide/navigation/navigation-design-graph
// TODO see https://developer.android.com/guide/navigation
// TODO see https://www.youtube.com/watch?v=Y0Cs2MQxyIs
// TODO see tabs and ViewPager https://www.youtube.com/watch?v=zQekzaAgIlQ
// TODO see Android Development Patterns https://www.youtube.com/playlist?list=PLWz5rJ2EKKc-lJo_RGGXL2Psr8vVCTWjM
// TODO see fragment guide http://developer.android.com/guide/components/fragments.html
// TODO see fragment jetpack (build a dynamic UI with Fragments) https://developer.android.com/guide/components/fragments

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
    }




    private class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{
        SimpleFragmentPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
            this.mFragments = new ArrayList<>();
            this.mFragments.add(new NumbersFragment());
            this.mFragments.add(new ColorsFragment());
            this.mFragments.add(new FamilyFragment());
            this.mFragments.add(new PhrasesFragment());
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

        private final ArrayList<Fragment> mFragments;
    }
}
