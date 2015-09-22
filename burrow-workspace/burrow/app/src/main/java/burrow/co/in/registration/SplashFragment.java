package burrow.co.in.registration;

import burrow.co.in.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashFragment extends Fragment {
    private Activity activity = null;
    private  ImageView image;

    public SplashFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.splash , container, false);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    
    @Override
    public void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	image=(ImageView)activity.findViewById(R.id.imageView_logo);
        if(image!=null) {
			Animation anim = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.zoom_in_logo);
			image.setAnimation(anim);
			image.getAnimation().start();
        }
    }

}