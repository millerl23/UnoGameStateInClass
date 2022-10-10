package com.example.unogamestateinclass;

import android.media.metrics.PlaybackErrorEvent;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class UnoGameState
        implements View.OnClickListener{

    private int turn; //index of player whose turn it is
    private PlayDirection direction;

    private ArrayList<ArrayList<Card>> playerHands;
    private ArrayList<Card> playedCards;
    private ArrayList<Card> drawDeck;

    private ArrayList<Card> player1;
    private ArrayList<Card> player2;
    private ArrayList<Card> player3;
    private ArrayList<Card> player4;

    private TextView gameText;

    public UnoGameState(TextView _gameText) {
        // Initialize
        gameText = _gameText;
      //  turn = 0;
        direction = PlayDirection.CW;

        drawDeck = generateDeck();;
        playedCards = new ArrayList<Card>();
        playerHands = new ArrayList<ArrayList<Card>>();

        player1 = new ArrayList<Card>();
        player2 = new ArrayList<Card>();
        player3 = new ArrayList<Card>();
        player4 = new ArrayList<Card>();

        playerHands.add(0, player1);
        playerHands.add(1, player2);
        playerHands.add(2, player3);
        playerHands.add(3, player4);

        Collections.shuffle(drawDeck);

        initializePlayerHands();
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

    // Parameters
    // fromStack: the origin of the card that is moving
    // from: the card that is moving
    // to: the place the card is going to
    private void swapCards(ArrayList<Card> fromStack, Card from, ArrayList<Card> to)
    {
        to.add(from);
        fromStack.remove(from);
    }

    private void initializePlayerHands()
    {
        if(drawDeck.size() >= 7 * playerHands.size()) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < playerHands.size(); j++) {
                    drawCardFromDeck(playerHands.get(j), 1);
                }
            }
        }
    }

    private void drawCardFromDeck(ArrayList<Card> to, int n)
    {
        for (int i = 0; i < n; n++) {
            Card nextCard = drawDeck.get(0);
            to.add(nextCard);
            drawDeck.remove(nextCard);
        }
    }

    @Override
    public void onClick(View view) {
        gameText.setText("You pushed the button...");
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
