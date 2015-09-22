package burrow.co.in.owner.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.View.OnClickListener;
import android.widget.Button;

import burrow.co.in.R;

public class AddTenantProfileTabThree extends Fragment{
	
	private Activity activity;
	private Button back, done; 
	
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.owner_add_tenant_profile_tab_three, container, false);
        activity = getActivity();
        
         back= (Button) v.findViewById(R.id.button_back);
         done = (Button) v.findViewById(R.id.button_next);
         
         back.setOnClickListener(buttonListner);
         done.setOnClickListener(buttonListner);
        
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
				activity.finish();           	
				break;
			case R.id.button_back:
            	vp.setCurrentItem(1);
				
				break;
			default:
				break;
			}
		}
	};

}
