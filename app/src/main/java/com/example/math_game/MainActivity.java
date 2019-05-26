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
import java.util.concurrent.TimeUnit;

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
    private int speed1, speed2, speed3, speed4;

    // Score
    private TextView scoreLabel, liveLabel, highScoreLabel, ball_1, ball_2, ball_3, ball_4;

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
        liveLabel = findViewById(R.id.live);
        highScoreLabel = findViewById(R.id.high_Score);
        random = new Random();
    }

    public void changePos(){

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
            wrongNum = result + random.nextInt(5) + 1;
            wrongNum2 = result - random.nextInt(7) + 1;
            wrongNum3 = result + random.nextInt(2) + 1;
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
            wrongNum = result + random.nextInt(5) + 1;
            wrongNum2 = result - random.nextInt(7) + 1;
            wrongNum3 = result + random.nextInt(2) + 1;
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
            wrongNum = result + random.nextInt(5) + 1;
            wrongNum2 = result - random.nextInt(7) + 1;
            wrongNum3 = result + random.nextInt(2) + 1;
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
            wrongNum = result + random.nextInt(5) + 1;
            wrongNum2 = result - random.nextInt(7) + 1;
            wrongNum3 = result + random.nextInt(2) + 1;
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

        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        linearLayout.setVisibility(View.VISIBLE);
        liveLabel.setVisibility(View.INVISIBLE);
        scoreLabel.setVisibility(View.INVISIBLE);
        titleLabel.setVisibility(View.VISIBLE);
        questionBox3.setVisibility(View.INVISIBLE);
        questionBox.setVisibility(View.INVISIBLE);
        questionBox2.setVisibility(View.INVISIBLE);
        basket.setVisibility(View.INVISIBLE);
        ball_1.setVisibility(View.INVISIBLE);
        ball_2.setVisibility(View.INVISIBLE);
        ball_3.setVisibility(View.INVISIBLE);
        ball_4.setVisibility(View.INVISIBLE);

    }


    // Start game
    public void startGame(View view){
        question();
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

        speed1 = random.nextInt(15);
        speed2 = random.nextInt(15);
        speed3 = random.nextInt(15);
        speed4 = random.nextInt(15);

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

    public void quitGame(View view){

    }
}
