package com.example.lenni.blackjack_ptm;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lenni on 19.08.2017.
 */

public class GameSettings implements Parcelable {
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

   protected GameSettings(Parcel in) {
      players_name = in.readString();
      difficulty = in.readInt();
   }
   @Override
   public String toString(){
      return players_name;
   }
   public static final Creator<GameSettings> CREATOR = new Creator<GameSettings>() {
      @Override
      public GameSettings createFromParcel(Parcel in) {
         return new GameSettings(in);
      }

      @Override
      public GameSettings[] newArray(int size) {
         return new GameSettings[size];
      }
   };

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

   @Override
   public int describeContents() {
      return 0;
   }

   @Override
   public void writeToParcel(Parcel parcel, int i) {
      parcel.writeString(players_name);
      parcel.writeInt(difficulty);
   }
}
