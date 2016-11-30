package com.example.sun.maoyandianying.avtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sun.maoyandianying.MainActivity;
import com.example.sun.maoyandianying.R;
import com.example.sun.maoyandianying.databean.SplashBean;
import com.example.sun.maoyandianying.utils.CacheUtils;
import com.example.sun.maoyandianying.utils.Url;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class SplastActivity extends Activity {

    @Bind(R.id.iv_bg_splash)
    RelativeLayout ivBgSplash;
    @Bind(R.id.iv_bg_splash_net)
    ImageView ivBgSplashNet;
    private Handler handler;
    private SplashBean splashBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splast);
        ButterKnife.bind(this);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setBgImageView();
            }
        }, 3000);
    }

    //设置背景图片
    private void setBgImageView() {
        //从本地获取图片
        String string = CacheUtils.getString(this, Url.SPLASH_URL);
        if(!TextUtils.isEmpty(string)){
            Log.e("TAG","111111111");
            processData(string);
        }
        getPhotoFrom();
    }

    //从网络获取图片json
    private void getPhotoFrom() {
        OkHttpUtils
                .get()
                .url(Url.SPLASH_URL)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    public class MyStringCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "联网失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            switch (id){
                case 100:
                    processData(response);
                    //保存到本地
                    CacheUtils.putString(SplastActivity.this,Url.SPLASH_URL,response);
                    break;
                case 101:
                    Toast.makeText(SplastActivity.this, "https", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    //解析数据
    private void processData(String json) {
        splashBean = new Gson().fromJson(json, SplashBean.class);

        //显示动画效果
        showAnimation();
    }

    private void showAnimation() {
        if(splashBean.getPosters().size() == 0) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else {
            int index = (int) (Math.random() * splashBean.getPosters().size());
            String picURl = splashBean.getPosters().get(index).getPic();

            //使用Glide设置图片
            Glide.with(this).load(picURl).into(ivBgSplashNet);
//            x.image().bind(iv_bg_splash_net, picURl);
            ivBgSplashNet.setVisibility(View.VISIBLE);
            //设置动画
            ScaleAnimation sa = new ScaleAnimation(0.8f, 1, 0.8f, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
            sa.setDuration(1500);
            sa.setFillAfter(true);
            ivBgSplashNet.startAnimation(sa);
            //设置完成后的监听
            sa.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    ivBgSplash.setVisibility(View.GONE);

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(SplastActivity.this,MainActivity.class));
                    finish();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
