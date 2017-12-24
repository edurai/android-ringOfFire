package edray.ringoffire;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

public class SetRuleActivity extends AppCompatActivity {

    public final String CARDTYPE = "CardType";
    public int type;
    public Card card = new Card(13);
    public EditText ruleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_rule);
        Intent intent = getIntent();
        type = intent.getExtras().getInt(CARDTYPE);
        ruleText = (EditText) findViewById(R.id.ruleText);
        // SharedPreferences for saving the rules internally
        SharedPreferences spCards = getSharedPreferences("SPRules", 0);
        ruleText.setText(spCards.getString(Integer.toString(type), card.effect(type)));

    }

    public void save(View v){
        Intent intent = new Intent(this, RulesActivity.class);
        SharedPreferences spCards = getSharedPreferences("SPRules", 0);
        SharedPreferences.Editor editor = spCards.edit();
        // If the rule has been deleted put the default rule back to the SP
        if(ruleText.getText().toString().equals("")){
            editor.putString(Integer.toString(type), card.effect(type));
            editor.commit();
            startActivity(intent);
        }
        else {
            editor.putString(Integer.toString(type), ruleText.getText().toString());
            editor.commit();
            startActivity(intent);
        }
    }

    public void cancel(View v){
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }
}
