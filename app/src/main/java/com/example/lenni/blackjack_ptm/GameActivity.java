package com.example.lenni.blackjack_ptm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;
import rjsv.floatingmenu.floatingmenubutton.subbutton.FloatingSubButton;

public class GameActivity extends AppCompatActivity {
   TextView user_text, dealer_text;
   CardDeck my_deck;
   ImageView player_image[], dealer_image[];
   FloatingSubButton hit_button, stand_button, surrender_button,bet_button;
   String player_name;
   int player_dealt, user_hand[], dealer_hand[], dealer_dealt;
   Integer hand_value, dealer_value;
   boolean in_win,in_lose, player_bust;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game);
      FloatingMenuButton floatingButton = (FloatingMenuButton) findViewById(R.id.action_pick_button);
      floatingButton.setAnchored(true);

      player_bust = false;
      in_win = false;
      in_lose = false;
      hand_value = 0;
      dealer_value = 0;
      user_hand = new int[5];
      dealer_hand = new int[5];
      user_text = (TextView)findViewById(R.id.player_score);
      dealer_text = (TextView)findViewById(R.id.dealer_score);

      player_image = new ImageView[5];
      player_image[0] = (ImageView) findViewById(R.id.player_1);
      player_image[1] = (ImageView) findViewById(R.id.player_2);
      player_image[2] = (ImageView) findViewById(R.id.player_3);
      player_image[3] = (ImageView) findViewById(R.id.player_4);
      player_image[4] = (ImageView) findViewById(R.id.player_5);

      dealer_image = new ImageView[5];
      dealer_image[0] = (ImageView) findViewById(R.id.dealer_1);
      dealer_image[1] = (ImageView) findViewById(R.id.dealer_2);
      dealer_image[2] = (ImageView) findViewById(R.id.dealer_3);
      dealer_image[3] = (ImageView) findViewById(R.id.dealer_4);
      dealer_image[4] = (ImageView) findViewById(R.id.dealer_5);

      GameStart();
      hit_button = (FloatingSubButton)findViewById(R.id.hit_action);
      hit_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            HitClick();
         }
      });
   }
   private void GameStart(){
      player_dealt = 0;
      dealer_dealt = 0;
      my_deck = new CardDeck();
      my_deck.CreateDeck();
      my_deck.Shuffle();
      DealerCall();
   }
   public void DealerCall(){
      int aces = 0;
      if(dealer_dealt < 5) {
         dealer_hand[dealer_dealt] = my_deck.getNewCard();
         dealer_image[dealer_dealt].setImageResource(my_deck.getCardResource(dealer_hand[dealer_dealt]));
         dealer_dealt++;
         for(int c = 0; c < dealer_dealt; c++){
            if(my_deck.getCardValue(dealer_hand[c]) == 11){
               aces++;
            }
            dealer_value += my_deck.getCardValue(dealer_hand[c]);
         }
         while(dealer_value > 21 && aces > 0){
            dealer_value -= 10;
            aces--;
         }
         String output = "Dealer's hand: " + dealer_value.toString();
         dealer_text.setText(output);
      }
   }
   private void HitClick(){
      int aces = 0;
      hand_value = 0;
      if (player_dealt < 5 && !player_bust){
         user_hand[player_dealt] = my_deck.getNewCard();
         player_image[player_dealt].setImageResource(my_deck.getCardResource(user_hand[player_dealt]));
         player_dealt++;

         for(int c = 0; c < player_dealt; c++){
            if(my_deck.getCardValue(user_hand[c]) == 11){
               aces++;
            }
            hand_value += my_deck.getCardValue(user_hand[c]);
         }
         while(hand_value > 21 && aces > 0){
             hand_value -= 10;
             aces--;
          }

         if (hand_value > 21){
            //ShowLose();
         }

         String output = "[Player] " + hand_value.toString();
         user_text.setText(output);
      }
   }


}
