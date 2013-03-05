package com.f.coin;

import java.io.File;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.webkit.WebSettings;

import org.apache.cordova.*;



import com.f.coin.R;





public class MainActivity extends DroidGap {
	private static final int MENU_ITEM_EINSTELLUNGEN = Menu.FIRST;
	//private static final int MENU_ITEM_IMPRESS = MENU_ITEM_EINSTELLUNGEN +1;
	
	private static final int MENU_ITEM_GOOGLE = MENU_ITEM_EINSTELLUNGEN +1;
	private static final int MENU_ITEM_CACHE = MENU_ITEM_GOOGLE +1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
 
    	
        super.onCreate(savedInstanceState);
        boolean firstrun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("firstrun", true);
        if (firstrun){
        	new AlertDialog.Builder(this).setTitle("Erster Start..").setMessage("Dies ist der erste Start von Fcoin").setNeutralButton("OK", null).show();

        // Save the state
        getSharedPreferences("PREFERENCE", MODE_PRIVATE)
            .edit()
            .putBoolean("firstrun", false)
            .commit();
        }
        
        final ConnectivityManager conMgr =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        super.setIntegerProperty("backgroundColor", Color.BLACK);
        if (activeNetwork != null && activeNetwork.isConnected()) {
        	super.setIntegerProperty("loadUrlTimeoutValue", 60000);
        	super.setIntegerProperty("splashscreen", R.drawable.splash);
        	super.loadUrl("https://fcoin.de", 7000);
        	WebSettings ws = super.appView.getSettings();
            ws.setSupportZoom(true);
            ws.setBuiltInZoomControls(true); 
            
        	 //LocationManager locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            // if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){  
            //     createGpsDisabledAlert();  
           //  }
        }
        else {
        	nointernet();
        }
    }
    
    @Override
    public void removeSplashScreen() {
      if (splashDialog != null && splashDialog.isShowing()) {
        final MainActivity that = this;  // Your activity casting fun
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
       
    
    private void nointernet(){
    	super.loadUrl("file:///android_asset/www/foffline.html", 500);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        builder.setMessage("Ihre Internetverbindung ist nicht stabil. Bitte überprüfen Sie ihre Einstellungen.")  
        .setTitle("Internet Fehler.")   
        .setCancelable(false)  
             .setPositiveButton("Doch, ich hab Internet.", 
                  new DialogInterface.OnClickListener(){  
                  public void onClick(DialogInterface dialog, int id){  
                	  finish();
                	  Intent it = new Intent(MainActivity.this,MainActivity.class);
                  	startActivity(it);  
                  }  
             });  
             builder.setNegativeButton("Abbrechen",  
                  new DialogInterface.OnClickListener(){  
                  public void onClick(DialogInterface dialog, int id){  
                	  //finish();  
                  }  
             });  
        AlertDialog alert = builder.create();  
        alert.show();  
        }  
         
    
    
    
    public void onReceivedError( int errorCode, String description, String failingUrl)
	{
    	nointernet();
	}
    
   
   
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_ITEM_EINSTELLUNGEN, 0, "Einstellungen");
		
		//menu.add(0, MENU_ITEM_IMPRESS, 0, "Impressum");
		menu.add(0, MENU_ITEM_GOOGLE, 0, "FCOIN bei G+");
		menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
		return super.onCreateOptionsMenu(menu);
	}
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM_EINSTELLUNGEN:
			//AlertDialog.Builder builder2 = new AlertDialog.Builder(this);  
	        //builder2.setMessage("Einstellungen noch nicht verfügbar!")
	        //.setTitle("Hinweis")
	        //.setPositiveButton("OK", null);
	        //builder2.show();
			//break;
			Intent mainIntent = new Intent(MainActivity.this, Einstellungen.class);
            MainActivity.this.startActivity(mainIntent);
			break;
		//case MENU_ITEM_IMPRESS:
			//super.loadUrl("https://fcoin.de/kontakt.php");
			//break;
		case MENU_ITEM_GOOGLE:		
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/102390642796132093917"));
		     startActivity(Intent.createChooser(intent, "Öffnen mit ?"));
			break;
		case MENU_ITEM_CACHE:
			AlertDialog.Builder buildercache = new AlertDialog.Builder(this);  
	        buildercache.setMessage("fcoin Cache wird bereinigt bitte anschließend neu starten.")
	        .setTitle("Hinweis")
	        .setPositiveButton("OK", null);
	        buildercache.show();
	        super.appView.clearCache(true);
	        super.loadUrl("https://fcoin.de");
	        clearApplicationData();
			break;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
    

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir1(new File(appDir, s));
                    Log.i("TAG", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
                }
            }
        }
    }

    public static boolean deleteDir1(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir1(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();  
        
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //do your stuff and Return true to prevent this event from being propagated further
        	Log.i("HA", "Finishing");
        	finish();
            return true;
        }

        return false;
        
    }
    
}
    
   