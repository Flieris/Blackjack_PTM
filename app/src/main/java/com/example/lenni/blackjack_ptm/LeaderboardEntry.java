package com.example.lenni.blackjack_ptm;

/**
 * Created by lenni on 27.08.2017.
 */

public class LeaderboardEntry {
   public String player_name;
   public int score;
   LeaderboardEntry(){

   }
   LeaderboardEntry(String name, int score){
      this.player_name = name;
      this.score = score;
   }

   public void setPlayerName(String name){
      this.player_name = name;
   }
   public String getPlayerName(){
      return this.player_name;
   }
   public void setScore(int score){
      this.score = score;
   }
   public int getScore(){
      return this.score;
   }
}
