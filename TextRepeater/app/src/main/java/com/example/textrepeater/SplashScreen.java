package com.example.textrepeater;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

public class SplashScreen extends AppCompatActivity {
    private Typeface typeface;
    private TextView appName;
    private ProgressBar progressBar;
    private int progress;
    private RoundedImageView imgLogo;
    private Animation animation1,animation2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        View view = getWindow().getDecorView();
        int uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        view.setSystemUiVisibility(uiOption);
        setContentView(R.layout.splash_screen);

        progressBar = findViewById(R.id.progressBar);
        appName = findViewById(R.id.appName);
        imgLogo = findViewById(R.id.imgLogo);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/monitor.ttf");
        appName.setTypeface(typeface);
        animation1 = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.up_from_bottom_slow);
        animation2 = AnimationUtils.loadAnimation(SplashScreen.this,R.anim.fade_in);
        imgLogo.setAnimation(animation1);
        appName.setAnimation(animation2);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
                goToHomePage();
            }
        });
        thread.start();
    }

    public void startProgress(){
        for (progress = 10; progress<=100; progress = progress + 20){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void goToHomePage(){
        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}