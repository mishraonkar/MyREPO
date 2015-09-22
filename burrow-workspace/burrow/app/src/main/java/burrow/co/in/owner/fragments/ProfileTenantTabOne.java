package burrow.co.in.owner.fragments;

import burrow.co.in.R;
import burrow.co.in.owner.common.General;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ProfileTenantTabOne extends Fragment {
	
	private Activity activity;
	private ImageView profilePic;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.owner_tenant_profile_tab_one, container, false);
        activity = getActivity();
        
        profilePic = (ImageView) v.findViewById(R.id.imageView_profilePic);
        Bitmap bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.icon_profile);
		profilePic.setImageBitmap(General.getCircularimage(bm));
        return v;
    }
}