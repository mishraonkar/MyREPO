package burrow.co.in.owner.menu;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import burrow.co.in.R;
import burrow.co.in.owner.common.General;

public class ProfileActivety extends AppCompatActivity {
	
    Toolbar mtoolbar;
    TextView toolBarTitle;
    LinearLayout menuConatiner;
    ImageView menu_bar, profilePic;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_profile);

        mtoolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        menuConatiner = (LinearLayout)findViewById(R.id.menu_container);
        profilePic = (ImageView) findViewById(R.id.imageView_profilePic); 
        
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon_profile);
		profilePic.setImageBitmap(General.getCircularimage(bm));

        mtoolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_owner_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
}
