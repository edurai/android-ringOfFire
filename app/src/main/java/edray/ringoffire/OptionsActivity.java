package edray.ringoffire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class OptionsActivity extends AppCompatActivity {

    public static final String CARDRADIO = "CardRadio";
    public static final String RULERADIO = "RuleRadio";
    public boolean card = true;  // 32 cards
    public boolean rule = true;  // Default rules

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    // Starts the game and sends the values of the radio buttons to GameActivity
    public void startGame(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(CARDRADIO, card);
        intent.putExtra(RULERADIO, rule);
        startActivity(intent);
    }

    // Handles which radio button was clicked
    public void radioClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()){
            case R.id.radio32:
                if (checked) {
                    card = true;
                }
                break;
            case R.id.radio52:
                if (checked) {
                    card = false;
                }
                break;
            case R.id.radioDefault:
                if (checked) {
                    rule = true;
                }
                break;
            case R.id.radioOwn:
                if (checked) {
                    rule = false;
                }
        }
    }

}
