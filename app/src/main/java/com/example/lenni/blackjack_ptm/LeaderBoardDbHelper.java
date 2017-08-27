package com.example.lenni.blackjack_ptm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lenni on 27.08.2017.
 */

public class LeaderBoardDbHelper extends SQLiteOpenHelper{
   public static final int DATABASE_VERSION = 1;
   public static final String DATABASE_NAME = "leaderboard.db";
   static final String COLUMN_SCORE = "Score";
   static final String COLUMN_PLAYER = "Name";
   static final String TABLE_LEADERBOARD = "Leaderboards";
   public LeaderBoardDbHelper(Context context){
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }
   public void onCreate(SQLiteDatabase db){

      db.execSQL("CREATE TABLE " + TABLE_LEADERBOARD + " (" + COLUMN_PLAYER+" Text ," +
                  COLUMN_SCORE + " INTEGER PRIMARY KEY)");
   }
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEADERBOARD);
      onCreate(db);
   }
   public void addEntry(LeaderboardEntry entry){
      SQLiteDatabase db  = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(COLUMN_PLAYER, entry.getPlayerName());
      values.put(COLUMN_SCORE, entry.getScore());

      db.insert(TABLE_LEADERBOARD,null,values);
      db.close();
   }

   public List<LeaderboardEntry> getBoard(){
      List<LeaderboardEntry> entries = new LinkedList<LeaderboardEntry>();


      String query ="SELECT * FROM " + TABLE_LEADERBOARD;
      SQLiteDatabase db = this.getWritableDatabase();
      Cursor cursor = db.rawQuery(query, null);

      LeaderboardEntry entry = null;
      if (cursor.moveToFirst()){
         do{
            entry = new LeaderboardEntry();
            entry.setPlayerName(cursor.getString(0));
            entry.setScore(Integer.parseInt(cursor.getString(1)));

            entries.add(entry);
         }while (cursor.moveToNext());
      }
      return entries;
   }
}