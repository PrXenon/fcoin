package com.f.coin;

import org.apache.cordova.*;


import android.os.Bundle;
import android.app.Dialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


public class Update extends DroidGap {

	@Override
	
	
	public void onCreate(Bundle savedInstanceState) {
		
		PackageInfo pinfo = null;
		try {
			pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String versionName = pinfo.versionName;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);
		super.setIntegerProperty("loadUrlTimeoutValue", 60000);
        super.clearCache();
        super.setIntegerProperty("splashscreen", R.drawable.update);
        
        
        super.loadUrl("http://prxenon.com/_apis/_fcoin/version.php?version="+versionName, 60000);
		
	}
	
	@Override
    public void removeSplashScreen() {
      if (splashDialog != null && splashDialog.isShowing()) {
        final Update that = this;  // Your activity casting fun
        Runnable runnable = new Runnable() {
          public void run() {
            AnimationListener animationListener = new AnimationListener() {
              public void onAnimationEnd(Animation animation) {
                that.splashDialog.dismiss();
                that.splashDialog = null;
              }
              public void onAnimationRepeat(Animation animation) {
              }
              public void onAnimationStart(Animation animation) {
              }
            };
            AlphaAnimation splashDismissAnimation = new AlphaAnimation(1.0f, 0.0f);
            splashDismissAnimation.setAnimationListener(animationListener);
            splashDismissAnimation.setDuration(1050);

            View view = ((ViewGroup) ((Dialog) that.splashDialog).getWindow().getDecorView().getRootView()).getChildAt(0);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setBackgroundResource(android.R.color.transparent);
            view.startAnimation(splashDismissAnimation);
          }
        };
        this.runOnUiThread(runnable);
      }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_einstellungen, menu);
		return true;
	}
	
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case R.id.goback:
	    	
            Update.this.finish();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	    
	    }

	    return true;
	}
	

}
