package com.example.lenni.blackjack_ptm;

/**
 * Created by lenni on 19.08.2017.
 */

public class GameSettings {
   public String players_name;
   public int difficulty;

   GameSettings(){

   }
   GameSettings(String p, int d){
      players_name = p;
      difficulty = d;
   }
   GameSettings(String p){
      players_name = p;
      difficulty = 0;
   }
   GameSettings(int d){
      players_name = "PLAYER";
      difficulty = d;
   }

   public String getPlayerName(){
      return players_name;
   }
   public void setPlayerName(String p){
      players_name = p;
   }
   public int getDifficulty(){
      return difficulty;
   }
   public void setDifficulty(int d){
      difficulty = d;
   }
}
