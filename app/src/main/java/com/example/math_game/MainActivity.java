package com.example.math_game;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Frame
    private FrameLayout frameLayout;
    private int frameWidth, frameHeight, initialFrameWidth;
    private LinearLayout linearLayout;

    // Image
    private ImageView basket;

    // Size
    private int basketSize;

    // position
    private float ball_1X, ball_1Y;
    private float ball_2X, ball_2Y;
    private float ball_3X, ball_3Y;
    private float ball_4X, ball_4Y;
    private float basketX, basketY;

    // Score
    private TextView scoreLabel, highScoreLabel, ball_1, ball_2, ball_3, ball_4;
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
        frameLayout = findViewById(R.id.gameFrame);
        linearLayout = findViewById(R.id.start_game);
        basket = findViewById(R.id.basket_image);
        ball_1 = findViewById(R.id.soccerBall_1);
        ball_2 = findViewById(R.id.soccerBall_2);
        ball_3 = findViewById(R.id.soccerBall_3);
        ball_4 = findViewById(R.id.soccerBall_4);
        scoreLabel = findViewById(R.id.score);
        highScoreLabel = findViewById(R.id.high_Score);
    }

    public void changePos(){

        //ball_1
        ball_1Y += 12;
        float ball_1CenterX = ball_1X ;
        float ball_1CenterY = ball_1Y;

        if (hitCheck(ball_1CenterX, ball_1CenterY)){
            ball_1Y = frameHeight + 100;
        }

        if (ball_1Y > frameHeight){
            ball_1Y -= frameHeight;
            ball_1X = (float) Math.floor(Math.random() * (frameWidth - ball_1.getWidth()));
        }
        ball_1.setX(ball_1X);
        ball_1.setY(ball_1Y);


        // Moving
        if (action_flag) {
            //when touch
            basketX += 14;
        }else{
            //releasing
            basketX -= 14;
        }

        // position check
        if (basketX < 0){
            basketX = 0;
        }
        if (frameWidth - basketSize < basketX){
            basketX = frameWidth - basketSize ;

        }
        basket.setX(basketX);
    }


    public boolean hitCheck(float x, float y){
        if (basketX <= x && x <= basketX + basketSize && basketY <= y && y <= frameHeight ){
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (start_flag){
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                action_flag = true;
            }else if (event.getAction()  == MotionEvent.ACTION_UP){
                action_flag = false;
            }
        }
        return true;
    }

    public void startGame(View view){
        start_flag = true;
        linearLayout.setVisibility(View.INVISIBLE);

        if (frameHeight == 0){
            frameHeight = frameLayout.getHeight();
            frameWidth = frameLayout.getWidth();

            basketSize = basket.getHeight();
            basketX = basket.getX();
            basketY = basket.getY();
        }

        basket.setX(0.0f);
        ball_1Y = ball_1.getY();
        ball_2Y = ball_2.getY();
        ball_3Y = ball_3.getY();
        ball_4Y = ball_4.getY();

        basket.setVisibility(View.VISIBLE);
        ball_1.setVisibility(View.VISIBLE);
        ball_2.setVisibility(View.VISIBLE);
        ball_3.setVisibility(View.VISIBLE);
        ball_4.setVisibility(View.VISIBLE);

        timeCount = 0;
        score = 0;
        scoreLabel.setText("Score: 0");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (start_flag){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                           changePos();
                        }
                    });
                }
            }
        }, 0, 20 );
    }

    public void quitGame(View view){

    }
}
