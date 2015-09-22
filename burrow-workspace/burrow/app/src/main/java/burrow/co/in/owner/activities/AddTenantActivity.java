package burrow.co.in.owner.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import burrow.co.in.R;
import burrow.co.in.owner.common.General;

public class AddTenantActivity extends AppCompatActivity{
	
	private Toolbar toolbar;
	private EditText searchText;
	private FloatingActionButton searchButton;
	private LinearLayout resultContainer;
	private TextView helpText;
	private View errorView, foundView;
	
	private Activity activity;
	
	int errorFlag = 0;
	int foundFlag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_add_tenant_layout);
        
        activity = AddTenantActivity.this;
        
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchText = (EditText) findViewById(R.id.search_edit_text);
        searchButton = (FloatingActionButton) findViewById(R.id.fab);
        resultContainer = (LinearLayout) findViewById(R.id.LinearLayout_resultContainer);
        helpText = (TextView) findViewById(R.id.textView_helpText);
        
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	finish();
            }
        });
        
        searchButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String searchName = searchText.getText().toString().trim();
				if (searchName.length() < 1 && foundFlag == 0) {
					if (errorFlag == 0) {
						LayoutInflater layoutInflater = LayoutInflater.from(activity);
						errorView = layoutInflater.inflate(R.layout.owner_search_container_error, resultContainer, false);
						resultContainer.addView(errorView);
						helpText.setVisibility(View.GONE);
						searchButton.setImageResource(R.drawable.ic_close_white_24dp);
						errorFlag =1;
						searchText.setEnabled(false);
						Button inviteButton = (Button) errorView.findViewById(R.id.button_inviteTenant);
						inviteButton.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
						        final Dialog dialog = new Dialog(activity);
								dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
								dialog.setContentView(R.layout.owner_send_invite_confirm);
								Button button_cancel = (Button) dialog.findViewById(R.id.button_no);
								Button button_addFlat = (Button) dialog.findViewById(R.id.button_yes);
								OnClickListener popUpclickEvent = new OnClickListener() {

									@Override
									public void onClick(View v) {
										// TODO Auto-generated method stub
										if (v.getId() == R.id.button_no) {
											dialog.dismiss();
										} else if(v.getId() == R.id.button_yes)  {

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
							}
						});
					}else{
						resultContainer.removeView(errorView);
						searchButton.setImageResource(R.drawable.ic_search_white_24dp);
						helpText.setVisibility(View.VISIBLE);
						errorFlag = 0;
						searchText.setEnabled(true);
					}
				}else{
					if (foundFlag == 0) {
						LayoutInflater layoutInflater = LayoutInflater.from(activity);
						foundView = layoutInflater.inflate(R.layout.owner_search_container_found, resultContainer, false);
						resultContainer.addView(foundView);
						helpText.setVisibility(View.GONE);
						searchButton.setImageResource(R.drawable.ic_check_white_24dp);
						foundFlag = 1;
						ImageView profilePic = (ImageView) foundView.findViewById(R.id.imageView_profilePic);
						Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profile_img);
						profilePic.setImageBitmap(General.getCircularimage(bm));
					}else{
	                    Intent intent = new Intent(activity, AddTenantProfile.class);
	                    activity.startActivity(intent);
						finish();
					}
				}
			}
		});
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
