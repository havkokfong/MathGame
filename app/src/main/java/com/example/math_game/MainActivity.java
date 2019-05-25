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

import java.util.ArrayList;
import java.util.Random;
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

    //Question
    private int num1, num2, result;
    private TextView questionBox, questionBox2, questionBox3;

    //Title
    private TextView titleLabel;

    // Class
    private Timer timer;
    private Handler handler = new Handler();
    private Random random;
    private ArrayList symList;

    // Status
    private boolean start_flag = false;
    private boolean action_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.gameFrame);
        linearLayout = findViewById(R.id.start_game);
        titleLabel = findViewById(R.id.titleText);
        basket = findViewById(R.id.basket_image);
        ball_1 = findViewById(R.id.soccerBall_1);
        ball_2 = findViewById(R.id.soccerBall_2);
        ball_3 = findViewById(R.id.soccerBall_3);
        ball_4 = findViewById(R.id.soccerBall_4);
        questionBox = findViewById(R.id.questionBox1);
        questionBox2 = findViewById(R.id.questionBox2);
        questionBox3 = findViewById(R.id.questionBox3);
        scoreLabel = findViewById(R.id.score);
        highScoreLabel = findViewById(R.id.high_Score);
        random = new Random();
        num1 = random.nextInt(99);
        num2 = random.nextInt(99);
        symList = new ArrayList();
        symList.add("+");
        symList.add("-");
        symList.add("*");
        symList.add("/");

    }

    public void changePos(){
        questionBox.setText(String.valueOf(num1));
        questionBox2.setText("+");
        questionBox3.setText(String.valueOf(num2));

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


        //ball_2
        ball_2Y += 12;
        float ball_2CenterX = ball_2X ;
        float ball_2CenterY = ball_2Y;

        if (hitCheck(ball_2CenterX, ball_2CenterY)){
            ball_2Y = frameHeight + 100;
        }

        if (ball_2Y > frameHeight){
            ball_2Y -= frameHeight;
            ball_2X = (float) Math.floor(Math.random() * (frameWidth - ball_2.getWidth()));
        }
        ball_2.setX(ball_2X);
        ball_2.setY(ball_2Y);



        //ball_3
        ball_3Y += 12;
        float ball_3CenterX = ball_3X ;
        float ball_3CenterY = ball_3Y;

        if (hitCheck(ball_3CenterX, ball_3CenterY)){
            ball_3Y = frameHeight + 100;
        }

        if (ball_3Y > frameHeight){
            ball_3Y -= frameHeight;
            ball_3X = (float) Math.floor(Math.random() * (frameWidth - ball_3.getWidth()));
        }
        ball_3.setX(ball_3X);
        ball_3.setY(ball_3Y);



        //ball_4
        ball_4Y += 12;
        float ball_4CenterX = ball_4X ;
        float ball_4CenterY = ball_4Y;

        if (hitCheck(ball_4CenterX, ball_4CenterY)){
            ball_4Y = frameHeight + 100;
        }

        if (ball_4Y > frameHeight){
            ball_4Y -= frameHeight;
            ball_4X = (float) Math.floor(Math.random() * (frameWidth - ball_4.getWidth()));
        }
        ball_4.setX(ball_4X);
        ball_4.setY(ball_4Y);



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

    // Hitting Check
    public boolean hitCheck(float x, float y){
        if (basketX <= x && x <= basketX + basketSize && basketY <= y && y <= frameHeight ){
            return true;
        }
        return false;
    }

    // Touching Control
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


    // Start game
    public void startGame(View view){
        start_flag = true;
        linearLayout.setVisibility(View.INVISIBLE);
        titleLabel.setVisibility(View.INVISIBLE);

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

        questionBox.setVisibility(View.VISIBLE);
        questionBox2.setVisibility(View.VISIBLE);
        questionBox3.setVisibility(View.VISIBLE);
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
