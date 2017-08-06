package com.example.lenni.blackjack_ptm;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   Button continue_last_game;
   Button show_leaderboards;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      Button start_new_game = (Button)findViewById(R.id.new_game);
      start_new_game.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Difficulty picked", Toast.LENGTH_LONG).show();
            DialogFragment newFragment = DifficultyDialog.newInstance();
            newFragment.show(getFragmentManager(),"DifficultyDialogTag");
         }
      });
   }



}
