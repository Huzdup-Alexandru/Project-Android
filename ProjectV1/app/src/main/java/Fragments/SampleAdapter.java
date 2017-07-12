package Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.astuetz.PagerSlidingTabStrip;
import com.example.huzdi.projectv1.R;

import Fragments.EcologistFragment;
import Fragments.FunnyFragment;
import Fragments.KnowledgeFragment;
import Fragments.SportiveFragment;

/**
 * Created by huzdi on 12.07.2017.
 */

public  class SampleAdapter extends FragmentPagerAdapter {

    public static final int NUM_ITEMS = 4;
    private String[] tabTitles = new String[]{"Ecologist Tab","Entertaining Tab","Knowledge Tab","Sportive Tab"};
    private int[] tabIcons = {R.drawable.eco,R.drawable.know,R.drawable.sport,R.drawable.entertaining};

    public SampleAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return EcologistFragment.newInstance("ecologist","page 1");
            case 1:
                return FunnyFragment.newInstance("funny","Page 2");
            case 2:
                return KnowledgeFragment.newInstance("knowledge","Page 3");
            case 3:
                return SportiveFragment.newInstance("sportive","Page 4");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}