package com.example.unogamestateinclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView gameText = findViewById(R.id.gameText);
        Button playButton = findViewById(R.id.playTurns);

        gameText.setMovementMethod(new ScrollingMovementMethod());

        playButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UnoGameState firstInstance = new UnoGameState();
                UnoGameState secondInstance = new UnoGameState(firstInstance, "Second Instance");
                UnoGameState thirdInstance = new UnoGameState(firstInstance, "Third Instance");

                gameText.append(firstInstance.toString());

                //Delays next move for 1 second to see separation of moves
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Ends current thread if interruptedexception
                }

                ArrayList<Card> firstPlayerHand = firstInstance.fetchPlayerHand(0);
                for(Card c : firstPlayerHand)
                {
                    // If card in foreach loop is successfully placed, don't place any more. End loop.
                    // Player id 0 will always refer to player #1. Player 1 places the first valid card from his hand.
                    // If card is a wildcard or +4, player 1 will always play a BLUE card.
                    if(firstInstance.placeCard(0, c))
                    {
                        break;
                    }
                }
                gameText.append(firstInstance.toString());

                //Delays next move for 1 second to see separation of moves
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Ends current thread if interruptedexception
                }

                int nextPlayerId = firstInstance.fetchCurrentPlayer();
                ArrayList<Card> nextPlayerHand = firstInstance.fetchPlayerHand(nextPlayerId);
                for(Card c : nextPlayerHand)
                {
                    if(firstInstance.placeCard(nextPlayerId, c))
                    {
                        break;
                    }
                }

                gameText.append(firstInstance.toString());

                //Delays next move for 1 second to see separation of moves
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Ends current thread if interrupted exception
                }

                nextPlayerId = firstInstance.fetchCurrentPlayer();
                nextPlayerHand = firstInstance.fetchPlayerHand(nextPlayerId);
                for(Card c : nextPlayerHand)
                {
                    if(firstInstance.placeCard(nextPlayerId, c))
                    {
                        break;
                    }
                }

                gameText.append(firstInstance.toString());

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Ends current thread if interrupted exception
                }

                gameText.append(secondInstance.toString());
                gameText.append(thirdInstance.toString());
            }
        });


    }


}

