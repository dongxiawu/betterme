package com.zoe.dongxia.betterme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment {
    private DrawerLayout mDrawerLayout;

    private ViewPager mViewPager;

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
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout)root.findViewById(R.id.drawer_layout);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }

//        mViewPager = root.findViewById(R.id.view_pager);
//
//        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return position + "";
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                if (position == 0){
//                    return TodoFragment.newInstance();
//                }else if (position ==1){
//                    return TaskFragment.newInstance();
//                }else {
//                    return null;
//                }
//            }
//
//            @Override
//            public int getCount() {
//                return 2;
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.more:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.more:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:break;
        }
        return super.onContextItemSelected(item);
    }
}
