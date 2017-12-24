package edray.ringoffire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Function to start the game, switches to options menu
    public void start(View v) {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    // Function to change the rules, switches to rules menu
    public void changeRules (View v){
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

    // Function to view the current rules, switches to view rules menu
    public void viewRules (View v){
        Intent intent = new Intent(this, ViewRulesActivity.class);
        startActivity(intent);
    }
}
