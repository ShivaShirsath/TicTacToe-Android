package ss.TicTacToe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.content.res.Configuration;
import android.widget.LinearLayout;
import android.os.PersistableBundle;

public class MainActivity extends AppCompatActivity {
	
	private long backPressedTime = 0;
	private Bundle state;
	private LinearLayout mainLayout, subLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		state = savedInstanceState;
        setContentView(R.layout.activity_main);
		
		mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
		subLayout = (LinearLayout)findViewById(R.id.subLayout);
    }
	
	public void oxyClick(View view){
		((Button)view).setText("❌");
	}
	public void onRestart(View view){
		Toast.makeText(getApplicationContext(), "ReStarted", Toast.LENGTH_SHORT).show();
		
		startActivity(getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName() ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		finish();
	}
	public void onStop(View view){
		
	}
	@Override public void onBackPressed() {
		if (backPressedTime + 2000 > System.currentTimeMillis()) {
			super.onBackPressed();
			finish();
			return;
		} else {
			Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
		}
		backPressedTime = System.currentTimeMillis();
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Checks the orientation of the screen
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			mainLayout.setOrientation(LinearLayout.HORIZONTAL);
			subLayout.setOrientation(LinearLayout.VERTICAL);
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
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
