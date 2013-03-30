package com.f.coin;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Einstellungen extends FragmentActivity implements
		ActionBar.TabListener {
	
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.einstellungen);

		
		// Set up the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {           
			final ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			
			// Create the adapter that will return a fragment for each of the three
			// primary sections of the app.
			mSectionsPagerAdapter = new SectionsPagerAdapter(
					getSupportFragmentManager());

			// Set up the ViewPager with the sections adapter.
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mSectionsPagerAdapter);

			// When swiping between different sections, select the corresponding
			// tab. We can also use ActionBar.Tab#select() to do this if we have
			// a reference to the Tab.
			mViewPager
					.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
						@Override
						public void onPageSelected(int position) {
							actionBar.setSelectedNavigationItem(position);
						}
					});

			// For each of the sections in the app, add a tab to the action bar.
			for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
				// Create a tab with text corresponding to the page title defined by
				// the adapter. Also specify this Activity object, which implements
				// the TabListener interface, as the callback (listener) for when
				// this tab is selected.
				actionBar.addTab(actionBar.newTab()
						.setText(mSectionsPagerAdapter.getPageTitle(i))
						.setTabListener(this));
			}
		}

	    }
		

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_einstellungen, menu);
		return true;
	}

	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case R.id.goback:
	    	
            Einstellungen.this.finish();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	    case R.id.update:
	    	
            //Einstellungen.this.finish();

	        Toast.makeText(this, "fcoin | nach Update wird gesucht...", Toast.LENGTH_LONG).show();
	        
	        Intent mainIntent = new Intent(Einstellungen.this, Update.class);
            Einstellungen.this.startActivity(mainIntent);
	        break;

	    }

	    return true;
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
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			
			return fragment;
			
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
				return getString(R.string.title_section1).toUpperCase();
			case 1:
				return getString(R.string.title_section2).toUpperCase();
			case 2:
				return getString(R.string.title_section3).toUpperCase();
			}
			return null;
		}
	}

	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		
		
    
   
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		String changelogp2 = "<p><b>Version 1.8.6 - 30.03.2013<br><br></b>&raquo; add Email an Anbieter<br>&raquo; add Eintrag Empfehlen/Teilen<br>&raquo; new AppIcon<br>&raquo; Bugfixes<br></p><p><b>Version 1.8.52 - 28.03.2013<br><br></b>&raquo; fcoin f-senden fixes<br></p><p><b>Version 1.8.4 - 28.03.2013<br><br></b>&raquo; add call Funktion<br></p><p><b>Version 1.8.1 - 27.03.2013<br><br></b>&raquo; fixed zoom<br>&raquo; fixed offlinepage<br></p><p><b>Version 1.8.0 - 25.03.2013 (stabel)<br><br></b>&raquo; stable Release<br>&raquo; Bugfixes<br>&raquo; Update offline Page<br>&raquo; Ladeanzeige in fcoin<br>&raquo; Marktplatz in Action Menu<br></p><p><b>Version 1.6.9 - 16.03.2013<br><br></b>&raquo; Bugfixes<br></p><p><b>Version 1.6.2 - 16.03.2013<br><br></b>&raquo; Bugfixes<br>&raquo; add Support Android 2.2.x (FROYO)<br>&raquo; changed BackKeyEvent<br></p><p><b>Version 1.6.1 - 15.03.2013<br><br></b>&raquo; add Marktplatz<br>&raquo; changed backbuttom event<br>&raquo; add fcoin API v0.1<br></p>";
		
		String changelog = "<p><b>Version 1.5.81 - 11.03.2013<br><br></b>&raquo; fixed externe Links<br></p><p><b>Version 1.5.5 - 06.03.2013<br><br></b>&raquo; add Teil-Support Android 2.3.3 (API 10) Einstellungs Menu fehlt ! <br></p><p><b>Version 1.5 - 04.03.2013<br><br></b>&raquo; add Einstellungen in Options menu<br>&raquo; removed old changelog page<br>&raquo; add appinfo &amp; impressum page<br>&raquo; add check new version</p><hr width=\"90%\" size=\"1\"><p><strong>Version 1.3.6 - 19.02.2013</strong></strong><br><br>&raquo; Add CacheClean in option menu<br>&raquo; some code stuff </p><hr width=\"90%\" size=\"1\"><p><strong>Version 1.3.1 - 18.02.2013</strong><br><br>&raquo; Splashscreen modifications<br>&raquo; unused code clean<br>&raquo; new ChangLog theme<br>&raquo; new offline page theme</p><hr width=\"90%\" size=\"1\"></p>";
		String appinfo = "<br><br><b>App Version:<b> 1.8.6";
		String fcoininfo ="<b>www.fcoin.de</b>";
		

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).matches("1")) {
				TextView textView = new TextView(getActivity());
				textView.setPadding(30, 30, 30, 30);
				textView.setTextSize(23);
				textView.setMovementMethod(new ScrollingMovementMethod());
				
				//textView.setGravity(Gravity.CENTER);
				//textView.setBackgroundResource(R.drawable.bg);
				textView.setText(Html.fromHtml(changelogp2+changelog));
				
				//textView.setText("Unendlich gut, hier kommt dann der Liedtext hin...");
				return textView;
			}
			if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).matches("3")) {
				TextView textView = new TextView(getActivity());
				textView.setPadding(30, 30, 30, 30);
				textView.setTextSize(23);
				textView.setMovementMethod(new ScrollingMovementMethod());
				
				//textView.setGravity(Gravity.CENTER);
				//textView.setBackgroundResource(R.drawable.bg);
				textView.setText(Html.fromHtml(getString(R.string.update)+appinfo));
				
				
				//textView.setText("Unendlich gut, hier kommt dann der Liedtext hin...");
				
				return textView;
				
				
				
			}
			if (Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)).matches("2")) {
				TextView textView = new TextView(getActivity());
				textView.setPadding(30, 30, 30, 30);
				textView.setTextSize(23);
				textView.setMovementMethod(new ScrollingMovementMethod());
				
				//textView.setGravity(Gravity.CENTER);
				//textView.setBackgroundResource(R.drawable.bg);
				//textView.setText(Html.fromHtml(getString(R.string.update)+appinfo));
				
				
				textView.setText("Alle Infos auf https://www.fcoin.de");
				
				return textView;
			}
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}

}
