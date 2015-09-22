package burrow.co.in.owner.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import burrow.co.in.R;
import burrow.co.in.owner.adapter.ProfileViewPagerAdapter;
import burrow.co.in.owner.custom_view.SlidingTabLayout;
import burrow.co.in.owner.menu.NotificationOwner;
import burrow.co.in.owner.menu.OwnerRentOverview;
import burrow.co.in.owner.menu.OwnerSendHistory;
import burrow.co.in.owner.menu.ProfileActivety;

public class ProfileTenantActivity extends AppCompatActivity{
	
    Activity activity;
    LinearLayout menuConatiner;
    ImageButton homeButton;
    Toolbar menuToolbar, toolbar;
    ViewPager viewPager;
    SlidingTabLayout tabs;
    int dropMenuFlag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_profile_tenant_layout);
        activity = ProfileTenantActivity.this;
        menuConatiner = (LinearLayout) findViewById(R.id.menu_container);
        homeButton = (ImageButton) findViewById(R.id.home_icon);
        menuToolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
    
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
        
        homeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
							menuConatiner.setVisibility(View.GONE);
							break;
						case R.id.button_notifications:
							intent = new Intent(activity, NotificationOwner.class);
							startActivity(intent);
							menuConatiner.setVisibility(View.GONE);
							break;
						case R.id.button_sentHistory:
							Toast.makeText(activity, "Profile", Toast.LENGTH_SHORT).show();
							intent = new Intent(activity, OwnerSendHistory.class);
							startActivity(intent);
							menuConatiner.setVisibility(View.GONE);
							break;
						case R.id.button_rentOverView:
							Toast.makeText(activity, "Profile", Toast.LENGTH_SHORT).show();
							intent = new Intent(activity, OwnerRentOverview.class);
							startActivity(intent);
							menuConatiner.setVisibility(View.GONE);
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

        viewPager.setAdapter(new ProfileViewPagerAdapter(
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
}
