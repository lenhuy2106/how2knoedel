package de.nle.how2knoedel.fragment.stateadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by T500 on 18.11.2014.
 */
public class MainSlidePagerAdapter extends FragmentStatePagerAdapter {

    public static int pos = 0;

    private List<Fragment> myFragments;

    public MainSlidePagerAdapter(FragmentManager fm, List<Fragment> myFrags) {
        super(fm);
        myFragments = myFrags;
    }

    @Override
    public Fragment getItem(int position) {
        return myFragments.get(position);

    }

    @Override
    public int getCount() {
        return myFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        setPos(position);
        String PageTitle = "";

        switch(pos)
        {
            case 0:
                PageTitle = "page 1";
                break;
            case 1:
                PageTitle = "page 2";
                break;
            case 2:
                PageTitle = "page 3";
                break;
            case 3:
                PageTitle = "page 4";
                break;
            case 4:
                PageTitle = "page 5";
                break;
            case 5:
                PageTitle = "page 6";
                break;
            case 6:
                PageTitle = "page 7";
                break;
        }
        return PageTitle;
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        MainSlidePagerAdapter.pos = pos;
    }
}

