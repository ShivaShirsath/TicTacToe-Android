package ss.TicTacToe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		TextView textView = (TextView)findViewById(R.id.main);
        textView.setText("Shiva is Here 🤘\nYou Must Remember, Who am I. 😁");

    }
}
// © https://github.com/ShivaShirsath
