package com.example.math_game;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    // Frame
    private FrameLayout frameLayout;
    private int frameWidth, frameHeight, initialFrameWidth;
    private LinearLayout linearLayout;

    // Image
    private ImageView ball_1, ball_2, ball_3, ball_4;
    private Drawable soccer_ball, basket;

    // Size
    private int boxSize;

    // position
    private float boxX, boxY;
    private float ball_1X, ball_1Y;
    private float ball_2X, ball_2Y;
    private float ball_3X, ball_3Y;
    private float ball_4X, ball_4Y;
    private float basketX, basketY;

    // Score
    private TextView scoreLabel, highScoreLabel;
    private int score, highscore, timeCount;

    // Class
    private Timer timer;
    private Handler handler = new Handler();

    // Status
    private boolean start_flag = false;
    private boolean action_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void startGame(View view){

    }

    public void quitGame(View view){

    }
}
