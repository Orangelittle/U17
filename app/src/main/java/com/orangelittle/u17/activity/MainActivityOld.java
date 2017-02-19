package com.orangelittle.u17.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
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

public class MainActivityOld extends BaseActivity
        implements View.OnClickListener {

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
//    private FloatingActionButton fab;
//    private ImageView iv1;
//    private ImageView iv2;
//    private ImageView iv3;
//    private ImageView iv4;
//    private int fabTop;
//    private int iv1H;
//    private int iv2H;
//    private int iv3H;
//    private int iv4H;
//    private ViewPager main_viewpager;
    private List<Fragment> mFragmentList;
//    private MyViewpagerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        U17Application.context = this;
        initView();



        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(false);
        SwipeBackHelper.getCurrentPage(this).setDisallowInterceptTouchEvent(true);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(avityCompat.START);
//        return true;
//    }


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
//        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);



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
//        adapter = new MyViewpagerAdapter(getSupportFragmentManager(),mFragmentList);
//        main_viewpager.setAdapter(adapter);
//        mRg.setUpwithViewPager(main_viewpager);

        currentFragment = recommendFragment;
        mFragmentManager.beginTransaction().add(R.id.container, recommendFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.container, boutiqueFragment).hide(boutiqueFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.container, mineFragment).hide(mineFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.container, gameFragment).hide(gameFragment).commit();
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(this);
//        iv1 = (ImageView) findViewById(R.id.iv1);
//        iv2 = (ImageView) findViewById(R.id.iv2);
//        iv3 = (ImageView) findViewById(R.id.iv3);
//        iv4 = (ImageView) findViewById(R.id.iv4);
//        iv1.setOnClickListener(this);
//        iv2.setOnClickListener(this);
//        iv3.setOnClickListener(this);
//        iv4.setOnClickListener(this);
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
//            case R.id.fab:
//                if (isOpen) {
//                    closeAnimation();
//                } else {
//                    openAnimation();
//                }
//                break;
//            case R.id.iv1:
//                mFragmentManager.beginTransaction().hide(currentFragment).show(recommendFragment).commit();
//                currentFragment = recommendFragment;
//                check(0);
//                closeAnimation();
//                break;
//            case R.id.iv2:
//                mFragmentManager.beginTransaction().hide(currentFragment).show(boutiqueFragment).commit();
//                currentFragment = boutiqueFragment;
//                check(1);
//                closeAnimation();
//                break;
//            case R.id.iv3:
//                mFragmentManager.beginTransaction().hide(currentFragment).show(gameFragment).commit();
//                currentFragment = gameFragment;
//                check(2);
//                closeAnimation();
//                break;
//            case R.id.iv4:
//                mFragmentManager.beginTransaction().hide(currentFragment).show(mineFragment).commit();
//                currentFragment = gameFragment;
//                check(3);
//                closeAnimation();
//                break;


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
//        main_viewpager.setCurrentItem(position,false);
    }

//    public void openAnimation() {
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 135, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setDuration(500);
//        rotateAnimation.setFillAfter(true);
//        fab.startAnimation(rotateAnimation);
//
//        setImgsVis(View.VISIBLE);
//        ViewCompat.animate(iv1).setDuration(500).alpha(1).translationYBy((-iv1H - 10)).start();
//        ViewCompat.animate(iv2).setDuration(600).alpha(1).translationYBy((-iv1H - iv2H - 20)).start();
//        ViewCompat.animate(iv3).setDuration(700).alpha(1).translationYBy((-iv1H - iv2H - iv3H - 30)).start();
//        ViewCompat.animate(iv4).setDuration(800).alpha(1).translationYBy((-iv1H - iv2H - iv3H -iv4H- 40)).start();
//        isOpen = true;
//    }

//    public void setImgsVis(int visible) {
//        iv1.setVisibility(visible);
//        iv2.setVisibility(visible);
//        iv3.setVisibility(visible);
//        iv4.setVisibility(visible);
//    }
//    public void closeAnimation() {
//        RotateAnimation rotateAnimation = new RotateAnimation(135, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setDuration(500);
//        rotateAnimation.setFillAfter(true);
//        fab.startAnimation(rotateAnimation);
//
//        ViewCompat.animate(iv1).setDuration(800).alpha(0).translationYBy((iv1H + 10)).start();
//        ViewCompat.animate(iv2).setDuration(700).alpha(0).translationYBy((iv1H + iv2H + 20)).start();
//        ViewCompat.animate(iv3).setDuration(600).alpha(0).translationYBy((iv1H + iv2H + iv3H + 30)).start();
//        ViewCompat.animate(iv4).setDuration(500).alpha(0).translationYBy((iv1H + iv2H + iv3H +iv4H+ 40)).start();
//        isOpen = false;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                SystemClock.sleep(800);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        setImgsVis(View.GONE);
//                    }
//                });
//            }
//        }).start();
//    }

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
//    /***
//     * 再按一次退出
//     */
//    private long oldTime;
//    private long currentTime;
//    private int exitTime = 2000;//3秒退出
//    @Override
//    public void onBackPressed() {
//        currentTime = System.currentTimeMillis();
//        if( currentTime - oldTime >= exitTime){
//            oldTime = currentTime;
//            Snackbar.make(mRg,"再按一次返回键退出程序",Snackbar.LENGTH_SHORT).show();
//            return;
//        }else {
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(0);
//        }
//    }
}
