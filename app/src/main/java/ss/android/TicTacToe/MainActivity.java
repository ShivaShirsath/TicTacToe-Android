package ss.android.TicTacToe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime = 0;

    private LinearLayout mainLayout, subLayout;
    private int clicks = 0;
    private ToggleButton turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        subLayout  = (LinearLayout) findViewById(R.id.subLayout);
        turn       = (ToggleButton) findViewById(R.id.turn);
    }

    public void oxyClick(View view) {
        clicks++;
        turn.setClickable(false);
        Button button = (Button) view;
        button.setText(turn.isChecked() ? "❌" : "⭕");
        button.setClickable(false);
        button.setBackgroundColor(0x00000000);
        if (checkGameOver(button.getText().toString()))
            showDialog(button.getText().toString() + " is Winner !");
        else if (clicks == 9) 
            showDialog("⭕❌ ! Draw");
        else 
            turn.setChecked(!turn.isChecked());
    }

    public boolean checkGameOver(String turnText) {
        return check(R.id.oxy0, R.id.oxy1, R.id.oxy2, turnText)
            || check(R.id.oxy3, R.id.oxy4, R.id.oxy5, turnText)
            || check(R.id.oxy6, R.id.oxy7, R.id.oxy8, turnText)
            || check(R.id.oxy0, R.id.oxy3, R.id.oxy6, turnText)
            || check(R.id.oxy1, R.id.oxy4, R.id.oxy7, turnText)
            || check(R.id.oxy2, R.id.oxy5, R.id.oxy8, turnText)
            || check(R.id.oxy0, R.id.oxy4, R.id.oxy8, turnText)
            || check(R.id.oxy2, R.id.oxy4, R.id.oxy6, turnText)
            ;
    }
    public boolean check(int id0, int id1, int id2, String turnText){
        return (
            ((Button) findViewById(id0)).getText().toString().contains(turnText) &&
            ((Button) findViewById(id1)).getText().toString().contains(turnText) &&
            ((Button) findViewById(id2)).getText().toString().contains(turnText)
            );
    }

    public void onRestart(View view) {
        Toast.makeText(getApplicationContext(), "ReStarted", Toast.LENGTH_SHORT).show();
        startActivity(getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName()).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // Start this new Activity
        finish(); // Stop this old Activity
    }

    public void showDialog(String title) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
            .setTitle(title)
            .setCancelable(false)
            .setPositiveButton("↻ restart ↺", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onRestart(new View(MainActivity.this));
                }
            })
            .create();
        turn.setText(title);
        dialog.show();
    }

    public void onExit(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Do again for exit.", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mainLayout.setOrientation(LinearLayout.HORIZONTAL);
            subLayout.setOrientation(LinearLayout.VERTICAL);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            subLayout.setOrientation(LinearLayout.HORIZONTAL);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
// © https://github.com/ShivaShirsath
