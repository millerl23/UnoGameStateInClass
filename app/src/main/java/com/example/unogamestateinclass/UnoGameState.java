package com.example.unogamestateinclass;

import android.media.metrics.PlaybackErrorEvent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class UnoGameState {

    private int turn; //index of player whose turn it is
    private PlayDirection direction;

    private ArrayList<ArrayList<Card>> playerHands;
    private ArrayList<Card> playedCards;
    private ArrayList<Card> drawDeck;

    private String latestAction;

    private static int instanceCount = 1;
    private int instanceNumber;

    public UnoGameState() {
        // Initialize
        turn = 0;
        direction = PlayDirection.CW;

        drawDeck = generateDeck();
        playerHands = new ArrayList<ArrayList<Card>>();

        for (int i = 0; i < 4; i++) {
            playerHands.add(i, new ArrayList<Card>());
        }

        shuffleDeck(drawDeck);
        initializePlayerHands();
        playedCards = createPlayedCardsDeck(drawDeck);

        instanceNumber = instanceCount;
        instanceCount += 1;
        instanceCount %= 3; // resets the count when "play turn" button is clicked
                            // multiple times

        Log.i(instanceCount+"", "");

        latestAction = "The game state was initialized. Player hands were randomly generated from shuffled deck.";

    }

    public UnoGameState(UnoGameState previous)
    {
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

        for (int i = 0; i < 4; i++) {
            playerHands.add(i, new ArrayList<Card>());
        }

        for(int i=0; i < previous.playerHands.size(); i++)
        {
            ArrayList<Card> newHand = previous.playerHands.get(i);

            for (Card c : newHand)
            {
                Card.Face face = c.getFace();
                Card.Color color = c.getColor();

                playerHands.get(i).add(new Card(color, face));
            }
        }

        instanceNumber = instanceCount;
        instanceCount += 1;

        latestAction = "The game state was copied from copy constructor.\n";
    }


    private void shuffleDeck(ArrayList<Card> deck) {
        Collections.shuffle(deck, new Random(1234));
    }

    private ArrayList<Card> createPlayedCardsDeck(ArrayList<Card> drawDeck) {
        Card firstCard = drawDeck.get(0);
        ArrayList<Card> playedCardsDeck = new ArrayList<>();
        playedCardsDeck.add(firstCard);
        drawDeck.remove(0);

        return playedCardsDeck;
    }

    // Generate deck of: 4 of each black card, 2 of each colored card
    // but only 1 zero of each colored card
    private ArrayList<Card> generateDeck()
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
                else if (f != Card.Face.WILD && f != Card.Face.DRAWFOUR)
                {
                    cards.add(new Card(c, f));
                    if (f != Card.Face.ZERO) cards.add(new Card(c, f));
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

    private boolean initializePlayerHands()
    {
        if(drawDeck.size() >= 7 * playerHands.size()) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < playerHands.size(); j++) {
                    drawCardFromDeck(playerHands.get(j), 1);
                }
            }
            return true;
        }
        return false;
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
            drawDeck.addAll(playedCards);
            Collections.shuffle(drawDeck);
            return true;
        }
        else {
            return false;
        }
    }

    private void drawCardFromDeck(ArrayList<Card> to, int n)
    {
        for (int i = 0; i < n; i++) {
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

        if (playedCardsTop == null)
        {
            return false;
        }
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
            latestAction = "Placed card was not valid!\n";
            return false;
            // ends function, rest of code doesn't run
        }



        int nextPlayerID;
        Card.Face face = card.getFace();

        switch (face) {

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
                turn += direction.value;
                turn %= playerHands.size();

                drawCardFromDeck(playerHands.get(turn), 2);
                break;

            case DRAWFOUR:
                turn += direction.value;
                turn %= playerHands.size();

                drawCardFromDeck(playerHands.get(turn), 4);

                // can change this based on demonstration
                card.setColor(Card.Color.BLUE);
                break;

            case WILD:

                // same thing here
                card.setColor(Card.Color.BLUE);
                break;
        }

        Card.Color color = card.getColor(); // we don't get color until here (for latestAction print)
                                            // because it may have changed during special action execution
        playedCards.add(card);
        playerHands.get(playerID).remove(card);

        turn += direction.value;
        turn %= playerHands.size();

        // Player's numerical value (1-4) is different from their ID's numerical value.
        // Maybe amend this by making player #0 null so that player 1's player id = 1?
        // Otherwise, any time we refer to a player id, we add one to translate that to what the frontfacing view knows as the player values,
        // hence why we do playerId + 1 and turn + 1.
        latestAction = "Player " + String.valueOf(playerID + 1) + " played card " + card.toString() + ". Turn goes to player " + String.valueOf(turn + 1);
        return true;
    }

    @NonNull
    @Override
    public String toString()
    {
        // Initialize return string with instance
        StringBuilder rtrn = new StringBuilder(new String(
                "\nThis is instance #" + instanceNumber) +
                ". The contents of the draw deck are: \n");

        // Add contents of drawDeck to return string
        for ( Card c : this.drawDeck ) {
            if(c != drawDeck.get(drawDeck.size()-1))
            {
                rtrn.append(c.toString()).append(", ");
            } else rtrn.append(c.toString()).append(".\n");

        }

        // Add latest action
        rtrn.append("\n").append(latestAction).append("\n\n");

        // Add played cards
        rtrn.append("The contents of the played cards deck are:");
        for ( Card c : this.playedCards){
            rtrn.append(c.toString()).append(", ");
        }

        // Add play direction
        rtrn.append("\nThe play direction is:");
        switch (this.direction) {
            case CW: rtrn.append("Clockwise.\n");
                break;
            case CCW: rtrn.append("Counterclockwise.\n");
                break;
            default: rtrn.append("Invalid play direction detected...\n");
        }

        // Add next player's turn
        rtrn.append("The player whose turn it is next: ");
        rtrn.append("Player").append(this.turn + 1).append("\n");

        // Add all players' hands
        for ( ArrayList<Card> hand : playerHands ){
            rtrn.append("Player");
            rtrn.append(playerHands.indexOf(hand) + 1);
            rtrn.append("'s hand consists of: ");

            for ( Card c : hand ){
                rtrn.append(c.toString()).append(", ");
            }
            rtrn.append("\n");
        }

        // Occurrence separator
        rtrn.append("----------Next Turn----------\n");
        return rtrn.toString();
    }

    public ArrayList<Card> fetchPlayerHand(int id)
    {
        ArrayList<Card> hand = playerHands.get(id);
        return hand;
    }

    public int fetchCurrentPlayer()
    {
        return turn % 4;
    }


}
