package com.example.lenni.blackjack_ptm;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {
   private int difficulty;
   private String player_name;
   public static final String SETTINGS = "Settings";
   public GameSettings settings;
   private RadioButton normal,hard;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_settings);
      settings = new GameSettings();
      normal = (RadioButton)findViewById(R.id.normal_difficulty);
      hard = (RadioButton)findViewById(R.id.different_difficulty);
      Button start_button = (Button)findViewById(R.id.start_game);
      start_button.setOnClickListener(new View.OnClickListener() {
            @Override
         public void onClick(View view) {
            player_name = ((EditText)findViewById(R.id.player_et)).getText().toString();
            settings.setPlayerName(player_name);
            if(normal.isChecked())difficulty = 0;
            if(hard.isChecked())difficulty = 1;
            settings.setDifficulty(difficulty);
            Intent intent = new Intent(getApplicationContext(),GameActivity.class);
            intent.putExtra(SETTINGS,settings);
            startActivity(intent);
         }
      });
   }


   protected void onActivityResult(){
      finish();
   }
}
