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

    public void start(View v) {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void changeRules (View v){
        Intent intent = new Intent(this, RulesActivity.class);
        startActivity(intent);
    }

    public void viewRules (View v){
        Intent intent = new Intent(this, ViewRulesActivity.class);
        startActivity(intent);
    }
}
