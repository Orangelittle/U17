package com.orangelittle.u17.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.orangelittle.u17.R;
import com.orangelittle.u17.fragment.BoutiqueFragment;
import com.orangelittle.u17.fragment.GameFragment;
import com.orangelittle.u17.fragment.MineFragment;
import com.orangelittle.u17.fragment.RecommendFragment;
import com.orangelittle.u17.global.U17Application;
import com.orangelittle.u17.interfaces.IOnFocusListenable;
import com.jude.swipebackhelper.SwipeBackHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity
        implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private RadioButton mRbRecommend;
    private RadioButton mRbBoutique;
    private RadioButton mRbMine;
    private RadioButton mRbGame;
    private RadioGroup mRg;
    private FragmentManager mFragmentManager;
    private Fragment currentFragment;
    private RecommendFragment recommendFragment;
    private BoutiqueFragment boutiqueFragment;
    private MineFragment mineFragment;
    private GameFragment gameFragment;
    private List<Fragment> mFragmentList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        U17Application.context = this;
        initView();

        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);
        SwipeBackHelper.getCurrentPage(this).setDisallowInterceptTouchEvent(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initView() {
        mRbRecommend = (RadioButton) findViewById(R.id.rb_recommend);
        mRbRecommend.setOnClickListener(this);
        mRbBoutique = (RadioButton) findViewById(R.id.rb_boutique);
        mRbBoutique.setOnClickListener(this);
        mRbMine = (RadioButton) findViewById(R.id.rb_mine);
        mRbMine.setOnClickListener(this);
        mRbGame = (RadioButton) findViewById(R.id.rb_game);
        mRbGame.setOnClickListener(this);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mRg.setOnClickListener(this);

        mFragmentManager = getSupportFragmentManager();
        recommendFragment = new RecommendFragment();
        boutiqueFragment = new BoutiqueFragment();
        mineFragment = new MineFragment();
        gameFragment = new GameFragment();

        mFragmentList=new ArrayList<>();
        mFragmentList.add(recommendFragment);
        mFragmentList.add(boutiqueFragment);
        mFragmentList.add(gameFragment);
        mFragmentList.add(mineFragment);

        currentFragment = recommendFragment;
        mFragmentManager.beginTransaction().add(R.id.container, recommendFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.container, boutiqueFragment).hide(boutiqueFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.container, mineFragment).hide(mineFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.container, gameFragment).hide(gameFragment).commit();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(currentFragment instanceof IOnFocusListenable) {
            ((IOnFocusListenable) currentFragment).onWindowFocusChanged(hasFocus);
        }
    }

    boolean isOpen = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_recommend:
                mFragmentManager.beginTransaction().hide(currentFragment).show(recommendFragment).commit();
                currentFragment = recommendFragment;
                break;
            case R.id.rb_boutique:
                mFragmentManager.beginTransaction().hide(currentFragment).show(boutiqueFragment).commit();
                currentFragment = boutiqueFragment;
                break;
            case R.id.rb_mine:
                mFragmentManager.beginTransaction().hide(currentFragment).show(mineFragment).commit();
                currentFragment = mineFragment;
                break;
            case R.id.rb_game:
                mFragmentManager.beginTransaction().hide(currentFragment).show(gameFragment).commit();
                currentFragment = gameFragment;
                break;


        }
    }

    private void check(int position) {
        for (int i = 0; i < mRg.getChildCount(); i++) {
            RadioButton childAt = (RadioButton) mRg.getChildAt(i);
            if (position != i) {
                childAt.setChecked(false);
            } else {
                childAt.setChecked(true);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (U17Application.context != null) {
            U17Application.context = null;
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}
