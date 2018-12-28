package com.feiyang.wanandroid.ui.main.activity;

import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.feiyang.wanandroid.R;
import com.feiyang.wanandroid.base.BaseActivity;
import com.feiyang.wanandroid.core.util.StatusBarUtils;
import com.feiyang.wanandroid.databinding.ActivityMainBinding;
import com.feiyang.wanandroid.ui.main.fragment.KnowledgeHierarchyFragment;
import com.feiyang.wanandroid.ui.main.fragment.MainFragment;
import com.feiyang.wanandroid.ui.main.fragment.NavigationFragment;
import com.feiyang.wanandroid.ui.main.fragment.ProjectFragment;
import com.feiyang.wanandroid.ui.main.vm.MainViewModel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends BaseActivity<MainActivity.Param, ActivityMainBinding, MainViewModel> {

    private Fragment[] fragments;


    @Override
    protected void initViews() {
        super.initViews();
        setUpFragments();
        observeData();
    }

    private void setUpFragments() {
        if (fragments == null) {
            fragments = new Fragment[4];
        }

        MainFragment               main               = MainFragment.newInstance();
        KnowledgeHierarchyFragment knowledgeHierarchy = KnowledgeHierarchyFragment.newInstance();
        NavigationFragment         navigation         = NavigationFragment.newInstance();
        ProjectFragment            project            = ProjectFragment.newInstance();

        fragments[0] = main;
        fragments[1] = knowledgeHierarchy;
        fragments[2] = navigation;
        fragments[3] = project;

        databinding.vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }
        });

        databinding.vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        databinding.bottomNavigationView.setSelectedItemId(R.id.tab_main_pager);
                        break;
                    case 1:
                        databinding.bottomNavigationView.setSelectedItemId(R.id.tab_knowledge_hierarchy);
                        break;
                    case 2:
                        databinding.bottomNavigationView.setSelectedItemId(R.id.tab_navigation);
                        break;
                    case 3:
                        databinding.bottomNavigationView.setSelectedItemId(R.id.tab_project);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        databinding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            databinding.title.setText(item.getTitle());
            switch (item.getItemId()) {
                case R.id.tab_main_pager://主页
                    databinding.vp.setCurrentItem(0);
                    break;
                case R.id.tab_knowledge_hierarchy://知识体系
                    databinding.vp.setCurrentItem(1);
                    break;
                case R.id.tab_navigation://导航
                    databinding.vp.setCurrentItem(2);
                    break;
                case R.id.tab_project://项目
                    databinding.vp.setCurrentItem(3);
                    break;
            }
            return true;
        });
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getVM() {
        return MainViewModel.class;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(databinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        databinding.title.setText("主页");
        StatusBarUtils.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.colorPrimary), 1f);
        initDrawerLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usage:
                break;
            case R.id.action_search:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, databinding.drawer, databinding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View  mContent   = databinding.drawer.getChildAt(0);
                float scale      = 1 - slideOffset;
                float endScale   = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        databinding.drawer.addDrawerListener(toggle);

        databinding.navView.setNavigationItemSelectedListener(item -> {
            databinding.navView.setCheckedItem(item);
            switch (item.getItemId()) {
                case R.id.nav_item_about_us:
                    break;
                case R.id.nav_item_logout:
                    break;
                case R.id.nav_item_my_collect:
                    break;
                case R.id.nav_item_setting:
                    break;
                case R.id.nav_item_wan_android:
                    break;
            }
            return true;
        });
    }



    abstract class Param implements Parcelable {

    }


}
