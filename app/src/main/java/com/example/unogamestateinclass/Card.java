package com.example.unogamestateinclass;

public class Card {

    private int cardID; // 0-9 for ints, 10 = skip, 11 = reverse,
                        // 12 = +2, 13 = wild, 14 = wild+4
    private CardColor color;

    public Card(int ID, CardColor _color){
        cardID = ID;
        color = _color;
    }

    public enum CardColor
    {
        RED,BLUE,GREEN,YELLOW,BLACK
    }
/*
    public enum CardType
    {
        INT,REVERSE,PLUS2,SKIP,WILD,PLUS4WILD
    }
*/
}
