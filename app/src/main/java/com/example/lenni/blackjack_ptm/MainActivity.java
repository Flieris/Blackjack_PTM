package com.example.lenni.blackjack_ptm;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   Button continue_last_game;


   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Button start_new_game = (Button)findViewById(R.id.new_game);
      Button leaderboard_button = (Button)findViewById(R.id.leaderboard_button);

      start_new_game.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Difficulty picked", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
            startActivity(intent);
         }
      });

      leaderboard_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),Leaderboard.class);
            startActivity(intent);
         }
      });
   }



}
