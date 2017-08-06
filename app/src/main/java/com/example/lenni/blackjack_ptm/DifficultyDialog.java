package com.example.lenni.blackjack_ptm;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.Toast;


/**
 * Created by Sebastian Lenkiewicz on 06.08.2017.
 */

public class DifficultyDialog extends DialogFragment {

   public DifficultyDialog(){}

   static DifficultyDialog newInstance(){
      return new DifficultyDialog();
   }

   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState){
      AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
      builder.setTitle(R.string.pick_difficulty)
            .setItems(R.array.difficulty_array, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                  Intent intent = new Intent(getActivity(),GameActivity.class);
                  startActivity(intent);
               }
            });
      return builder.create();
   }
}
