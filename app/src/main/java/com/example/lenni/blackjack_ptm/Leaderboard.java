package com.example.lenni.blackjack_ptm;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Leaderboard extends ListActivity {
   static List<LeaderboardEntry> entries;
   Button reset;
   LeaderBoardDbHelper db;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_leaderboard);
      reset = (Button)findViewById(R.id.reset_board);
      db = new LeaderBoardDbHelper(this);
      showBoard(db);
      reset.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            db.resetDb();
            showBoard(db);
         }
      });
   }

   public void showBoard(LeaderBoardDbHelper db){
      ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
      entries = db.getBoard();
      Collections.sort(entries, new Comparator<LeaderboardEntry>(){
         public int compare(LeaderboardEntry obj1, LeaderboardEntry obj2){
            return Integer.valueOf(obj2.getScore()).compareTo(obj1.getScore());
         }
      });
      System.out.println(entries);
      for (LeaderboardEntry val : entries) {

         // Writing values to map
         HashMap<String, String> map = new HashMap<String, String>();
         map.put("player",val.getPlayerName());
         map.put("score", String.valueOf(val.getScore()));

         System.out.println(map);
         Items.add(map);
      }
      SimpleAdapter adapter = new SimpleAdapter(this, Items,
              R.layout.entries,new String[] { "player", "score"},
              new int[] {R.id.label, R.id.score});
      this.setListAdapter(adapter);
   }
}
