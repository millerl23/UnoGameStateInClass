package com.example.unogamestateinclass;

import android.media.metrics.PlaybackErrorEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
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



    public UnoGameState() {
        // Initialize
        turn = 0;
        direction = PlayDirection.CW;

        drawDeck = generateDeck();
        playedCards = createPlayedCardsDeck(drawDeck);
        playerHands = new ArrayList<ArrayList<Card>>();

        player1 = new ArrayList<Card>();
        player2 = new ArrayList<Card>();
        player3 = new ArrayList<Card>();
        player4 = new ArrayList<Card>();

        playerHands.add(0, player1);
        playerHands.add(1, player2);
        playerHands.add(2, player3);
        playerHands.add(3, player4);

        shuffleDeck(drawDeck);

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

    public UnoGameState(UnoGameState previous)
    {
        gameText = previous.gameText;
        turn = previous.turn;
        direction = previous.direction;
        drawDeck = new ArrayList<Card>();
        for(Card c : previous.drawDeck)
        {
            Card.Face face = c.getFace();
            Card.Color color = c.getColor();
            drawDeck.add(new Card(color, face));
        }
        playedCards = new ArrayList<Card>();
        for(Card c : previous.playedCards)
        {
            Card.Face face = c.getFace();
            Card.Color color = c.getColor();
            playedCards.add(new Card(color, face));
        }
        playerHands = new ArrayList<>();
        player1 = new ArrayList<>();
        player2 = new ArrayList<>();
        player3 = new ArrayList<>();
        player4 = new ArrayList<>();
        for(int i=0; i < previous.playerHands.size(); i++)
        {
            ArrayList<Card> newHand = previous.playerHands.get(i);

            for (Card c : newHand)
            {
                Card.Face face = c.getFace();
                Card.Color color = c.getColor();
                switch(i){
                    case 0:
                        player1.add(new Card(color, face));
                        break;
                    case 1:
                        player2.add(new Card(color, face));
                        break;
                    case 2:
                        player3.add(new Card(color, face));
                        break;
                    case 3:
                        player4.add(new Card(color, face));
                        break;
                }
            }

            playerHands.add(player1);
            playerHands.add(player2);
            playerHands.add(player3);
            playerHands.add(player4);
        }
    }


    private void shuffleDeck(ArrayList<Card> deck) {
        Collections.shuffle(deck);
    }

    private ArrayList<Card> createPlayedCardsDeck(ArrayList<Card> drawDeck) {
        Card firstCard = drawDeck.get(0);
        drawDeck.remove(0);

        ArrayList<Card> playedCardsDeck = new ArrayList<>();
        playedCardsDeck.add(firstCard);

        return playedCardsDeck;
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

    public boolean checkVictory (ArrayList<ArrayList<Card>> playerHands) {
        if (playerHands.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkDrawEmpty (ArrayList<Card> drawDeck) {
        if (drawDeck.size() == 0) {
            for (Card c : playedCards) {
                drawDeck.add(c);
            }
            Collections.shuffle(drawDeck);
            return true;
        }
        else {
            return false;
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


    public enum PlayDirection
    {
        CW (1), CCW (-1);

        private int value;

        PlayDirection(int value) {
            this.value = value;
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


    public boolean checkCardValidity(Card card) {

        if (card.getFace() == Card.Face.WILD || card.getFace() == Card.Face.DRAWFOUR) {
            return true;
        }

        Card playedCardsTop = playedCards.get(0);

        if (playedCardsTop.getFace() == card.getFace() ||
            playedCardsTop.getColor() == card.getColor()) {
            return true;
        }

        return false;
    }


    /**
     * Checks card validity. If valid, removes card from player hand,
     * executes special card functionalities, places card into placedCards,
     * then increments turn.
     *
     * @param playerID index of player whose placing card
     * @param card the actual card object being placed
     *
     * @return true if card is valid, false otherwise
     */
    public boolean placeCard(int playerID, Card card)
    {
        boolean cardValidity = checkCardValidity(card);
        if (!cardValidity) {
            return false;
            // ends function, rest of code doesn't run
        }

        playerHands.get(playerID).remove(card);

        int nextPlayerID;

        switch (card.getFace()) {

            case SKIP:
                turn += direction.value;
                turn %= playerHands.size();
                break;

            case REVERSE:
                if (direction == PlayDirection.CCW) {
                    direction = PlayDirection.CW;
                } else {
                    direction = PlayDirection.CCW;
                }

                break;

            case DRAWTWO:
                nextPlayerID = (playerID + 1) % playerHands.size();
                drawCardFromDeck(playerHands.get(nextPlayerID), 2);

                turn += direction.value;
                turn %= playerHands.size();
                break;

            case DRAWFOUR:
                nextPlayerID = (playerID + 1) % playerHands.size();
                drawCardFromDeck(playerHands.get(nextPlayerID), 4);

                turn += direction.value;
                turn %= playerHands.size();

                // can change this based on demonstration
                card.setColor(Card.Color.BLUE);
                break;

            case WILD:

                // same thing here
                card.setColor(Card.Color.BLUE);
                break;
        }

        playedCards.add(card);

        turn += direction.value;
        turn %= playerHands.size();

        return true;
    }

    @NonNull
    @Override
    public String toString()
    {
        String rtrn = new String("The contents of the draw deck are: ");
        for ( Card c : this.drawDeck ) {
            rtrn += c.toString() + ", ";
        }

        rtrn += "\nThe contents of the played cards deck are: ";
        for ( Card c : this.playedCards){
            rtrn += c.toString() + ", ";
        }

        rtrn += "\nThe play direction is: ";
        switch (this.direction) {
            case CW: rtrn += "Clockwise.\n";
                break;
            case CCW: rtrn += "Counterclockwise.\n";
                break;
            default: rtrn += "Invalid play direction detected...\n";
        }

        rtrn += "The player whose turn it is: ";
        switch (this.turn) {
            case 0: rtrn += "Player1\n";
                break;
            case 1: rtrn += "Player2\n";
                break;
            case 2: rtrn += "Player3\n";
                break;
            case 3: rtrn += "Player4\n";
                break;
            default: rtrn += "Invalid turn detected...\n";
        }

        for ( ArrayList<Card> hand : playerHands ){
            switch (playerHands.indexOf(hand)){
                case 0: rtrn += "Player1's hand consists of: ";
                    break;
                case 1: rtrn += "Player2's hand consists of: ";
                    break;
                case 2: rtrn += "Player3's hand consists of: ";
                    break;
                case 3: rtrn += "Player4's hand consists of: ";
                    break;
                default: rtrn += "Invalid player detected...\n";
            }
            for ( Card c : hand ){
                rtrn += c.toString() + ", ";
            }
        }
        return rtrn;
    }




}
