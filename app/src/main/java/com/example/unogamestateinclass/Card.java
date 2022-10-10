package com.example.unogamestateinclass;

public class Card {

    private int cardID; // 0-9 for ints, 10 = skip, 11 = reverse,
                        // 12 = +2, 13 = wild, 14 = wild+4
                        // Change to enum called Face
    private Color color;
    private Face face;

    public Card(Color _color, Face _face){
        color = _color;
        face = _face;
    }

    public enum Face //
    {
        ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,REVERSE,SKIP,DRAWTWO,DRAWFOUR,WILD

    }

    public enum Color
    {

        RED(0),BLUE(1),GREEN(2),YELLOW(3),BLACK(4);
       private int colorID;

        Color(int ID){
            this.colorID = ID;
        }
/*
        public void setColorID(int colorID) {
            this.colorID = colorID;
        }
*/

    }

    public Color getColor() {
        return color;
    }

    // this is used for wild and draw four cards.
    public void setColor(Color newColor) {
        color = newColor;
    }

    public Face getFace() {
        return face;
    }
/*
    public enum CardType
    {
        INT,REVERSE,PLUS2,SKIP,WILD,PLUS4WILD
    }
*/
    @Override
    public String toString(){
        String rtrn = this.color + " " + this.face;
        return rtrn;
    }
}
