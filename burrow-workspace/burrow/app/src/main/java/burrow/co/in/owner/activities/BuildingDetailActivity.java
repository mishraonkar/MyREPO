package burrow.co.in.owner.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import burrow.co.in.R;
import burrow.co.in.owner.adapter.FlatsAdapter;
import burrow.co.in.owner.menu.NotificationOwner;
import burrow.co.in.owner.menu.OwnerRentOverview;
import burrow.co.in.owner.menu.OwnerSendHistory;
import burrow.co.in.owner.menu.ProfileActivety;


public class BuildingDetailActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    Toolbar toolbar;
    Toolbar menuToolbar;
    LinearLayout menuConatiner;    
    ImageButton backButton;
    ImageButton homeButton;
    Activity activity;
    int dropMenuFlag = 0;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_building_detail_view);
        activity = BuildingDetailActivity.this;
        
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.list_flats);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        menuToolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        menuConatiner = (LinearLayout) findViewById(R.id.menu_container);
        homeButton = (ImageButton) findViewById(R.id.home_icon);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
        
        homeButton.setOnClickListener(clickEvent);
        floatingActionButton.setOnClickListener(clickEvent);
        collapsingToolbarLayout.setTitle("Flats");
        
        recyclerView.setAdapter(new FlatsAdapter(activity));

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        
        appBarLayout.setOnClickListener(clickEvent);
        
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
    
	private BitmapDrawable getBitmapDrawable(Context context, Dialog dialog, String str){
    	
    	TextView textView = (TextView) dialog.findViewById(R.id.textViewFake);
		textView.setText(str);
		textView.setTextColor(Color.parseColor("#FF3030"));

		int spec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		textView.measure(spec, spec);
		textView.layout(0, 0, textView.getWidth(),textView.getMeasuredHeight());
		Bitmap b = Bitmap.createBitmap(textView.getWidth(),textView.getHeight(), Bitmap.Config.ARGB_8888);
		
		
		Canvas canvas = new Canvas(b);
		canvas.translate(-textView.getScrollX(), -textView.getScrollY());
		textView.draw(canvas);
		textView.setDrawingCacheEnabled(true);
		
		
		Bitmap cacheBmp = textView.getDrawingCache();
		Bitmap viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true);
		textView.destroyDrawingCache(); 
		
		BitmapDrawable bmpDrawable = new BitmapDrawable(context.getResources(), viewBmp);
		bmpDrawable.setBounds(0, 0, bmpDrawable.getIntrinsicWidth(),bmpDrawable.getIntrinsicHeight());
		return bmpDrawable;
    }
    
    OnClickListener clickEvent = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			switch (id) {
			case R.id.home_icon:
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
				break;
				
			case R.id.fab:
				
		        final Dialog dialog = new Dialog(activity);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.owner_flat_add_view);
				Button button_cancel = (Button) dialog.findViewById(R.id.button_cancel);
				Button button_addFlat = (Button) dialog.findViewById(R.id.button_addFlat);
				OnClickListener popUpclickEvent = new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (v.getId() == R.id.button_cancel) {
							dialog.dismiss();
						} else if(v.getId() == R.id.button_addFlat)  {
							System.out.println("bildingdetailActivity-onclick popup");
							String flatName = ((EditText)dialog.findViewById(R.id.editText_flatName)).getText().toString().trim();
							String flatFloor = ((EditText)dialog.findViewById(R.id.editText_flatFloor)).getText().toString().trim();
							String flatType = ((Spinner)dialog.findViewById(R.id.spinner_flatType)).getSelectedItem().toString().trim();
							
							int errorFlag = 0;
							
							TextInputLayout til_flatName = ((TextInputLayout)dialog.findViewById(R.id.textInputLayout_flatName));
							if (flatName.length() < 1) {
								errorFlag = 1; 
								SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
			                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, dialog, "Building name is required.");
			                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			                    til_flatName.setError(ssb);
							}else {
								til_flatName.setError(null);
								til_flatName.setErrorEnabled(false);
							}
							
							TextInputLayout til_flatFloor = ((TextInputLayout)dialog.findViewById(R.id.textInputLayout_flatFloor));
							if (flatFloor.length() < 1) {
								errorFlag = 1; 
								SpannableStringBuilder ssb = new SpannableStringBuilder("Error message");
			                    BitmapDrawable bmpDrawableText = getBitmapDrawable(activity, dialog, "Building floor is required.");
			                    ssb.setSpan(new ImageSpan(bmpDrawableText), 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			                    til_flatFloor.setError(ssb);
							}else {
								til_flatFloor.setError(null);
								til_flatFloor.setErrorEnabled(false);
							}
							
							if (errorFlag == 0) {
								dialog.dismiss();
								// save data
							}
							
						}
					}
					
				};
				
				button_cancel.setOnClickListener(popUpclickEvent);
				button_addFlat.setOnClickListener(popUpclickEvent);
				
			    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			    lp.copyFrom(dialog.getWindow().getAttributes());
			    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
			    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
			    dialog.show();
			    dialog.getWindow().setAttributes(lp);
			    
				break;

			default:
				break;
			}
		}
	};
	
}
