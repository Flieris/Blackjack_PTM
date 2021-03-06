package com.example.lenni.blackjack_ptm;

import android.os.Bundle;

/**
 * Created by Sebastian Lenkiewicz on 06.08.2017.
 */

public class CardDeck {
   private Card deck[];
   private int card_index;
   private int card_dealt;
   public int deck_size;

   public void CreateDeck(int s){
      int first_card = R.drawable.zzzclubs_01;
      int next_card = first_card + 1;
      int card = 0;
      deck_size = 52*s;
      final int value[] = {11,2,3,4,5,6,7,8,9,10,10,10,10};

      deck = new Card[deck_size];

      for (int suit=0; suit< 4; suit++)
      {
         for (int rank=0;rank<13;rank++,card++)
         {
            // Set the value of the cards up.
            deck[card] = new Card();
            deck[card].setCard(first_card+card,value[rank]);
            deck[card].setNextPrev(card+1,card-1);
         }
      }

      deck[0].setPrev(-1);
      deck[deck_size-1].setNext(-1);

   }
   public void setDeckSize(int s){
      deck_size = 52*s;
   }
   public int getDeckSize(){
      return deck_size;
   }
   public void Shuffle(){
      int swap;
      for(int i = 0; i < deck_size;i++){
         swap = (int)(Math.random() * deck_size);
         if (swap != i){
            Remove(i);
            addAfter(swap,i);
         }
      }
      for(int i = 0; i < deck_size; i++){
         if(deck[i].getPrev() == -1){
            card_index = i;
            break;
         }
      }
      card_dealt = 0;
   }

   public void Remove(int card){

      if (deck[card].getPrev() != -1)
         deck[deck[card].getPrev()].setNext(deck[card].getNext());

      if (deck[card].getNext() != -1)
         deck[deck[card].getNext()].setPrev(deck[card].getPrev());
   }

   public void addAfter(int card, int new_card){
      if(deck[card].getNext() != -1)
         deck[deck[card].getNext()].setPrev(new_card);

      deck[new_card].setNextPrev(deck[card].getNext(),card);
      deck[card].setNext(new_card);
   }

   public int getNewCard(){
      int result = card_index;
      card_index = deck[card_index].getNext();
      card_dealt++;
      return result;
   }

   //probably could make it more android-like (like sharedpreferences or smth
   public void saveState(Bundle state){
      int[] prev_array = state.getIntArray("prev_list");
      int[] next_array = state.getIntArray("next_array");

      card_dealt = state.getInt("card_dealt");
      card_index = state.getInt("card_index");

      for(int count = 0; count < 52; count++)
         deck[count].setNextPrev(next_array[count],prev_array[count]);
   }

   public int getCardResource(int card){
      if (card > 52)
         card -= 52;
      return deck[card].getResource();
   }
   public boolean needShuffle(){
      return card_dealt > 42;
   }
   public int getCardValue(int card){
      return deck[card].getValue();
   }
}
