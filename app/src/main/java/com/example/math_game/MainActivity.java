package com.example.math_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // Frame
    private FrameLayout frameLayout;
    private int frameWidth, frameHeight, initialFrameWidth;
    private LinearLayout linearLayout, mainLayout;

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
    private int speed1, speed2, speed3, speed4;

    // Score
    private TextView scoreLabel, liveLabel, highScoreLabel, ball_1, ball_2, ball_3, ball_4;
    private SharedPreferences score_record;

    private int score, highscore, timeCount, live;

    //Question
    private int num1, num2, result, ranSign, wrongNum, ranWrongNum, wrongNum2, wrongNum3;
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

    //setting_preferences
    private SharedPreferences preferences, object_preferences;
    private int theme, object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.mainLayout);
        frameLayout = findViewById(R.id.gameFrame);
        linearLayout = findViewById(R.id.start_game);
        basket = findViewById(R.id.basket_image);
        ball_1 = findViewById(R.id.soccerBall_1);
        ball_2 = findViewById(R.id.soccerBall_2);
        ball_3 = findViewById(R.id.soccerBall_3);
        ball_4 = findViewById(R.id.soccerBall_4);
        questionBox = findViewById(R.id.questionBox1);
        questionBox2 = findViewById(R.id.questionBox2);
        questionBox3 = findViewById(R.id.questionBox3);
        scoreLabel = findViewById(R.id.score);
        liveLabel = findViewById(R.id.live);
        highScoreLabel = findViewById(R.id.high_Score);
        random = new Random();
        score_record = getSharedPreferences("Game_Score", Context.MODE_PRIVATE);
        preferences = getSharedPreferences("theme_value", MODE_PRIVATE);
        object_preferences = getSharedPreferences("object_value", MODE_PRIVATE);
        highscore = score_record.getInt("HIGH_SCORE", 0);
        highScoreLabel.setText("High Score: " + highscore);

    }

    @Override
    protected void onStart() {
        super.onStart();
        theme = preferences.getInt("theme_value", 0);
        if (theme == 0){
            mainLayout.setBackgroundResource(R.drawable.background_blue_with_cloud);
        }
        else if (theme == 1){
            mainLayout.setBackgroundResource(R.drawable.background_orange_with_cloud);
        }
        else if (theme == 2){
            mainLayout.setBackgroundResource(R.drawable.background_purple_with_cloud);
        }
        else if (theme == 3){
            mainLayout.setBackgroundResource(R.drawable.background_natural_with_cloud);
        }

        object = object_preferences.getInt("object_value", 0);

        if (object == 0){
            ball_1.setBackgroundResource(R.drawable.soccer_ball);
            ball_2.setBackgroundResource(R.drawable.soccer_ball_2);
            ball_3.setBackgroundResource(R.drawable.soccer_ball_3);
            ball_4.setBackgroundResource(R.drawable.soccer_ball_4);
        }
        else if (object == 1){
            ball_1.setBackgroundResource(R.drawable.stone);
            ball_2.setBackgroundResource(R.drawable.stone_2);
            ball_3.setBackgroundResource(R.drawable.stone_3);
            ball_4.setBackgroundResource(R.drawable.stone_4);
        }
        else if(object == 2){
            ball_1.setBackgroundResource(R.drawable.bottle);
            ball_2.setBackgroundResource(R.drawable.bottle_2);
            ball_3.setBackgroundResource(R.drawable.bottle_3);
            ball_4.setBackgroundResource(R.drawable.bottle_4);
        }
    }

    public void changePos(){

        //Ball Speed
        speed1 = random.nextInt((15 - 5) + 1) + 5;
        speed2 = random.nextInt((10 - 3) + 1) + 3;
        speed3 = random.nextInt((12 - 7) + 1) + 7;
        speed4 = random.nextInt((15 - 8) + 1) + 8;

        //ball_1
        ball_1Y += speed1;
        float ball_1CenterX = ball_1X ;
        float ball_1CenterY = ball_1Y;

        if (hitCheck(ball_1CenterX, ball_1CenterY)){
            ball_1Y = frameHeight + 100;
            score += 10;
            result = 0;
            scoreLabel.setText("Score: " + score);
            ball_2Y = frameHeight;
            ball_3Y = frameHeight;
            ball_4Y = frameHeight;
            question();
        }

        if (ball_1Y > frameHeight){
            ball_1Y -= frameHeight;
            ball_1X = (float) Math.floor(Math.random() * (frameWidth - ball_1.getWidth()));
        }
        ball_1.setX(ball_1X);
        ball_1.setY(ball_1Y);


        //ball_2
        ball_2Y += speed2;
        float ball_2CenterX = ball_2X ;
        float ball_2CenterY = ball_2Y;

        if (hitCheck(ball_2CenterX, ball_2CenterY)){
            ball_2Y = frameHeight + 100;
            live -= 1;
            liveLabel.setText("Live: " + live);
            ball_1Y += frameHeight;
            ball_3Y += frameHeight;
            ball_4Y += frameHeight;
        }

        if (ball_2Y > frameHeight){
            ball_2Y -= frameHeight;
            ball_2X = (float) Math.floor(Math.random() * (frameWidth - ball_2.getWidth()));
        }
        ball_2.setX(ball_2X);
        ball_2.setY(ball_2Y);



        //ball_3
        ball_3Y += speed3;
        float ball_3CenterX = ball_3X ;
        float ball_3CenterY = ball_3Y;

        if (hitCheck(ball_3CenterX, ball_3CenterY)){
            ball_3Y = frameHeight + 100;
            live -= 1;
            liveLabel.setText("Live: " + live);
            ball_2Y = frameHeight;
            ball_1Y = frameHeight;
            ball_4Y = frameHeight;

        }

        if (ball_3Y > frameHeight){
            ball_3Y -= frameHeight;
            ball_3X = (float) Math.floor(Math.random() * (frameWidth - ball_3.getWidth()));
        }
        ball_3.setX(ball_3X);
        ball_3.setY(ball_3Y);



        //ball_4
        ball_4Y += speed4;
        float ball_4CenterX = ball_4X ;
        float ball_4CenterY = ball_4Y;

        if (hitCheck(ball_4CenterX, ball_4CenterY)){
            ball_4Y = frameHeight + 100;
            live -= 1;
            liveLabel.setText("Live: " + live);
            ball_2Y += frameHeight;
            ball_3Y += frameHeight;
            ball_1Y += frameHeight;
        }

        if (ball_4Y > frameHeight){
            ball_4Y -= frameHeight;
            ball_4X = (float) Math.floor(Math.random() * (frameWidth - ball_4.getWidth()));
        }
        ball_4.setX(ball_4X);
        ball_4.setY(ball_4Y);

        // Game Over
        if (live == 0){
            gameOver();
        }


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

    //Question
    public void question(){
        ranSign = random.nextInt(3);
        ranWrongNum = random.nextInt(5) + 1;
        num1 = random.nextInt(10);
        num2 = random.nextInt(10);

        if (ranSign == 0){
            result = 0;
            wrongNum = 0;
            wrongNum2 = 0;
            wrongNum3 = 0;
            result = num1 + num2;
            wrongNum = result + random.nextInt((5 - 2) + 1) + 2;
            wrongNum2 = result - random.nextInt((7 - 4) + 1) + 4;
            wrongNum3 = result + random.nextInt((3 + 2) + 1) + 2;
            questionBox.setText(String.valueOf(num1));
            questionBox2.setText("+");
            questionBox3.setText(String.valueOf(num2));
            ball_1.setText(String.valueOf(result));
            ball_2.setText(String.valueOf(wrongNum));
            ball_3.setText(String.valueOf(wrongNum2));
            ball_4.setText(String.valueOf(wrongNum3));
        }
        else if (ranSign == 1){
            result = 0;
            wrongNum = 0;
            wrongNum2 = 0;
            wrongNum3 = 0;
            result = num1 - num2;
            wrongNum = result + random.nextInt((5 - 2) + 1) + 2;
            wrongNum2 = result - random.nextInt((7 - 4) + 1) + 4;
            wrongNum3 = result + random.nextInt((3 + 2) + 1) + 2;
            questionBox.setText(String.valueOf(num1));
            questionBox2.setText("-");
            questionBox3.setText(String.valueOf(num2));
            ball_1.setText(String.valueOf(result));
            ball_2.setText(String.valueOf(wrongNum));
            ball_3.setText(String.valueOf(wrongNum2));
            ball_4.setText(String.valueOf(wrongNum3));
        }
        else if (ranSign == 2){
            result = 0;
            wrongNum = 0;
            wrongNum2 = 0;
            wrongNum3 = 0;
            result = num1 * num2;
            wrongNum = result + random.nextInt((5 - 2) + 1) + 2;
            wrongNum2 = result - random.nextInt((7 - 4) + 1) + 4;
            wrongNum3 = result + random.nextInt((3 + 2) + 1) + 2;
            questionBox.setText(String.valueOf(num1));
            questionBox2.setText("*");
            questionBox3.setText(String.valueOf(num2));
            ball_1.setText(String.valueOf(result));
            ball_2.setText(String.valueOf(wrongNum));
            ball_3.setText(String.valueOf(wrongNum2));
            ball_4.setText(String.valueOf(wrongNum3));
        }
        else if (ranSign == 3){
            result = 0;
            wrongNum = 0;
            wrongNum2 = 0;
            wrongNum3 = 0;
            result = num1 / num2;
            wrongNum = result + random.nextInt((5 - 2) + 1) + 2;
            wrongNum2 = result - random.nextInt((7 - 4) + 1) + 4;
            wrongNum3 = result + random.nextInt((3 + 2) + 1) + 2;
            questionBox.setText(String.valueOf(num1));
            questionBox2.setText("/");
            questionBox3.setText(String.valueOf(num2));
            ball_1.setText(String.valueOf(result));
            ball_2.setText(String.valueOf(wrongNum));
            ball_3.setText(String.valueOf(wrongNum2));
            ball_4.setText(String.valueOf(wrongNum3));
        }


    }

    public void gameOver(){
        timer.cancel();
        timer = null;
        start_flag = false;
        theme = preferences.getInt("theme_value", 0);
        if (theme == 0){
            mainLayout.setBackgroundResource(R.drawable.background_blue_with_cloud);
        }
        else if (theme == 1){
            mainLayout.setBackgroundResource(R.drawable.background_orange_with_cloud);
        }
        else if (theme == 2){
            mainLayout.setBackgroundResource(R.drawable.background_purple_with_cloud);
        }
        else if (theme == 3){
            mainLayout.setBackgroundResource(R.drawable.background_natural_with_cloud);
        }

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        linearLayout.setVisibility(View.VISIBLE);
        liveLabel.setVisibility(View.INVISIBLE);
        scoreLabel.setVisibility(View.INVISIBLE);
        questionBox3.setVisibility(View.INVISIBLE);
        questionBox.setVisibility(View.INVISIBLE);
        questionBox2.setVisibility(View.INVISIBLE);
        basket.setVisibility(View.INVISIBLE);
        ball_1.setVisibility(View.INVISIBLE);
        ball_2.setVisibility(View.INVISIBLE);
        ball_3.setVisibility(View.INVISIBLE);
        ball_4.setVisibility(View.INVISIBLE);

        //Update score
        if (score > highscore){
            highscore = score;
            highScoreLabel.setText("High Score: " + highscore);
            SharedPreferences.Editor editor = score_record.edit();
            editor.putInt("HIGH_SCORE", highscore);
            editor.commit();
        }
    }


    // Start game
    public void startGame(View view){
        question();
        start_flag = true;
        linearLayout.setVisibility(View.INVISIBLE);
        theme = preferences.getInt("theme_value", 0);
        if (theme == 0){
            mainLayout.setBackgroundResource(R.drawable.background_blue_with_cloud_game);
        }
        else if (theme == 1){
            mainLayout.setBackgroundResource(R.drawable.background_orange_with_cloud_game);
        }
        else if (theme == 2){
            mainLayout.setBackgroundResource(R.drawable.background_purple_with_cloud_game);
        }
        else if (theme == 3){
            mainLayout.setBackgroundResource(R.drawable.background_natural_with_cloud_game);
        }


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
        scoreLabel.setVisibility(View.VISIBLE);
        liveLabel.setVisibility(View.VISIBLE);
        basket.setVisibility(View.VISIBLE);
        ball_1.setVisibility(View.VISIBLE);
        ball_2.setVisibility(View.VISIBLE);
        ball_3.setVisibility(View.VISIBLE);
        ball_4.setVisibility(View.VISIBLE);

        live = 5;
        timeCount = 0;
        score = 0;
        scoreLabel.setText("Score: 0");
        liveLabel.setText("Live: " + live);

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

    public void settings(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void quitGame(View view){
        finish();
    }
}
