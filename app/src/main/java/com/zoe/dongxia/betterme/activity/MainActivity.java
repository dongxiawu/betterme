package com.zoe.dongxia.betterme.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zoe.dongxia.betterme.R;
import com.zoe.dongxia.betterme.fragment.TaskFragment;
import com.zoe.dongxia.betterme.fragment.TodoFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DrawerLayout mDrawerLayout;

    private ViewPager mViewPager;

    private NavigationView mNavigationView;

    private View mToDoTabView;

    private ImageView mTodoIcon;

    private TextView mTodoTitle;

    private View mTaskTabView;

    private ImageView mTaskIcon;

    private TextView mTaskTitle;

    private int mCurrentPosition = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView(){

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        }

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        mNavigationView = (NavigationView)findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        int headerCount = mNavigationView.getHeaderCount();
        if (headerCount > 0){
            for (int i=0;i<headerCount;i++){
                View header = mNavigationView.getHeaderView(i);
                if (header.getId() == R.id.nav_header){
                    View background = header.findViewById(R.id.background);
                    background.setOnClickListener(mOnNavigationHeaderClickListener);
                    View logo = header.findViewById(R.id.logo);
                    logo.setOnClickListener(mOnNavigationHeaderClickListener);
                    View userName = header.findViewById(R.id.user_name);
                    userName.setOnClickListener(mOnNavigationHeaderClickListener);
                    View signature = header.findViewById(R.id.signature);//个性签名
                    signature.setOnClickListener(mOnNavigationHeaderClickListener);
                }
            }
        }

        mViewPager = findViewById(R.id.view_pager);

        mViewPager.setAdapter(
                new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                onPageChange(position);
            }
        });

        mToDoTabView = findViewById(R.id.todo_layout);
        mTodoIcon = findViewById(R.id.todo_icon);
        mTodoTitle = findViewById(R.id.todo_title);
        mToDoTabView.setOnClickListener(mOnTabClickListener);

        mTaskTabView = findViewById(R.id.task_layout);
        mTaskIcon = findViewById(R.id.task_icon);
        mTaskTitle = findViewById(R.id.task_title);
        mTaskTabView.setOnClickListener(mOnTabClickListener);


    }

    private void initData(){
        onPageChange(mViewPager.getCurrentItem());
    }

    NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.statistic:
                    Intent intent = new Intent(MainActivity.this,StatisticActivity.class);
                    startActivity(intent);
                    break;
                case R.id.future:
                    intent = new Intent(MainActivity.this,FutureActivity.class);
                    startActivity(intent);
                    break;
                case R.id.relax:
                    intent = new Intent(MainActivity.this,RelaxActivity.class);
                    startActivity(intent);
                    break;
                case R.id.sync:
                    intent = new Intent(MainActivity.this,SyncActivity.class);
                    startActivity(intent);
                    break;
                case R.id.vip:
                    intent = new Intent(MainActivity.this,VipActivity.class);
                    startActivity(intent);
                    break;
                case R.id.settings:
                    intent = new Intent(MainActivity.this,SettingsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.about:
                    intent = new Intent(MainActivity.this,AboutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.feedback:
                    intent = new Intent(MainActivity.this,FeedbackActivity.class);
                    startActivity(intent);
                    break;
                default:break;
            }
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //处理导航栏头部点击事件
    private View.OnClickListener mOnNavigationHeaderClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.background:
                    AlertDialog.Builder backgroundBuilder =
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("要更换封面吗？").setMessage("您可以从相册选择喜欢的封面")
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    })
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                    AlertDialog backgroundDialog = backgroundBuilder.create();
                    backgroundDialog.show();
                    break;
                case R.id.signature:
                    AlertDialog.Builder signatureBuilder =
                    new AlertDialog.Builder(MainActivity.this).setView(R.layout.signature_input_view)
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog signatureDialog = signatureBuilder.create();
                    signatureDialog.show();
                    break;
                case R.id.user_name:
                case R.id.logo:
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
                default:break;
            }
        }
    };

    View.OnClickListener mOnTabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.todo_layout:
                    onPageChange(0);
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.task_layout:
                    onPageChange(1);
                    mViewPager.setCurrentItem(1);
                    break;
                    default:break;
            }
        }
    };

    private void onPageChange(int newPos){
        if (mCurrentPosition != newPos){
            mCurrentPosition = newPos;
            mTodoIcon.setImageResource(
                    mCurrentPosition==0 ? R.drawable.todo_press : R.drawable.todo_un_press );
            mTodoTitle.setTextColor(
                    mCurrentPosition==0 ? Color.GREEN : Color.BLACK );
            mTaskIcon.setImageResource(
                    mCurrentPosition==1 ? R.drawable.tasks_press : R.drawable.tasks_un_press );
            mTaskTitle.setTextColor(
                    mCurrentPosition==1 ? Color.GREEN : Color.BLACK );
        }
    }
}
