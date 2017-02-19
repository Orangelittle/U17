package com.orangelittle.u17.fragment;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.annotations.NonNull;
import com.orangelittle.u17.R;
import com.orangelittle.u17.activity.SignActivity;
import com.orangelittle.u17.adapter.MineViewPagerAdapter;
import com.orangelittle.u17.fragment.level1.Rb1Fragment;
import com.orangelittle.u17.fragment.level1.Rb2Fragment;
import com.orangelittle.u17.fragment.level1.Rb3Fragment;
import com.orangelittle.u17.widget.FloatBallView;
import com.orangelittle.u17.widget.MyTabViewGroup;
import com.orangelittle.u17.widget.WaveView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment implements View.OnClickListener {

    private TabLayout tab;
    private AppBarLayout mine_appbar;
    private Toolbar mine_toolbar;
    private WaveView mWaveView;
    private ImageView favicon;
    private ImageView changed_favicon;
    private RadioButton clear_history;
    private RadioButton go_sign;
    private RadioButton check_user;
    private FloatBallView mGroup;
    private ViewPager pager;


    private MyTabViewGroup myTab;





    private ViewPager viewPager;

    public MineFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
              View view = inflater.inflate(R.layout.fragment_mine, container, false);
              return  view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        mGroup=(FloatBallView) view.findViewById(R.id.group);
        clear_history=(RadioButton) view.findViewById(R.id.clear_history);
        go_sign=(RadioButton) view.findViewById(R.id.go_sign);
        check_user=(RadioButton) view.findViewById(R.id.check_user);
        mGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.clear_history:
                        Toast.makeText(getContext(),"正在开发",Toast.LENGTH_SHORT).show();
                        clear_history.setChecked(false);
                        break;
                    case  R.id.go_sign:
                        Intent intent=new Intent(getActivity(), SignActivity.class);
                        startActivity(intent);
                        go_sign.setChecked(false);
                        break;
                    case  R.id.check_user:
                        Toast.makeText(getContext(),"正在开发",Toast.LENGTH_SHORT).show();
                        check_user.setChecked(false);
                        break;
                    default:
                }
            }
        });


        myTab = (MyTabViewGroup) view.findViewById(R.id.mine_tab);
        List<Fragment> list = new ArrayList<>();
        list.add(new Rb1Fragment());
        list.add(new Rb2Fragment());
        list.add(new Rb3Fragment());
        final String [] titles={"收藏","历史","下载"};
        MineViewPagerAdapter adapter = new MineViewPagerAdapter( getChildFragmentManager(),list,titles);
        pager = (ViewPager) view.findViewById(R.id.mine_viewpager);
        pager.setAdapter(adapter);
//        tab.setupWithViewPager(pager);
        myTab.setUpwithViewPager(pager);

        favicon = (ImageView) view.findViewById(R.id.touxiang);
        mWaveView = (WaveView) view.findViewById(R.id.mWaveView);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2,-2);
        lp.gravity = Gravity.BOTTOM|Gravity.CENTER;

        mWaveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0,0,0,(int)y+2);
                favicon.setLayoutParams(lp);
            }
        });
        mine_toolbar = ((Toolbar) view.findViewById(R.id.mine_toolbar));
        changed_favicon = (ImageView) view.findViewById(R.id.changed_favicon);

        mine_appbar = ((AppBarLayout) view.findViewById(R.id.mine_appbar));

        mine_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int appbarheight= mine_appbar.getTotalScrollRange();
                float percent=Math.abs((float)verticalOffset/appbarheight);
                int alpha = Math.round(percent * 255);
                mine_toolbar.setAlpha(alpha);
                if (Math.abs(verticalOffset)>=appbarheight-50){
                    changed_favicon.setVisibility(View.VISIBLE);
                    changed_favicon.setAlpha(alpha);
                    ViewCompat.setTranslationZ(changed_favicon,20f);
                    ViewCompat.setScaleY(changed_favicon,percent);
                    ViewCompat.setScaleX(changed_favicon,percent);
                }else{
                    changed_favicon.setVisibility(View.INVISIBLE);
                }
                ViewCompat.setTranslationZ(favicon,20f);
                ViewCompat.setScaleY(favicon,1-percent);
                ViewCompat.setScaleX(favicon,1-percent);
                favicon.setAlpha(255-alpha);
                ViewCompat.setScaleY(mWaveView,1-percent);
                ViewCompat.setTranslationY(mWaveView,40);
            }
        });

//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


        favicon.setOnClickListener(this);
        //todo 权限问题
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//只有6.0以上才需要动态申请

                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {//是否需要显示给用户说明权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);//申请权限
                    } else {
                        Log.e("自定义标签", "类名==MainActivity" + "方法名==onClick=====:" + "其他情况");
                    }

                    //如果是没有权限
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //申请权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
                    } else {
                        bitmap = getLoacalBitmap(Environment.getExternalStorageDirectory()+"/temp_photo.jpg"); //从本地取图片
                    }
                }else{//6.0以下直接执行
//                    bitmap = getLoacalBitmap(Environment.getExternalStorageDirectory()+"/temp_photo.jpg"); //从本地取图片
                    bitmap = getScaleBitmap(getActivity(),Environment.getExternalStorageDirectory()+"/temp_photo.jpg"); //从本地取图片
                }
                if (bitmap != null) {
                    favicon.setImageBitmap(bitmap);
                    favicon.setMaxHeight(40);
                    favicon.setMaxWidth(80);
                    changed_favicon.setImageBitmap(bitmap);
                }else{
                    Snackbar.make(favicon,"么有更新头像哦",Snackbar.LENGTH_SHORT).show();
                }
            }
        }).start();

    }


    /**
     * 申请权限的回调
     *
     * @param requestCode
     * @param permissions  申请的权限
     * @param grantResults 每个权限的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 200:
                int grantResult1 = grantResults[0];
                if (grantResult1 == PackageManager.PERMISSION_GRANTED) {//代表同意权限了
                    bitmap = getLoacalBitmap(Environment.getExternalStorageDirectory()+"/temp_photo.jpg"); //从本地取图片
                } else {//grantResult == PackageManager.PERMISSION_DENIED 用户拒绝了
                    Log.e("自定义标签", "类名==MainActivity" + "方法名==onRequestPermissionsResult=====:" + "你面子不够大");
                }

                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {

        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        }
    }

   private Dialog mCameraDialog;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.touxiang:
                mCameraDialog = new Dialog(getContext(), R.style.my_dialog);
                LinearLayout root = (LinearLayout) LayoutInflater.from(getContext()).inflate(
                        R.layout.fragment_mine_camera_control, null);
                root.findViewById(R.id.btn_open_camera).setOnClickListener(btnClick);
                root.findViewById(R.id.btn_choose_img).setOnClickListener(btnClick);
                root.findViewById(R.id.btn_cancel).setOnClickListener(btnClick);
                mCameraDialog.setContentView(root);
                Window dialogWindow = mCameraDialog.getWindow();
                dialogWindow.setGravity(Gravity.BOTTOM);
                dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
                WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                lp.x = 0; // 新位置X坐标
                lp.y = -20; // 新位置Y坐标
                lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
                lp.alpha = 9f; // 透明度
                lp.alpha = 9f; // 透明度
                dialogWindow.setAttributes(lp);
                mCameraDialog.show();
                break;
        }
    }

    View.OnClickListener btnClick=new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_open_camera: // 打开照相机
                    camera();
                    mCameraDialog.dismiss();
                    break;
                // 打开相册
                case R.id.btn_choose_img:
                    gallery();
                    mCameraDialog.dismiss();
                    break;
                // 取消
                case R.id.btn_cancel:
                    if (mCameraDialog != null) {
                        mCameraDialog.dismiss();
                    }
                    break;
            }
        }
    };

    public static File getTempImage() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            File tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return tempFile;
        }
        return null;
    }

    public static Bitmap getScaleBitmap(Context ctx, String filePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, opt);

        int bmpWidth = opt.outWidth;
        int bmpHeght = opt.outHeight;

        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        opt.inSampleSize = 1;
        if (bmpWidth > bmpHeght) {
            if (bmpWidth > screenWidth)
                opt.inSampleSize = bmpWidth / screenWidth;
        } else {
            if (bmpHeght > screenHeight)
                opt.inSampleSize = bmpHeght / screenHeight;
        }
        opt.inJustDecodeBounds = false;

        bmp = BitmapFactory.decodeFile(filePath, opt);
        return bmp;
    }

    private static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    private Bitmap bitmap;

    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    /*
	 * 从相册获取
	 */
public void gallery() {
    // 激活系统图库，选择一张图片
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
}

    /*
     * 从相机获取
     */
    public void camera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(new File(Environment
                            .getExternalStorageDirectory(), PHOTO_FILE_NAME)));
        }
        startActivityForResult(intent, PHOTO_REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);

            }

        } else if (requestCode == PHOTO_REQUEST_CAMERA) {
            if (hasSdcard()) {
                tempFile = new File(Environment.getExternalStorageDirectory(),
                        PHOTO_FILE_NAME);
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(getContext(), "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            try {
                bitmap = data.getParcelableExtra("data");
                saveBitmap(bitmap);
                favicon.setImageBitmap(bitmap);
                changed_favicon.setImageBitmap(bitmap);
//                boolean delete = tempFile.delete();
//                System.out.println("delete = " + delete);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 剪切图片
     * @param uri
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    /**
     * 存储剪切后的图片
     * @param bitmap
     */
    private void saveBitmap(Bitmap bitmap){
        File f=new File(Environment.getExternalStorageDirectory(),
                PHOTO_FILE_NAME);
        FileOutputStream fOut=null;
        try {
            f.createNewFile();
            fOut=new FileOutputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fOut);

        try {
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

}

