package com.example.lenni.blackjack_ptm;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leaderboard extends ListActivity {
   static List<LeaderboardEntry> entries;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_leaderboard);

      LeaderBoardDbHelper db = new LeaderBoardDbHelper(this);
      ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
      entries = db.getBoard();

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
