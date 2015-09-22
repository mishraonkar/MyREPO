package burrow.co.in.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import burrow.co.in.Constants;
import burrow.co.in.R;
import burrow.co.in.owner.activities.OwnerMainActivity;

public class OTPFragment extends Fragment {
    Activity activity;
    ViewPager viewpager;
    EditText otp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View otpView = inflater.inflate(R.layout.otp_fragment_layout, container, false);
        otp = (EditText) otpView.findViewById(R.id.editText_otp);
        
        
        otp.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
		     
				if (s.length() == 4) {
					if (s.toString().equals("0000")) {
		                SharedPreferences.Editor editor = activity.getSharedPreferences(Constants.SHARED_PREF_NAME_REG, Context.MODE_PRIVATE).edit();
		                editor.putInt(Constants.SHARED_PREF_NAME_REG_STATUS, Constants.SUCCESS_STATUS);
		                editor.commit();
		                Intent intent = new Intent(getActivity(), OwnerMainActivity.class);
		                startActivity(intent);
		                activity.finish();
					}
				}
			}
		});
        
        otp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    otp.setGravity(Gravity.CENTER); 
                } else {
                    otp.setGravity(Gravity.LEFT); 
                }
            }
        });
    	return otpView;
    }

    @Override
    public void onAttach(Context context) {
        // TODO Auto-generated method stub
        super.onAttach(context);
        this.activity = getActivity();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}