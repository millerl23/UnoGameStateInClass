package com.example.unogamestateinclass;

import android.media.metrics.PlaybackErrorEvent;

import java.util.ArrayList;
import java.util.Random;

public class UnoGameState {

    private int turn; //index of player whose turn it is
    private PlayDirection direction;

    private ArrayList<ArrayList<Card>> playerHands;
    private ArrayList<Card> playedCards;
    private ArrayList<Card> drawDeck;

    private ArrayList<Card> hand0;
    private ArrayList<Card> hand1;
    private ArrayList<Card> hand2;
    private ArrayList<Card> hand3;



    public UnoGameState() {
        // Initialize
      turn = 0;
      direction = PlayDirection.CW;
      drawDeck = generateDeck();


      playerHands.add(0, hand0);
      playerHands.add(1, hand1);
      playerHands.add(2, hand2);
      playerHands.add(3, hand3);

      //generateHand(playerHands.get(0));
     /* playerHands.get(0).add(0, new Card(0,Card.Color.RED));
        playerHands.get(0).add(0, new Card(5,Card.Color.BLUE));
        playerHands.get(0).add(0, new Card(10,Card.Color.GREEN));
        playerHands.get(0).add(0, new Card(11,Card.Color.RED));
        playerHands.get(0).add(0, new Card(14,Card.Color.BLACK));
        playerHands.get(0).add(0, new Card(6,Card.Color.YELLOW));
        playerHands.get(0).add(0, new Card(7,Card.Color.GREEN));
    */
    }



    private ArrayList<Card> generateDeck() // This will actually generate a card of each type of face
    {
        ArrayList<Card> cards = new ArrayList<>();
        for ( Card.Color c : Card.Color.values()){
            for ( Card.Face f : Card.Face.values()){
                if (c == Card.Color.BLACK) {
                    if(f == Card.Face.WILD || f == Card.Face.DRAWFOUR) {
                        for ( int i = 0; i < 4; i++) {
                            cards.add(new Card(c, f));
                        }
                    }
                }
                else {
                    cards.add(new Card(c, f));
                    if ( f!= Card.Face.ZERO){
                        cards.add(new Card(c, f));
                    }
                }
            }
        }
        return cards;
    }

    private ArrayList<Card> generatePlayerHands()
    {
        Random numberOfCards = new Random();

        if(drawDeck.size() >= 7 * playerHands.size())
        {
            for(int i=0; i < 7; i++)
            {
                for(int j=0; j < playerHands.size(); j++)
                {
                    drawCardFromDeck(playerHands.get(j));
                }
            }
        }
    }

    private void drawCardFromDeck(ArrayList<Card> to)
    {
        Card nextCard = drawDeck.get(0);
        to.add(nextCard);
        drawDeck.remove(nextCard);
    }
    public enum PlayDirection
    {
        CW (1), CCW (-1);

        private int direction;

        PlayDirection(int direction) {
            this.direction = direction;
        }
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
