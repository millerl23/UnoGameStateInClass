package com.example.unogamestateinclass;

public class Card {

    private int cardID; // 0-9 for ints, 10 = skip, 11 = reverse,
                        // 12 = +2, 13 = wild, 14 = wild+4
                        // Change to enum called Face
    private Color color;

    public Card(int ID, Color _color){
        cardID = ID;
        color = _color;
    }

    public enum Color
    {

        RED(0),BLUE(1),GREEN(2),YELLOW(3),BLACK(4);
        private int colorID;

        Color(int ID){
            this.colorID = ID;
        }

        public void setColorID(int colorID) {
            this.colorID = colorID;
        }


    }
/*
    public enum CardType
    {
        INT,REVERSE,PLUS2,SKIP,WILD,PLUS4WILD
    }
*/
}
