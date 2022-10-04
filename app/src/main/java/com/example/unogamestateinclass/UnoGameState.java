package com.example.unogamestateinclass;

import java.util.ArrayList;

public class UnoGameState {

    private ArrayList<ArrayList<Card>> playerHands;
    private ArrayList<Card> playedCards;
    private ArrayList<Card> drawDeck;

    private ArrayList<Card> hand0;
    private ArrayList<Card> hand1;
    private ArrayList<Card> hand2;
    private ArrayList<Card> hand3;



    public UnoGameState() {
      playerHands.add(0, hand0);
      playerHands.add(1, hand1);
      playerHands.add(2, hand2);
      playerHands.add(3, hand3);

      playerHands.get(0).add(0, new Card(0,Card.CardColor.RED));
    }


    
    public enum PlayDirection
    {
        CW,CCW
    }
    // for each player
        // card in hand
            // color and number/type of card


    // Methods pertaining to the game state
        // Play a card
        // Draw a card
        // Shuffle Deck
        //

    public void placeCard(int playerID, Card card)
    {
        // get top card of played cards



        // remove card from player's hand


        // add to top of played cards

    }




}
