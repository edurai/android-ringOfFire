package edray.ringoffire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final String CARDRADIO = "CardRadio";
    public static final String RULERADIO = "RuleRadio";
    public Card card;
    public int cards;  // Number of types
    public int totalCs;  // Number of total cards
    public int minBreak;  // Number of cards that must be drawn before the ring is breakable
    public int maxBreak;  // Number of cards that can be drawn, the last one breaks the ring
    public double probRange = 1.0;
    public int randomNum;
    private int cardCounter = 0;
    private boolean broke = false;
    private String drinkBreak = "";
    public boolean cards32;
    public boolean defRule;
    public SharedPreferences spCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  // Screen is not supposed to be rotated
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        cards32 = intent.getExtras().getBoolean(CARDRADIO);
        defRule = intent.getExtras().getBoolean(RULERADIO);
        spCards = getSharedPreferences("SPRules", 0);

        // Checks options set by user for number of cards
        if (cards32){
            card = new Card(8);
            cards = 8;
            totalCs = 32;
            minBreak = 13;  // First possible break = 15
            maxBreak = 27;  // Maximum possible break = 28
        }
        else {
            card = new Card(13);
            cards = 13;
            totalCs = 52;
            minBreak = 23;  // First possible break = 25
            maxBreak = 47;  // Maximum possible break = 48
        }
    }

    public void draw(View v){
        TextView breakText = (TextView)findViewById(R.id.breakText);  // Text showing that the ring was broken
        TextView cardText = (TextView)findViewById(R.id.cardText);  // Text showing the rule of the card
        TextView debugArray = (TextView)findViewById(R.id.debugArray);
        TextView debugCounter = (TextView)findViewById(R.id.debugCounter);
        TextView debugRandom = (TextView)findViewById(R.id.debugRandom);
        TextView debugNewCard = (TextView)findViewById(R.id.debugNewCard);

            // Last card was drawn, end the game
            if (cardCounter == totalCs) {
                Intent intent = new Intent(this, GameOverActivity.class);
                startActivity(intent);
            }
            if (!broke && cardCounter > minBreak) {
                // If almost all cards are drawn and the ring still was not broken, break it
                if (cardCounter == maxBreak) {
                    broke = true;
                    drinkBreak = "Ring unterbrochen. Du musst exen!";
                }
                // If the random number is within the probability range, break the ring
                randomNum = randInt (1,100);
                for (int i = 1; i <= probRange; i++){
                    if (randomNum == i){
                        broke = true;
                        drinkBreak = "Ring unterbrochen, sauf!";
                    }
                }
                // Raise probability by 1 % or 0.5%
                if (cards32) {
                    probRange += 1;
                }
                else probRange += 0.5;
            }

        // Draw a random card
        int randomCard = randInt(0, cards-1);

        // Check if the card is still in the game (card.type[randomCard] > 0)
        int newCard = check(randomCard, card, cards, cardCounter, totalCs);

        // Render the texts to the display
        breakText.setText(drinkBreak);
        if (defRule){
            cardText.setText(card.effect(newCard));
        }
        else {
            cardText.setText(spCards.getString(Integer.toString(newCard), card.effect(newCard)));
        }

        // Recalculate (removing used cards from array)
        card.type[newCard] -= 1;
        cardCounter += 1;
        drinkBreak = "";

        debugArray.setText(String.valueOf(card.type[randomCard]));
        debugCounter.setText(String.valueOf(randomNum));
        debugNewCard.setText(String.valueOf(cardCounter));
        debugRandom.setText(String.valueOf(probRange));

    }

    // Function for checking if the drawn card is still available
    public int check(int randomCard, Card c, int cs, int cc, int totalCards){
            if (cc == totalCards) {
                return 0;
            }
            // Check the boundaries, if card not available then take the next higher one
            // If randomCard was the highest then start checking again with the lowest
            else if (c.type[randomCard] == 0) {
                int ranC = randomCard + 1;
                if (ranC == cs) {
                    return check(0, c, cs, cc, totalCards);
                }
                else return check(ranC, c, cs, cc, totalCards);
            }
            else return randomCard;
        }

    // Function for generating a random number between min and max (inclusive)
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return random;
    }
}
