package Fragments;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.huzdi.projectv1.R;

public class PagerStrip extends FragmentActivity implements EcologistFragment.OnFragmentInteractionListener, FunnyFragment.OnFragmentInteractionListener,
KnowledgeFragment.OnFragmentInteractionListener, SportiveFragment.OnFragmentInteractionListener{


    FragmentPagerAdapter adapterViewPager;
    ViewPager viewPager;
    Fragment mContent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_strip);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SampleAdapter(getSupportFragmentManager()));
      adapterViewPager = new SampleAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);





        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                Toast.makeText(PagerStrip.this,"Selected page position: " + position,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    public CharSequence getPageTitle(int position){
        return "ecologist" + (position +1 );
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
