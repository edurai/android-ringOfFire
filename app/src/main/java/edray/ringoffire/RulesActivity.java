package edray.ringoffire;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class RulesActivity extends AppCompatActivity {

    public final String CARDTYPE = "CardType";
    public int type;
    public SharedPreferences spCards;
    public String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        spCards = getSharedPreferences("SPRules", 0);
        test = spCards.getString("13", "0");

    }

    // Chose the right card to edit the rule
    public void setRule(View v){
        Intent intent = new Intent(this, SetRuleActivity.class);
        switch (v.getId()){
            case R.id.card2:
                type = 8;
                break;
            case R.id.card3:
                type = 9;
                break;
            case R.id.card4:
                type = 10;
                break;
            case R.id.card5:
                type = 11;
                break;
            case R.id.card6:
                type = 12;
                break;
            case R.id.card7:
                type = 0;
                break;
            case R.id.card8:
                type = 1;
                break;
            case R.id.card9:
                type = 2;
                break;
            case R.id.card10:
                type = 3;
                break;
            case R.id.cardJ:
                type = 4;
                break;
            case R.id.cardQ:
                type = 5;
                break;
            case R.id.cardK:
                type = 6;
                break;
            case R.id.cardA:
                type = 7;
                break;
        }
        intent.putExtra(CARDTYPE, type);
        startActivity(intent);
    }

   /* public void saveRules(View v){
        Intent intent = new Intent(this, SaveLoadRulesActivity.class);
        startActivity(intent);
    }*/

    public void back(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
