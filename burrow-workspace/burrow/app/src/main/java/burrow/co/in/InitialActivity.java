package burrow.co.in;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import burrow.co.in.registration.SelectFragment;
import burrow.co.in.registration.SplashFragment;
import burrow.co.in.owner.activities.OwnerMainActivity;

public class InitialActivity extends AppCompatActivity {

	private FragmentManager fm = null;
	private Activity activity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial);
		activity = InitialActivity.this;
		
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, new SplashFragment());
        ft.commit();
        
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
            	SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREF_NAME_REG, Context.MODE_PRIVATE); 
            	int status = prefs.getInt(Constants.SHARED_PREF_NAME_REG_STATUS, Constants.ERROR_STATUS);
            	if (status == Constants.SUCCESS_STATUS) {
            		Intent intent = new Intent(activity, OwnerMainActivity.class);
	                startActivity(intent);
	                activity.finish();
				} else {
	                fm = getSupportFragmentManager();
	                FragmentTransaction ft = fm.beginTransaction();
	                ft.replace(R.id.fragment_container, new SelectFragment());
	                ft.commit();
				}
            }
        }, 2500);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.initial, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
