package com.example.newsapplication.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.example.newsapplication.Helper.Constants;
import com.example.newsapplication.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {


    int selectedTabPosition=-1;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(mLayoutManager);
        viewPager.setAdapter(new PagerAdapter(getFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //selectedTabPosition=tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"Entertainment", "Business", "Health", "Science","Sports", "Technology"};
        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return DashboardFragment.newInstance(Constants.CATEGORY_ENTERTAINMENT);
                case 1:
                    return DashboardFragment.newInstance(Constants.CATEGORY_BUSINESS);
                case 2:
                    return DashboardFragment.newInstance(Constants.CATEGORY_HEALTH);
                case 3:
                    return DashboardFragment.newInstance(Constants.CATEGORY_SCIENCE);
                case 4:
                    return DashboardFragment.newInstance(Constants.CATEGORY_SPORTS);
                case 5:
                    return DashboardFragment.newInstance(Constants.CATEGORY_TECHNOLOGY);
                default:
                    return DashboardFragment.newInstance(Constants.CATEGORY_ENTERTAINMENT);
            }
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}


