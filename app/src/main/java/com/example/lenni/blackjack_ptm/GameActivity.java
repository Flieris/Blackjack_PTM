package com.example.lenni.blackjack_ptm;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import rjsv.floatingmenu.floatingmenubutton.FloatingMenuButton;
import rjsv.floatingmenu.floatingmenubutton.subbutton.FloatingSubButton;

import static android.os.SystemClock.sleep;

public class GameActivity extends AppCompatActivity {
   TextView user_text, dealer_text, money_text;
   CardDeck my_deck;
   ImageView player_image[], dealer_image[];
   FloatingSubButton hit_button, stand_button, surrender_button;
   String player_name;
   int player_dealt, user_hand[], dealer_hand[], dealer_dealt, money, bet, difficulty;
   Integer hand_value, dealer_value;
   boolean in_win,in_lose, player_bust;
   GameSettings settings;
   LeaderboardEntry entry;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game);
      FloatingMenuButton floatingButton = (FloatingMenuButton) findViewById(R.id.action_pick_button);
      floatingButton.setAnchored(true);
      entry = new LeaderboardEntry();
      player_bust = false;
      in_win = false;
      in_lose = false;
      hand_value = 0;
      dealer_value = 0;
      user_hand = new int[5];
      dealer_hand = new int[5];
      user_text = (TextView)findViewById(R.id.player_score);
      dealer_text = (TextView)findViewById(R.id.dealer_score);
      money_text = (TextView)findViewById(R.id.player_money);
      money = 100;
      bet = 10;
      money_text.setText("Money = " + money + "$");
      Intent intent = getIntent();
      settings = intent.getParcelableExtra(SettingsActivity.SETTINGS);
      player_name = settings.getPlayerName();
      difficulty = settings.getDifficulty();
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
      stand_button = (FloatingSubButton)findViewById(R.id.stand_action);
      stand_button.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view){
            StandClick();
         }
      });

      surrender_button = (FloatingSubButton)findViewById(R.id.surrender_action);
      surrender_button.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view){
            SurrenderClick();
         }
      });

   }
   public void ResetEverything(){
      hand_value = 0;
      dealer_value = 0;
      for(int i = 0; i < 5; i++){
         player_image[i].setImageResource(R.drawable.back_blue);
         dealer_image[i].setImageResource(R.drawable.back_blue);
      }
      String player = settings.getPlayerName() +" hand " + hand_value.toString();
      user_text.setText(player);
      String dealer = "Dealer's hand " + dealer_value.toString();
      dealer_text.setText(dealer);
      money_text.setText("Money = " + money + "$");
      GameStart();
   }
   private void GameStart(){
      player_dealt = 0;
      dealer_dealt = 0;
      String player = settings.getPlayerName() +" hand " + hand_value.toString();
      user_text.setText(player);
      my_deck = new CardDeck();
      my_deck.CreateDeck(1);
      my_deck.Shuffle();
      DealerCall();
   }
   public void DealerCall(){
      int aces = 0;
      dealer_value = 0;
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
         while(dealer_value > 21 && aces > 0 && difficulty == 0){
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
            hand_value += my_deck.getCardValue(user_hand[c]);
            if(my_deck.getCardValue(user_hand[c]) == 11){
               aces++;
               if(difficulty == 1){
                  hand_value -= 10;
               }
            }
         }
        /* if (difficulty == 1){
            while(aces > 0){
               hand_value -= 10;
               aces--;
            }
         }*/
         while(hand_value > 21 && aces > 0 && difficulty == 0){
             hand_value -= 10;
             aces--;
          }

         if (hand_value > 21){
            ShowLose();
         }

         String output = settings.getPlayerName() + " hand: " + hand_value.toString();
         user_text.setText(output);
      }
   }
   private void StandClick(){
      do{
         DealerCall();
      } while (dealer_value < 17 && dealer_value <= hand_value && dealer_dealt < 5);

      if (dealer_value > 21){
         ShowWin();
      }
      else{
         CompareScore();
      }
      if(dealer_dealt >= 5){
         CompareScore();
      }
   }
   private void SurrenderClick(){
      AlertDialog surrender = new AlertDialog.Builder(this)
              .setTitle("Give up?")
              .setMessage("Do you want to give up on this game?")
              .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                    QuitGame();
                 }
              })
              .setNegativeButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                 }
              }).show();
   }
   private void CompareScore() {
      if (dealer_value > hand_value){
         ShowLose();
      } else if (hand_value > dealer_value){
         ShowWin();
      } else{
         AlertDialog draw = new AlertDialog.Builder(this)
                 .setTitle("Draw!")
                 .setMessage("Draw! Do you want to play another?")
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                       ResetEverything();
                    }
                 })
                 .setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       QuitGame();
                    }
                 }).show();
      }
   }

   private void ShowWin() {
      money += bet * 2;
      AlertDialog win = new AlertDialog.Builder(this)
              .setTitle("Round won!")
              .setMessage("You won this round! Do you want to play another?")
              .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                 public void onClick(DialogInterface dialog, int which) {
                    ResetEverything();
                 }
              })
              .setNeutralButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                    QuitGame();
                 }
              }).show();
   }


   public void ShowLose(){
      money -= bet*2;
      if (money > bet*2){
         AlertDialog lose = new AlertDialog.Builder(this)
                 .setTitle("Round lost!")
                 .setMessage("You lost this round! Do you want to play another?")
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which) {
                       ResetEverything();
                    }
                 })
                 .setNeutralButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       QuitGame();
                    }
                 }).show();
      }
   else{
      AlertDialog lose = new AlertDialog.Builder(this)
              .setTitle("Game over!")
              .setMessage("You lost this game! Want to play another?")
              .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                 public void onClick(DialogInterface dialog, int which) {
                    money = 100;
                    ResetEverything();
                 }
              })
              .setNeutralButton("No", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                    QuitGame();
                 }
              }).show();
      }

   }

   public void QuitGame(){
      entry.setPlayerName(settings.getPlayerName());
      entry.setScore(money);
      LeaderBoardDbHelper db = new LeaderBoardDbHelper(this);
      db.addEntry(entry);
      Intent quit = new Intent(getApplicationContext(),MainActivity.class);
      startActivity(quit);
   }

}
