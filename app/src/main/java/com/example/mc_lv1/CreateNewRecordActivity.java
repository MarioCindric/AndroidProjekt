package com.example.mc_lv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

public class CreateNewRecordActivity extends BaseActivity {
    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_record);

        SharedViewModel sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        viewPager2 = findViewById(R.id.pager);

        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch(position)
                {
                    case 0:
                        return new PersonalInfoFragment();
                    case 1:
                        return new StudentInfoFragment();
                    case 2:
                        return new SummaryFragment();
                }
                return new PersonalInfoFragment();
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });

       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
           @Override
           public void onPageSelected(int position){
               super.onPageSelected(position);
           }
       });

    }
}