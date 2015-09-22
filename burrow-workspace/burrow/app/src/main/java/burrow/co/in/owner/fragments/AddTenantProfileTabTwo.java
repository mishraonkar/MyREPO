package burrow.co.in.owner.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import burrow.co.in.R;

public class AddTenantProfileTabTwo extends Fragment {

	private Button back, next;
	private LinearLayout idLayout, addressLayout;
	private ImageView idView, addressView;
	
	private Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.owner_add_tenant_profile_tab_two, container, false);
        
        activity = getActivity();
        
        back = (Button) v.findViewById(R.id.button_back);
        next = (Button) v.findViewById(R.id.button_next);
        idLayout = (LinearLayout) v.findViewById(R.id.linearLayout_idProof);
        addressLayout = (LinearLayout) v.findViewById(R.id.linearLayout_addressProof);
        idView = (ImageView) v.findViewById(R.id.imageView_idProof);
        addressView = (ImageView) v.findViewById(R.id.imageView_addressProof);
        
        back.setOnClickListener(buttonListner);
        next.setOnClickListener(buttonListner);
        
        return v;
    }
    
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    
    OnClickListener buttonListner = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int id = v.getId();
			final ViewPager vp =  (ViewPager) activity.findViewById(R.id.pager);
			switch (id) {			
			case R.id.button_next:
				
		        final Dialog dialog = new Dialog(activity);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.owner_add_tenant_profile_tab_two_confirm);
				Button button_cancel = (Button) dialog.findViewById(R.id.button_no);
				Button button_addFlat = (Button) dialog.findViewById(R.id.button_yes);
				final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.checkBox_confirm);
				
				OnClickListener popUpclickEvent = new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (v.getId() == R.id.button_no) {
							dialog.dismiss();
						} else if(v.getId() == R.id.button_yes)  {
							if (checkBox.isChecked()) {
								vp.setCurrentItem(2);
								dialog.dismiss();
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
			case R.id.button_back:
            	vp.setCurrentItem(0);
				
				break;
			default:
				break;
			}
		}
	};
}
