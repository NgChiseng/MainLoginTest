package com.idbc.ngchiseng.mainlogintest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

public class TourActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TextView start;
    private TextView skip;
    private ImageView page0;
    private ImageView page1;
    private ImageView page2;
    private ImageView page3;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        /* --- This will to link the layout elements with this activity for their future use, and
        create the dots array and append in this, all the pages.
         */
        start = (TextView) findViewById(R.id.start);
        skip = (TextView) findViewById(R.id.skip);
        page0 = (ImageView) findViewById(R.id.page0);
        page1 = (ImageView) findViewById(R.id.page1);
        page2 = (ImageView) findViewById(R.id.page2);
        page3 = (ImageView) findViewById(R.id.page3);
        dots = new ImageView[]{page0, page1, page2, page3};

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            /*  Method that will be invoked when the current page is scrolled, either as part of a
            programmatically initiated smooth scroll or a user initiated touch scroll.

                @date[23/05/2017]

                @author[ChiSeng Ng]

                @param [int] position Position index of the first page currently being displayed.
            Page position+1 will be visible if positionOffset is nonzero.
                @param [float] positionOffset Value from [0, 1) indicating the offset from the page at
            position.
                @param [int] positionOffsetPixels Value in pixels indicating the offset from position.

                @return [void]
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            /*  Method that will be invoked when a new page becomes selected. Animation is not
            necessarily complete. In this case is overrided for set the background color of each
            page, and set the visibility of SKIP y START buttons.

                @date[23/05/2017]

                @author[ChiSeng Ng]

                @param [int] position Position index of the new selected page.

                @return [void]
             */
            @Override
            public void onPageSelected(int position) {

                /* --- This will mark the button corresponding toque page marked in the scroll bar
                of the screen.
                 */
                updateIndicators(position);

                switch (position) {
                    case 0:
                        mViewPager.setBackgroundColor(0xA000AAFF);
                        break;
                    case 1:
                        mViewPager.setBackgroundColor(0XA000FFAA);
                        break;
                    case 2:
                        mViewPager.setBackgroundColor(0xA0FFFF00);
                        break;
                    case 3:
                        mViewPager.setBackgroundColor(0xFF9F3F);
                }
                skip.setVisibility(position == 3 ? View.INVISIBLE : View.VISIBLE);
                start.setVisibility(position == 3 ? View.VISIBLE : View.INVISIBLE);

            }

            /*  Method called when the scroll state changes. Useful for discovering when the user
            begins dragging, when the pager is automatically settling to the current page, or when
            it is fully stopped/idle.

                @date[23/05/2017]

                @author[ChiSeng Ng]

                @param [int] state The new scroll state.

                @return [void]
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /* --- This will activate the listener to the START button
         */
        start.setOnClickListener(new View.OnClickListener() {

            /*  Method that handler the this event handler with the event listener defined, in this
            case is used for edit the TourSharePreferences's "visited" preference in "true", an start
            the activity corresponding(MainActivity for now).

                @date[23/05/2017]

                @author[ChiSeng Ng]

                @param [View] view View or widget that represent the START button in this context.

                @return [void]
             */
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences("Tour", 0).edit();
                editor.putBoolean("visited", true);
                editor.apply();
                Intent i = new Intent(TourActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        /* --- This will activate the listener to the SKIP button
         */
        skip.setOnClickListener(new View.OnClickListener() {

            /*  Method that handler the this event handler with the event listener defined, in this
            case is used for edit the TourSharePreferences's "visited" preference in "true", an start
            the activity corresponding(MainActivity for now).

                @date[23/05/2017]

                @author[ChiSeng Ng]

                @param [View] view View or widget that represent the SKIP button in this context.

                @return [void]
             */
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getSharedPreferences("Tour", 0).edit();
                editor.putBoolean("visited", true);
                editor.apply();
                Intent i = new Intent(TourActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

    }

    /*  Method that search, compare and update the mark of the dots in the scroll bar of the screen
    according to page corresponding.

        @date[23/05/2017]

        @author[ChiSeng Ng]

        @param [int] position Index of the current position of the page pressed.

        @return [void]
    */
    void updateIndicators(int position) {
        for (int i = 0; i < 3; i++) {
            dots[i].setImageResource(
                    i == position ? android.R.drawable.radiobutton_on_background :
                            android.R.drawable.radiobutton_off_background
            );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tour, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /* ---This will declare the variable img that will save each image will show in each fragment
        corresponding and also will declare the tour_imgs array to save the references of the draw
        images.
        */
        private ImageView img;
        private Integer[] tour_imgs = {R.drawable.graphic, R.drawable.map, R.drawable.tree};

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tour, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
