
package burrow.co.in.owner.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import burrow.co.in.R;
import burrow.co.in.owner.adapter.AddTenantProfileAdapter;
import burrow.co.in.owner.custom_view.SlidingTabLayout;

public class AddTenantProfile extends AppCompatActivity {

    ViewPager viewPager;
    SlidingTabLayout tabs;
    Toolbar toolbar;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_add_tenant_profile);
        activity = AddTenantProfile.this;
        
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
        
        
        viewPager.setAdapter(new AddTenantProfileAdapter(
                getSupportFragmentManager(),
                new String[]{"TAB1", "TAB2", "TAB3"}));
        tabs.setDistributeEvenly(true);
        tabs.setBackgroundColor(ContextCompat.getColor(activity, R.color.green));
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return ContextCompat.getColor(activity, R.color.yellow);
            }
        });
        tabs.setViewPager(viewPager);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_owner_add_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {

            case R.id.action_settings :
                //Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                //startActivity(intent);
                return  true;
        }
        return true;
    }

}