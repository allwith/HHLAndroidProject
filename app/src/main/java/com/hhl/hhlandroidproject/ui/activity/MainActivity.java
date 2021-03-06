package com.hhl.hhlandroidproject.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.hhl.hhlandroidproject.R;
import com.hhl.hhlandroidproject.entity.LeftMenuItem;
import com.hhl.hhlandroidproject.ui.base.BaseActivity;
import com.hhl.hhlandroidproject.ui.view.BallCycleDrawable;
import com.hhl.hhlcommonlibs.adapter.HHLBaseAdapterHelper;
import com.hhl.hhlcommonlibs.adapter.HHLQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.lv_left_menu)
    ListView mLeftMenuLv;
    @Bind(R.id.id_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;

    private HHLQuickAdapter<LeftMenuItem> mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        initProgressBar();
    }

    private void initProgressBar() {
        mProgressBar.setIndeterminateDrawable(new BallCycleDrawable.Builder(this).build());
    }

    public void initViews() {
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationIcon(R.drawable.ic_action_menu);

        //初始化菜单
        initLeftMenu();

        initLeftMenuData();
    }

    private void initLeftMenuData() {
        List<LeftMenuItem> itemList = new ArrayList<>();
        //关于我
        LeftMenuItem aboutMeItem = new LeftMenuItem();
        aboutMeItem.setMenuTitle("关于我");
        itemList.add(aboutMeItem);

        //单个Item
        LeftMenuItem singleTypeItem = new LeftMenuItem();
        singleTypeItem.setMenuTitle("单个Item");
        itemList.add(singleTypeItem);

        //多个item
        LeftMenuItem multiTypeItem = new LeftMenuItem();
        multiTypeItem.setMenuTitle("多个Item");
        itemList.add(multiTypeItem);

        //ViewDragHelper
        LeftMenuItem viewDragHelperItem = new LeftMenuItem();
        viewDragHelperItem.setMenuTitle("ViewDragHelper");
        itemList.add(viewDragHelperItem);

        //sticky
        LeftMenuItem stikey = new LeftMenuItem();
        stikey.setMenuTitle("StickyListHeader");
        itemList.add(stikey);

        LeftMenuItem flipCardItem = new LeftMenuItem();
        flipCardItem.setMenuTitle("翻转卡片");
        itemList.add(flipCardItem);

        LeftMenuItem pullUpItem = new LeftMenuItem();
        pullUpItem.setMenuTitle("上拉加载更多");
        itemList.add(pullUpItem);

        //添加总得数据
        mMenuAdapter.addAll(itemList);
    }

    /**
     * 初始化菜单
     */
    private void initLeftMenu() {
        mMenuAdapter = new HHLQuickAdapter<LeftMenuItem>(this,R.layout.left_menu_item) {
            @Override
            protected void convert(HHLBaseAdapterHelper adapterHelper, LeftMenuItem item) {
                adapterHelper.setText(R.id.tv_menu_title,item.getMenuTitle());
            }
        };

        mLeftMenuLv.setAdapter(mMenuAdapter);

        mLeftMenuLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LeftMenuItem item = (LeftMenuItem) parent.getAdapter().getItem(position);

                if (item != null) {
                    if ("关于我".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,AboutActivity.class));
                    }else if ("单个Item".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,SimpleItemActivity.class));
                    }else if ("多个Item".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,MultiItemActivity.class));
                    }else if ("ViewDragHelper".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,ViewDragHelperActivity.class));
                    }else if ("StickyListHeader".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,StickyListHeaderActivity2.class));
                    }else if ("翻转卡片".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,FlipCardActivity.class));
                    }else if ("上拉加载更多".equals(item.getMenuTitle())){
                        startActivity(new Intent(MainActivity.this,PullUpActivity.class));
                    }
                }

                mDrawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
