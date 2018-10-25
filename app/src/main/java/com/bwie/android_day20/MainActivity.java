package com.bwie.android_day20;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private CirCleView MyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView = findViewById(R.id.Bull);
        ObjectAnimator move = ObjectAnimator.ofFloat(MyView, "translationY", 0, 1100f);
        ObjectAnimator scale2 = ObjectAnimator.ofFloat(MyView, "translationX",0,600f);
        AnimatorSet animset = new AnimatorSet();
        animset.play(move).with(scale2);
        animset.setDuration(3000);
        animset.start();
        // 动画监听
        animset.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
