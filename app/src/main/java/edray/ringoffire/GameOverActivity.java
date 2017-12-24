package edray.ringoffire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
    }

    // After the game is over switch to the main menu
    public void switchMenu (View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // After the game is over switch to the options menu to restart the game
    public void start (View v){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}
