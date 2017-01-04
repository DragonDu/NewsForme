package com.du.newsforme;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.du.newsforme.bean.UserInfo;
import com.du.newsforme.fragment.HomeFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_main)
    FrameLayout fl_main;

    @BindView(R.id.ngv)
    NavigationView navigationView;
    @BindView(R.id.drawlt)
    DrawerLayout drawerLayout;
//    @BindView(R.id.profile_image)
    CircleImageView circleImageView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coor)
    CoordinatorLayout coor;


    private List<Fragment> mfrlist = new ArrayList<>();
    private FragmentManager manager;
//    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private SearchView searchView;
    private TextView header_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        EventBus.getDefault().register(this);
        mDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout, toolbar, R.string.dakai, R.string.guanbi);
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerContent(navigationView);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        setupDrawerContent(mNavigationView);

        //获取导航header 的view ID
        View headerView = navigationView.getHeaderView(0);
        circleImageView= (CircleImageView) headerView.findViewById(R.id.profile_image);
        header_name = (TextView) headerView.findViewById(R.id.tv_header_name);
        initEvent();


//        initview();
//        initevent();


//        initEvet();
        switch2News();
    }

    private void initEvent() {

        //头像点击设置
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startLoginActivity(getApplicationContext());
            }
        });

        //浮动按钮设置
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "回到顶端", Toast.LENGTH_SHORT).show();
//                Snackbar.make(view, "我要分享啊", Snackbar.LENGTH_LONG)
//                        .setAction("什么鬼", null).show();

                EventBus.getDefault().post(new NewsEvent());
            }
        });
    }

    /**
     * 接受登录后的用户信息
     * @param user
     */

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setUser(LoginActivity.TupianClass user){
        UserInfo userInfo = user.shuaxin();
        header_name.setText(userInfo.getUserName());
        Log.e("用户的名字", userInfo.getUserName());
        circleImageView.setImageURI(Uri.parse(userInfo.getUserIcon()));

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        mMainPresenter.switchNavigation(menuItem.getItemId());
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setQueryHint("请输入您想查询的内容");
        searchView.setSubmitButtonEnabled(true);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("query", query);
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.action_settings:
                Log.e("监听搜索", "setting");
                return true;
            case R.id.search:
                String s = searchView.getQuery().toString();
                Log.e("监听搜索", s);
                Log.e("监听搜索", "asdasd");
                return true;
        }
//        if (id == R.id.action_settings) {
//            Log.e("监听搜索", "setting");
//            return true;
//        } else if (id == R.id.search) {
//            String s = searchView.getQuery().toString();
//            Log.e("监听搜索", s);
//            Log.e("监听搜索", "asdasd");
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    //    /**
//     * 加载布局
//     */
//    private void initview() {
//        //出事话抽屉
//
//        //出事话底部菜单
//        bottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.home_press, "主页")
//                        .setActiveColorResource(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.newscenter_press, "新闻")
//                        .setActiveColorResource(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.smartservice_press, "智能")
//                        .setActiveColorResource(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.setting_press, "设置")
//                        .setActiveColorResource(R.color.colorPrimary)
//                )
//
//                .setFirstSelectedPosition(0)
//                .initialise();
//
//
//    }
//    public void initEvet() {
//        mfrlist.add(new HomeFragment());
//        manager = getSupportFragmentManager();
//        FtovpAdapter adapter = new FtovpAdapter(manager, mfrlist);
//        fl_main.setAdapter(adapter);
//    }
    public void switch2News() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, new HomeFragment()
        ).commit();
        toolbar.setTitle("小杜");
    }

    /**
     * 加载事件
     */
//    private void initevent() {
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("被电击了","aaaa");
//            }
//        });

//        mfrlist.add(new HomeFragment());
//        mfrlist.add(new SecondFragment());
//        mfrlist.add(new ThirdFragment());
//        mfrlist.add(new FourthFragment());
//        manager = getSupportFragmentManager();
//        FtovpAdapter adapter = new FtovpAdapter(manager, mfrlist);
//        fl_main.setAdapter(adapter);
    //给底部按钮添加点击事件
//        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar
//                .OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(int position) {
//                fl_main.setCurrentItem(position);
//
//            }
//
//            @Override
//            public void onTabUnselected(int position) {
//            }
//
//            @Override
//            public void onTabReselected(int position) {
//            }
//        });

    //给Viewpager 添加滑动事件
//        fl_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int
//                    positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                bottomNavigationBar.selectTab(position);
//                switch (position) {
//
//                    case 0:
//                        toolbar.setTitle("小杜新闻");
//                        break;
//                    case 1:
//                        toolbar.setTitle("新闻检索");
//
//                        break;
//                    case 2:
//                        toolbar.setTitle("小机器人");
//                        break;
//                    case 3:
//                        toolbar.setTitle("不知道了");
//                        break;
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//}
    public void newthr() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    public void finish() {
        super.finish();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 按钮回到最顶层事件
     */
    public class NewsEvent {

    }
}
