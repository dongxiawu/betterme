package com.zoe.dongxia.betterme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment{
    private static final String TAG = "MainFragment";
    
    private DrawerLayout mDrawerLayout;

    private ViewPager mViewPager;
    
    private NavigationView mNavigationView;
    
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }


    private void initView(View root){

        Toolbar toolbar = (Toolbar)root.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(mOnToolbarMenuItemClickListener);

        if (toolbar.getNavigationIcon() != null){
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        mDrawerLayout = (DrawerLayout)root.findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView)root.findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager = root.findViewById(R.id.view_pager);

        mViewPager.setAdapter(
                new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return position + "";
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0){
                    return TodoFragment.newInstance();
                }else if (position ==1){
                    return TaskFragment.newInstance();
                }else {
                    return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }


    private Toolbar.OnMenuItemClickListener mOnToolbarMenuItemClickListener
            = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                default:break;
            }
            return true;
        }
    };

    NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.more:
                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout,SettingsFragment.newInstance()).commit();
                    break;
                default:break;
            }
            return true;
        }
    };
}
