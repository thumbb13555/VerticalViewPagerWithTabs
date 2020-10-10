package com.jetec.verticalviewpagerwithtabs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {

    TabsAdapter tabAdapter;
    ViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**製作陣列*/
        String[] strings = {"香蕉","蘋果","鳳梨","橘子","蓮霧"};
        /**設置ViewPager*/
        final VerticalViewPager viewPager = findViewById(R.id.pager);
        /**綁定ViewPager給ViewPagerAdapter*/
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),strings);
        viewPager.setAdapter(pagerAdapter);
        /**監聽ViewPager被滑動時的事件處理*/
        viewPager.addOnPageChangeListener(onPageChangeListener);

        /**設置RecyclerView*/
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tabAdapter = new TabsAdapter(strings,recyclerView);
        recyclerView.setAdapter(tabAdapter);
        /**點擊RecyclerView的Tab時將ViewPager滾動至該頁*/
        tabAdapter.onTabClick = new TabsAdapter.OnTabClick() {
            @Override
            public void onTabClick(int position) {
                viewPager.setCurrentItem(position,true);
            }
        };

    }
    /**監聽ViewPager被滑動時的事件處理*/
    private ViewPager.OnPageChangeListener onPageChangeListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
        /**抓取到被滑動到的那一頁*/
        @Override
        public void onPageSelected(int position) {
            tabAdapter.setCurrentPage(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {}
    };


}