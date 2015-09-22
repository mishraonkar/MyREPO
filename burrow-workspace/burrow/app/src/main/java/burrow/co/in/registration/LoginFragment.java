package burrow.co.in.registration;

import burrow.co.in.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LoginFragment extends Fragment {
	
    Activity activity;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signin_google,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        ImageView Googleimg = (ImageView) activity.findViewById(R.id.imgGoogle);
        Googleimg.setOnClickListener(new View.OnClickListener() {
        	
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, new RegisterFragment(), "login_fragment");
                ft.commit();
            }
        });
    }
}