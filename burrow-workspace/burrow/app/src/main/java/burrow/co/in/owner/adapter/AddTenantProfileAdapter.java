package burrow.co.in.owner.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import burrow.co.in.owner.fragments.AddTenantProfileTabOne;
import burrow.co.in.owner.fragments.AddTenantProfileTabThree;
import burrow.co.in.owner.fragments.AddTenantProfileTabTwo;
import burrow.co.in.owner.fragments.ProfileTenantTabOne;
import burrow.co.in.owner.fragments.ProfileTenantTabThree;
import burrow.co.in.owner.fragments.ProfileTenantTabTwo;



public class AddTenantProfileAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public AddTenantProfileAdapter(FragmentManager fm, CharSequence mTitles[]) {
        super(fm);

        this.Titles = mTitles;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                //AddTenantTabOne addTenantTabOne = new ;
                return new AddTenantProfileTabOne();

            case 1:
                return new AddTenantProfileTabTwo();
                
            case 2:
                return new AddTenantProfileTabThree();

            default:
                return null;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return Titles.length;
    }
}