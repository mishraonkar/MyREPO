package burrow.co.in.registration.adapter;


import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import burrow.co.in.R;
import burrow.co.in.registration.LoginFragment;

public class WorkAdapter extends PagerAdapter {

   Activity activity;
    LayoutInflater mLayoutInflater;

    public WorkAdapter(Activity activity) {
        this.activity=activity;
        mLayoutInflater = LayoutInflater.from(activity);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if(position==0)
        {
            View view = mLayoutInflater.inflate(R.layout.home_screen_1, container, false);

            container.addView(view);
            return view;
        }
        else if(position==1)
        {
            View view = mLayoutInflater.inflate(R.layout.home_screen_2, container, false);

            container.addView(view);
            return view;
        }
        else if(position==2)
        {
            View view = mLayoutInflater.inflate(R.layout.home_screen_3, container, false);
            Button bt = (Button) view.findViewById(R.id.btGetStarted);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_container, new LoginFragment(), "login_fragment");
                    ft.commit();
                }
            });


            container.addView(view);
            return view;
        }
        else
        {
            return null;
        }


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
