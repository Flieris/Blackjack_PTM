package com.example.lenni.blackjack_ptm;

/**
 * Created by Sebastian Lenkiewicz on 06.08.2017.
 */

public class Card {
   private int m_resource;
   private int m_value;
   private int m_next;
   private int m_prev;

   public void setCard(int resource, int value){
      m_resource = resource;
      m_value = value;
   }

   public int getValue(){
      return m_value;
   }

   public int getResource(){
      return m_resource;
   }

   public void setNextPrev(int next, int prev){
      m_next = next;
      m_prev = prev;
   }

   public int getNext(){
      return m_next;
   }

   public void setNext(int next){
      m_next = next;
   }

   public int getPrev(){
      return m_prev;
   }

   public void setPrev(int prev){
      m_prev = prev;
   }
}
