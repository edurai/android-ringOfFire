package edray.ringoffire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
        if (cards32) {
            card = new Card(8);
            cards = 8;
            totalCs = 32;
            minBreak = 13;  // First possible break = 15
            maxBreak = 27;  // Maximum possible break = 28
        } else {
            card = new Card(13);
            cards = 13;
            totalCs = 52;
            minBreak = 23;  // First possible break = 25
            maxBreak = 47;  // Maximum possible break = 48
        }
    }

    public void draw(View v) {
        TextView breakText = (TextView) findViewById(R.id.breakText);  // Text showing that the ring was broken
        TextView cardText = (TextView) findViewById(R.id.cardText);  // Text showing the rule of the card
        ImageView cardImage = (ImageView) findViewById(R.id.imageView);

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
            randomNum = randInt(1, 100);
            for (int i = 1; i <= probRange; i++) {
                if (randomNum == i) {
                    broke = true;
                    drinkBreak = "Ring unterbrochen, sauf!";
                }
            }
            // Raise probability by 1 % or 0.5%
            if (cards32) {
                probRange += 1;
            } else probRange += 0.5;
        }

        // Draw a random card
        int randomCard = randInt(0, cards - 1);

        // Check if the card is still in the game (card.type[randomCard] > 0)
        int newCard = check(randomCard, card, cards, cardCounter, totalCs);

        // Chose randomly which value the cards gets (heart, spades, check, cross)
        int randValue = randInt(0,3);
        int cardValue = whichOne(card, newCard, randValue, cardCounter, totalCs);

        // Render the texts to the display
        breakText.setText(drinkBreak);
        if (defRule) {
            cardText.setText(card.effect(newCard));
        } else {
            cardText.setText(spCards.getString(Integer.toString(newCard), card.effect(newCard)));
        }

        // Recalculate (removing used cards from array)
        card.type[newCard][4] -= 1;
        cardCounter += 1;
        drinkBreak = "";

        // Set the image depending on the calculated card values
        // TODO: There might be a better way to do this
        if(cardValue == 0) {
            switch (newCard) {
                case 0:
                    cardImage.setImageResource(R.mipmap.herz7);
                    break;
                case 1:
                    cardImage.setImageResource(R.mipmap.herz8);
                    break;
                case 2:
                    cardImage.setImageResource(R.mipmap.herz9);
                    break;
                case 3:
                    cardImage.setImageResource(R.mipmap.herz10);
                    break;
                case 4:
                    cardImage.setImageResource(R.mipmap.herzbube);
                    break;
                case 5:
                    cardImage.setImageResource(R.mipmap.herzdame);
                    break;
                case 6:
                    cardImage.setImageResource(R.mipmap.herzkoenig);
                    break;
                case 7:
                    cardImage.setImageResource(R.mipmap.herzass);
                    break;
                case 8:
                    cardImage.setImageResource(R.mipmap.herz2);
                    break;
                case 9:
                    cardImage.setImageResource(R.mipmap.herz3);
                    break;
                case 10:
                    cardImage.setImageResource(R.mipmap.herz4);
                    break;
                case 11:
                    cardImage.setImageResource(R.mipmap.herz5);
                    break;
                case 12:
                    cardImage.setImageResource(R.mipmap.herz6);
                    break;
            }
        }
        else if (cardValue == 1) {
            switch (newCard) {
                case 0:
                    cardImage.setImageResource(R.mipmap.karo7);
                    break;
                case 1:
                    cardImage.setImageResource(R.mipmap.karo8);
                    break;
                case 2:
                    cardImage.setImageResource(R.mipmap.karo9);
                    break;
                case 3:
                    cardImage.setImageResource(R.mipmap.karo10);
                    break;
                case 4:
                    cardImage.setImageResource(R.mipmap.karobube);
                    break;
                case 5:
                    cardImage.setImageResource(R.mipmap.karodame);
                    break;
                case 6:
                    cardImage.setImageResource(R.mipmap.karokoenig);
                    break;
                case 7:
                    cardImage.setImageResource(R.mipmap.karoass);
                    break;
                case 8:
                    cardImage.setImageResource(R.mipmap.karo2);
                    break;
                case 9:
                    cardImage.setImageResource(R.mipmap.karo3);
                    break;
                case 10:
                    cardImage.setImageResource(R.mipmap.karo4);
                    break;
                case 11:
                    cardImage.setImageResource(R.mipmap.karo5);
                    break;
                case 12:
                    cardImage.setImageResource(R.mipmap.karo6);
                    break;
            }
        }
        else if (cardValue == 2) {
            switch (newCard) {
                case 0:
                    cardImage.setImageResource(R.mipmap.kreuz7);
                    break;
                case 1:
                    cardImage.setImageResource(R.mipmap.kreuz8);
                    break;
                case 2:
                    cardImage.setImageResource(R.mipmap.kreuz9);
                    break;
                case 3:
                    cardImage.setImageResource(R.mipmap.kreuz10);
                    break;
                case 4:
                    cardImage.setImageResource(R.mipmap.kreuzbube);
                    break;
                case 5:
                    cardImage.setImageResource(R.mipmap.kreuzdame);
                    break;
                case 6:
                    cardImage.setImageResource(R.mipmap.kreuzkoenig);
                    break;
                case 7:
                    cardImage.setImageResource(R.mipmap.kreuzass);
                    break;
                case 8:
                    cardImage.setImageResource(R.mipmap.kreuz2);
                    break;
                case 9:
                    cardImage.setImageResource(R.mipmap.kreuz3);
                    break;
                case 10:
                    cardImage.setImageResource(R.mipmap.kreuz4);
                    break;
                case 11:
                    cardImage.setImageResource(R.mipmap.kreuz5);
                    break;
                case 12:
                    cardImage.setImageResource(R.mipmap.kreuz6);
                    break;
            }
        }
        else if (cardValue == 3) {
            switch (newCard) {
                case 0:
                    cardImage.setImageResource(R.mipmap.pik7);
                    break;
                case 1:
                    cardImage.setImageResource(R.mipmap.pik8);
                    break;
                case 2:
                    cardImage.setImageResource(R.mipmap.pik9);
                    break;
                case 3:
                    cardImage.setImageResource(R.mipmap.pik10);
                    break;
                case 4:
                    cardImage.setImageResource(R.mipmap.pikbube);
                    break;
                case 5:
                    cardImage.setImageResource(R.mipmap.pikdame);
                    break;
                case 6:
                    cardImage.setImageResource(R.mipmap.pikkoenig);
                    break;
                case 7:
                    cardImage.setImageResource(R.mipmap.pikass);
                    break;
                case 8:
                    cardImage.setImageResource(R.mipmap.pik2);
                    break;
                case 9:
                    cardImage.setImageResource(R.mipmap.pik3);
                    break;
                case 10:
                    cardImage.setImageResource(R.mipmap.pik4);
                    break;
                case 11:
                    cardImage.setImageResource(R.mipmap.pik5);
                    break;
                case 12:
                    cardImage.setImageResource(R.mipmap.pik6);
                    break;
            }
        }
    }

    // Function for checking if the drawn card is still available
    public int check(int randomCard, Card c, int cs, int cc, int totalCards) {
        if (cc == totalCards) {
            return 0;
        }
        // Check the boundaries, if card not available then take the next higher one
        // If randomCard was the highest then start checking again with the lowest
        else if (c.type[randomCard][4] == 0) {
            int ranC = randomCard + 3;
            return check((ranC % cs), c, cs, cc, totalCards);
        } else return randomCard;
    }

    // Function for generating a random number between min and max (inclusive)
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return random;
    }

    // Function for checking if the value of drawn card is still available
    public int whichOne(Card c, int newC, int rand, int cc, int tc) {
        if (cc == tc){
            return 0;
        }
        else if (c.type[newC][rand] == 0) {
            rand += 3;
            return whichOne(c, newC, (rand % 4), cc, tc);
        }
        else {
            c.type[newC][rand] = 0;
            return rand;
        }
    }
}
