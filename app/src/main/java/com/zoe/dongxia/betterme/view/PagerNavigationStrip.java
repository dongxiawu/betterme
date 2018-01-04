package com.zoe.dongxia.betterme.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zoe.dongxia.betterme.R;

import java.util.ArrayList;
import java.util.List;

@ViewPager.DecorView
public class PagerNavigationStrip extends LinearLayout {
    private ViewPager mPager;

    private boolean mUpdatingView;

    @ColorInt
    private int mColorNormal = Color.BLACK;

    @ColorInt
    private int mColorSelected = Color.RED;

    //TODO
    private List<NavigationStripItem> mItemViewList = new ArrayList<>();

    private final PageListener mPageListener = new PageListener();

    public PagerNavigationStrip(Context context) {
        this(context, null);
    }

    public PagerNavigationStrip(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public PagerNavigationStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PagerNavigationStrip(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.PagerNavigationStrip, defStyleAttr, defStyleRes);


        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {

                default:break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        final ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException(
                    "PagerNavigationStrip must be a direct child of a ViewPager.");
        }

        final ViewPager pager = (ViewPager) parent;
        final PagerAdapter adapter = pager.getAdapter();

        pager.addOnAdapterChangeListener(mPageListener);
        pager.addOnPageChangeListener(mPageListener);
        mPager = pager;
        updateAdapter(null, adapter);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mPager != null) {
            updateAdapter(mPager.getAdapter(), null);
            mPager.removeOnAdapterChangeListener(mPageListener);
            mPager.removeOnPageChangeListener(mPageListener);
            mPager = null;
        }
    }


    void updateView(int currentItem, PagerAdapter adapter) {
        final int itemCount = adapter != null ? adapter.getCount() : 0;
        mUpdatingView = true;

        if (mItemViewList.size() != itemCount){
            for (int i=0;i<mItemViewList.size();i++){
                removeView(mItemViewList.get(i));
            }
            mItemViewList.clear();

            for (int i=0;i<itemCount;i++){
                NavigationStripItem item = new NavigationStripItem(getContext());

                item.setTitle(adapter.getPageTitle(i));
                mItemViewList.add(item);
                addView(item);
            }
        }

        for (int i = 0; i < itemCount; i++){
            if (i != currentItem){
                mItemViewList.get(i).setTitleColor(mColorNormal);
            }else {
                mItemViewList.get(i).setTitleColor(mColorSelected);
            }
        }

        mUpdatingView = false;
    }

    @Override
    public void requestLayout() {
        if (!mUpdatingView) {
            super.requestLayout();
        }
    }

    void updateAdapter(PagerAdapter oldAdapter, PagerAdapter newAdapter) {
        if (oldAdapter != null) {
            oldAdapter.unregisterDataSetObserver(mPageListener);
            if (mItemViewList.size() > 0){
                mItemViewList.clear();
                removeAllViews();
            }
        }
        if (newAdapter != null) {
            newAdapter.registerDataSetObserver(mPageListener);
        }
        if (mPager != null) {
            updateView(mPager.getCurrentItem(), newAdapter);
            requestLayout();
        }
    }

    private class PageListener extends DataSetObserver implements ViewPager.OnPageChangeListener,
            ViewPager.OnAdapterChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //nothing to do
        }

        @Override
        public void onPageSelected(int position) {
            updateView(mPager.getCurrentItem(), mPager.getAdapter());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            //nothing to do
        }

        @Override
        public void onAdapterChanged(ViewPager viewPager, PagerAdapter oldAdapter,
                                     PagerAdapter newAdapter) {
            updateAdapter(oldAdapter, newAdapter);
        }

        @Override
        public void onChanged() {
            updateView(mPager.getCurrentItem(), mPager.getAdapter());
        }
    }

    class NavigationStripItem extends FrameLayout{
        View item;
        ImageView iconImage;
        TextView titleText;
        boolean isSelected;

        NavigationStripItem(Context context){
            super(context);
//            item = LayoutInflater.from(context)
//                    .inflate(R.layout.navigation_strip_item,this);
            iconImage = item.findViewById(R.id.icon);
            titleText = item.findViewById(R.id.title);
        }

        void setTitle(CharSequence title){
            titleText.setText(title);
        }

        void setTitleColor(@ColorInt int color){
            titleText.setTextColor(color);
        }

        void setIcon(Drawable icon){
            iconImage.setImageDrawable(icon);
        }
    }
}
