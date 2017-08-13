package com.example.lenni.blackjack_ptm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;

public class GameActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game);
       FloatingMenuButton floatingButton = (FloatingMenuButton) findViewById(R.id.action_pick_button);
       floatingButton.setAnchored(true);
   }
}
