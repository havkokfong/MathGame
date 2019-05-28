package com.example.math_game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Settings extends AppCompatActivity {

    private int theme;
    private SharedPreferences preferences, object_preferences;
    private LinearLayout setting_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        preferences = getSharedPreferences("theme_value", MODE_PRIVATE);
        object_preferences = getSharedPreferences("object_value", MODE_PRIVATE);
        setting_layout = findViewById(R.id.setting_background);
    }

    @Override
    protected void onStart() {
        super.onStart();
        theme = preferences.getInt("theme_value", 0);
        if (theme == 0){
            setting_layout.setBackgroundResource(R.drawable.background_blue_with_cloud);
        }
        else if (theme == 1){
            setting_layout.setBackgroundResource(R.drawable.background_orange_with_cloud);
        }
        else if (theme == 2){
            setting_layout.setBackgroundResource(R.drawable.background_purple_with_cloud);
        }
        else if (theme == 3){
            setting_layout.setBackgroundResource(R.drawable.background_natural_with_cloud);
        }
    }

    public void blueColor(View view){
        setting_layout.setBackgroundResource(R.drawable.background_blue_with_cloud);
        preferences.edit().putInt("theme_value", 0).apply();
    }

    public void orangeColor(View view){
        setting_layout.setBackgroundResource(R.drawable.background_orange_with_cloud);
        preferences.edit().putInt("theme_value", 1).apply();
    }

    public void purpleColor(View view){
        setting_layout.setBackgroundResource(R.drawable.background_purple_with_cloud);
        preferences.edit().putInt("theme_value", 2).apply();
    }
    public void natureColor(View view){
        setting_layout.setBackgroundResource(R.drawable.background_natural_with_cloud);
        preferences.edit().putInt("theme_value", 3).apply();
    }

    public void backMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ballObject(View view){
        object_preferences.edit().putInt("object_value", 0).apply();
    }

    public void stoneObject(View view){
        object_preferences.edit().putInt("object_value", 1).apply();
    }

    public void bottleObject(View view){
        object_preferences.edit().putInt("object_value", 2).apply();
    }
}
