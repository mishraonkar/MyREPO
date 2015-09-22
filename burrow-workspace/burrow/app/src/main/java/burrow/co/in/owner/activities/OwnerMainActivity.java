package burrow.co.in.owner.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import burrow.co.in.Constants;
import burrow.co.in.R;
import burrow.co.in.owner.api.BuildingDetails;
import burrow.co.in.owner.menu.NotificationOwner;
import burrow.co.in.owner.menu.OwnerRentOverview;
import burrow.co.in.owner.menu.OwnerSendHistory;
import burrow.co.in.owner.menu.ProfileActivety;

public class OwnerMainActivity extends AppCompatActivity{
	

    FloatingActionButton floatingActionButton;
    TextView toolBarTitle;
    LinearLayout buildingsContainer;
    Activity activity;
    Toolbar toolbar, menuToolbar;
    LinearLayout menuConatiner, profile, notifications, sentHistory, rentOverview;
    RecyclerView recyclerView;
    
    SharedPreferences prefs;
    int dropMenuFlag = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_activity_main);
        activity = OwnerMainActivity.this;
        prefs = activity.getSharedPreferences(Constants.SHARED_PREF_NAME_BUILDING_DETAIL, Context.MODE_PRIVATE);
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        recyclerView = (RecyclerView) findViewById(R.id.list_buildings);
        menuConatiner = (LinearLayout) findViewById(R.id.menu_container);
        menuToolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        profile = (LinearLayout) findViewById(R.id.profile);
        notifications = (LinearLayout) findViewById(R.id.notifcation);
        sentHistory = (LinearLayout) findViewById(R.id.sent);
        rentOverview = (LinearLayout) findViewById(R.id.rent_overview);
        
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_apps_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	Animation animVisible = AnimationUtils.loadAnimation(activity,  R.anim.slide_down);
            	final Animation animInvisible = AnimationUtils.loadAnimation(activity,  R.anim.slide_up);
            	menuConatiner.setAnimation(animVisible);
            	menuConatiner.getAnimation().start();
            	menuConatiner.setVisibility(View.VISIBLE);
            	dropMenuFlag = 1;
            	
                final LinearLayout trasparent = (LinearLayout) menuConatiner.findViewById(R.id.lineraLayout_transparent);
                trasparent.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (dropMenuFlag == 1) {
							dropMenuFlag = 0;
	                    	trasparent.setBackgroundColor(Color.parseColor("#00FFFFFF"));
	                    	menuConatiner.setAnimation(animInvisible);
	                    	menuConatiner.getAnimation().start();					
	                    	menuConatiner.setVisibility(View.GONE);
						}
					}
				});
                             
                animVisible.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation arg0) {
                    }           
                    @Override
                    public void onAnimationRepeat(Animation arg0) {
                    }           
                    @Override
                    public void onAnimationEnd(Animation arg0) {
                    	trasparent.setBackgroundColor(Color.parseColor("#64000000"));
                    }
                });
                
                menuToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
                menuToolbar.setTitle("Menu");
                menuToolbar.setNavigationOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	dropMenuFlag = 0;
                    	trasparent.setBackgroundColor(Color.parseColor("#00FFFFFF"));
                    	menuConatiner.setAnimation(animInvisible);
                    	menuConatiner.getAnimation().start();					
                    	menuConatiner.setVisibility(View.GONE);
		            }
		        });
                
                OnClickListener eventListener = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int buttonid = v.getId();
						Intent intent;
						switch (buttonid) {
						case R.id.button_profile:
							intent = new Intent(activity, ProfileActivety.class);
							startActivity(intent);
							trasparent.setBackgroundColor(Color.parseColor("#00FFFFFF"));
							menuConatiner.setVisibility(View.GONE);
							dropMenuFlag = 0;
							break;
						case R.id.button_notifications:
							intent = new Intent(activity, NotificationOwner.class);
							startActivity(intent);
							trasparent.setBackgroundColor(Color.parseColor("#00FFFFFF"));
							menuConatiner.setVisibility(View.GONE);
							dropMenuFlag = 0;
							break;
						case R.id.button_sentHistory:
							Toast.makeText(activity, "Profile", Toast.LENGTH_SHORT).show();
							intent = new Intent(activity, OwnerSendHistory.class);
							startActivity(intent);
							trasparent.setBackgroundColor(Color.parseColor("#00FFFFFF"));
							menuConatiner.setVisibility(View.GONE);
							dropMenuFlag = 0;
							break;
						case R.id.button_rentOverView:
							Toast.makeText(activity, "Profile", Toast.LENGTH_SHORT).show();
							intent = new Intent(activity, OwnerRentOverview.class);
							startActivity(intent);
							trasparent.setBackgroundColor(Color.parseColor("#00FFFFFF"));
							menuConatiner.setVisibility(View.GONE);
							dropMenuFlag = 0;
							break;

						default:
							break;
						}
					}
				};
				LinearLayout menuHolder = (LinearLayout) menuConatiner.findViewById(R.id.linearLayout_menuHolder);
				menuHolder.setOnClickListener(eventListener);
                Button profile = (Button) menuConatiner.findViewById(R.id.button_profile);
                Button notification = (Button) menuConatiner.findViewById(R.id.button_notifications);
                Button sentHistory = (Button) menuConatiner.findViewById(R.id.button_sentHistory);
                Button rentOverview = (Button) menuConatiner.findViewById(R.id.button_rentOverView);
                
                profile.setOnClickListener(eventListener);
                notification.setOnClickListener(eventListener);
                sentHistory.setOnClickListener(eventListener);
                rentOverview.setOnClickListener(eventListener);
            }
        });
        Typeface customFont = Typeface.createFromAsset(getAssets(), "burrow_font.ttf");
        toolBarTitle.setTypeface(customFont);
        
        floatingActionButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(activity, AddBuildingActivity.class);
				startActivity(intent);
			}
		});
        
        new BuildingDetails(activity,recyclerView).setBuildingAdapter();
          
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		int status = prefs.getInt(Constants.SHARED_PREF_NAME_BUILDING_DETAIL_FLAG, Constants.NEED_TO_UPDATE);
		if (!(status == Constants.UPTO_DATE)) {
			new BuildingDetails(activity,recyclerView).setBuildingAdapter();
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_owner_building, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {

            case R.id.action_search :
                //Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                //startActivity(intent);
                return  true;
        }
        return true;
    }


}
