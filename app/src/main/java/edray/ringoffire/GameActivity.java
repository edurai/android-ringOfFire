package edray.ringoffire;

import android.content.Intent;
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
    public int cards;
    private int cardCounter = 1;
    private boolean broke = false;
    private int[] ringBreak = new int[20];
    private String drinkBreak = "";
    public boolean cards32;
    public boolean defRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        cards32 = intent.getExtras().getBoolean(CARDRADIO);
        defRule = intent.getExtras().getBoolean(RULERADIO);
        if (cards32){
            card = new Card(8);
            cards = 8;
        }
        else {
            card = new Card(13);
            cards = 13;
        }
    }

    public void draw(View v){
        TextView breakText = (TextView)findViewById(R.id.breakText);
        TextView cardText = (TextView)findViewById(R.id.cardText);
        TextView debugArray = (TextView)findViewById(R.id.debugArray);
        TextView debugCounter = (TextView)findViewById(R.id.debugCounter);
        TextView debugRandom = (TextView)findViewById(R.id.debugRandom);
        TextView debugNewCard = (TextView)findViewById(R.id.debugNewCard);

        if (cards32) {
        if (cardCounter == 33) {
            Intent intent = new Intent(this, GameOverActivity.class);
            startActivity(intent);
        }
        else if (!broke && cardCounter > 4) {
            if (cardCounter == 25) {
                broke = true;
                drinkBreak = "Ring unterbrochen. Du musst exen!";
            }
            for (int i = 0; i < cardCounter - 4; i++){
                int randomNum = randInt(0, 19);
                ringBreak[i] = randomNum;
            }
            int randBreak = randInt(20, 39);
            for (int i = 0; i < cardCounter - 4; i++) {
                if ((randBreak - 20) == ringBreak[i]){
                    broke = true;
                    drinkBreak = "Ring unterbrochen, sauf!";
                }
            }
        }}

        else {
            if (cardCounter == 53) {
                Intent intent = new Intent(this, GameOverActivity.class);
                startActivity(intent);
            }
            else if (!broke && cardCounter > 8) {
                if (cardCounter == 45) {
                    broke = true;
                    drinkBreak = "Ring unterbrochen. Du musst exen!";
                }
                for (int i = 0; i < cardCounter - 8; i++){
                    int randomNum = randInt(0, 19);
                    ringBreak[i] = randomNum;
                }
                int randBreak = randInt(20, 39);
                for (int i = 0; i < cardCounter - 8; i++) {
                    if ((randBreak - 20) == ringBreak[i]){
                        broke = true;
                        drinkBreak = "Ring unterbrochen, sauf!";
                    }
                }
            }
        }
        int randomCard = randInt(0, cards-1);
        int newCard = check(randomCard, card, cards, cardCounter);
        String effect = card.effect(newCard);
        breakText.setText(drinkBreak);
        cardText.setText(effect);
        card.type[newCard] -= 1;
        cardCounter += 1;
        drinkBreak = "";
        debugArray.setText(String.valueOf(card.type[randomCard]));
        debugCounter.setText(String.valueOf(card.type[newCard]));
        debugNewCard.setText(String.valueOf(cardCounter));

    }

    public int check(int randomCard, Card c, int cs, int cc) {
        if (cards32) {
            if (cc == 33) {
                return 0;
            }
            else if (c.type[randomCard] == 0) {
                int ranC = randomCard + 1;
                if (ranC == cs) {
                    return check(0, c, cs, cc);
                }
                else return check(ranC, c, cs, cc);
            }
            else return randomCard;
        }
        else if (cc == 53) {
            return 0;
        }
        else if (c.type[randomCard] == 0) {
            int ranC = randomCard + 1;
            if (ranC == cs) {
                return check(0, c, cs, cc);
            } else return check(ranC, c, cs, cc);
        }
        else return randomCard;
    }


    public static int randInt(int min, int max) {
        Random rand = new Random();
        int random = rand.nextInt((max - min) + 1) + min;
        return random;
    }
}
