package edray.ringoffire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class OptionsActivity extends AppCompatActivity {

    public static final String CARDRADIO = "CardRadio";
    public static final String RULERADIO = "RuleRadio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void startGame(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        RadioButton cardRadio32 = (RadioButton) findViewById(R.id.radio32);
        RadioButton defRadio = (RadioButton) findViewById(R.id.radioDefault);
        boolean card = cardRadio32.isChecked();
        boolean rule = defRadio.isChecked();
        intent.putExtra(CARDRADIO, card);
        intent.putExtra(RULERADIO, rule);
        startActivity(intent);
    }

    public void radioCardClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.radio32:
                if (checked) {
                    //
                }
                break;
            case R.id.radio52:
                if (checked) {
                    //
                }
                break;
        }
    }

    public void radioRuleClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()){
            case R.id.radioDefault:
                if (checked) {
                    //
                }
                break;
            case R.id.radioOwn:
                if (checked) {
                    //
                }
                break;
        }
    }
}
