package de.nle.how2knoedel.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Vector;

import de.nle.how2knoedel.R;
import de.nle.how2knoedel.fragment.MainSlideDetailsFragment;
import de.nle.how2knoedel.fragment.MainSlideOverviewFragment;
import de.nle.how2knoedel.fragment.stateadapter.MainSlidePagerAdapter;
import de.nle.how2knoedel.util.DepthPageTransformer;

public class MainSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slide);

        List<Fragment> fragments = new Vector<Fragment>();

        //for each fragment you want to add to the pager
            // save data state
        Bundle page = new Bundle();
//        page.putString("url", url);
        fragments.add(Fragment.instantiate(
                this, MainSlideOverviewFragment.class.getName(), page));

        Bundle page2 = new Bundle();
//        page.putString("url", url);
        fragments.add(Fragment.instantiate(
                this, MainSlideDetailsFragment.class.getName(), page2));

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new MainSlidePagerAdapter(
                getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);
        // slide animation
        mPager.setPageTransformer(true, new DepthPageTransformer());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
}