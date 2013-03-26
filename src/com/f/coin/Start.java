package com.f.coin;

import java.io.File;
import java.net.URL;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;



@SuppressLint("SetJavaScriptEnabled")
public class Start extends Activity {
	final Activity activity = this;

	
	private static final int MENU_ITEM_EINSTELLUNGEN = Menu.FIRST;
	private static final int MENU_ITEM_MARKT = MENU_ITEM_EINSTELLUNGEN +1;
	
	private static final int MENU_ITEM_GOOGLE = MENU_ITEM_MARKT +1;
	private static final int MENU_ITEM_UPDATE = MENU_ITEM_GOOGLE +1;
	
	private static final int MENU_ITEM_CACHE = MENU_ITEM_UPDATE +1;
	private static final int MENU_ITEM_EXIT = MENU_ITEM_CACHE +1;
	
	
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
        
        
		
this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		
setContentView(R.layout.activity_start);
		getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
	            Window.PROGRESS_VISIBILITY_ON);
		
		
		WebViewClient fcoinwebview = new WebViewClient()
	       {
	           
			@Override
            public void onReceivedError(WebView view, int errorCode, String      description, String failingUrl){
				
				Intent offline= new Intent(Start.this, Offline.class);
	            Start.this.startActivity(offline);
	            Start.this.finish();
		        }

			// Override page so it's load on my view only
	           @Override
	           public boolean shouldOverrideUrlLoading(WebView view, String  url)
	           {
	        	   try {
	        		      URL urlObj = new URL(url);
	        		      if( TextUtils.equals(urlObj.getHost(),"www.fcoin.de") ) {
	        		        //Allow the WebView in your application to do its thing
	        		        return false;
	        		      } 
	        		      if( TextUtils.equals(urlObj.getHost(),"fcoin.prxenon.com") ) {
	        		    	  
	        					Intent markt = new Intent(Start.this, Marktplatz.class);
	        		            Start.this.startActivity(markt);
	        		            
		        		      } 
	        		      else {
	        		        //Pass it to the system, doesn't match your domain
	        		        Intent intent = new Intent(Intent.ACTION_VIEW);
	        		        intent.setData(Uri.parse(url));
	        		        startActivity(intent);
	        		        //Tell the WebView you took care of it.
	        		        return true;
	        		      }
	        		    }
	        		    catch (Exception e) {
	        		      e.printStackTrace();
	        		    }
				return true;
	           }
	       };
	       
	       final ProgressDialog progressDialog = new ProgressDialog(activity);
	       progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	       progressDialog.setMessage("fcoin Daten werden geladen...");
	       progressDialog.setCancelable(false);
   
		
		final ConnectivityManager conMgr =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        //super.setIntegerProperty("backgroundColor", Color.BLACK);
        if (activeNetwork != null && activeNetwork.isConnected()) {
        	//super.setIntegerProperty("loadUrlTimeoutValue", 60000);
        	//super.setIntegerProperty("splashscreen", R.drawable.splash);
        	WebView fcoinstart = (WebView)findViewById(R.id.fcoinstart);
    		WebSettings webSettings = fcoinstart.getSettings();
    		webSettings.setJavaScriptEnabled(true);
    		webSettings.setSupportZoom(true);
    		webSettings.setBuiltInZoomControls(true); 
    		fcoinstart.setWebViewClient(fcoinwebview);
    		fcoinstart.loadUrl("https://www.fcoin.de");//The file:// is the protocol and it's nedded for internal files
    		
    		
    		// WebChromeClient give progress etc info
    	    fcoinstart.setWebChromeClient(new WebChromeClient() {

    	        public void onProgressChanged(WebView view, int progress) {

    	            progressDialog.show();
    	            progressDialog.setProgress(0);
    	            activity.setProgress(progress * 1000);

    	            progressDialog.incrementProgressBy(progress);

    	            if (progress == 100 && progressDialog.isShowing())
    	                progressDialog.dismiss();
    	        }
    	    });
        }
        else {
        	nointernet();
        }
    }
	
	 
	    
	    private void nointernet(){
	    	//super.loadUrl("file:///android_asset/www/foffline.html");
	    	
	        
	        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
	        builder.setMessage("Ihre Internetverbindung ist nicht stabil. Bitte überprüfen Sie ihre Einstellungen.")  
	        .setTitle("Internet Fehler.")   
	        .setCancelable(false)  
	             .setPositiveButton("Doch, ich hab Internet.", 
	                  new DialogInterface.OnClickListener(){  
	                  public void onClick(DialogInterface dialog, int id){  
	                	  finish();
	                	  Intent it = new Intent(Start.this,Start.class);
	                  	startActivity(it);  
	                  }  
	             });  
	             builder.setNegativeButton("Abbrechen",  
	                  new DialogInterface.OnClickListener(){  
	                  public void onClick(DialogInterface dialog, int id){  
	                	  //finish(); 
	                	  Intent offline= new Intent(Start.this, Offline.class);
	                      Start.this.startActivity(offline);
	                      Start.this.finish();
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
	    	getMenuInflater().inflate(R.menu.start, menu);
	    	//menu.add(0, MENU_ITEM_MARKT, 0, "Marktplatz");
	    
			
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD) { 
				menu.add(0, MENU_ITEM_MARKT, 0, "Marktplatz");
		    	menu.add(0, MENU_ITEM_GOOGLE, 0, "FCOIN bei G+");
		    	menu.add(0, MENU_ITEM_UPDATE, 0, "auf Update prüfen");
		    	menu.add(0, MENU_ITEM_EINSTELLUNGEN, 0, "Einstellungen");
		    	menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
	    		
	    		
	        }
			if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) { 
				
		    	menu.add(0, MENU_ITEM_GOOGLE, 0, "FCOIN bei G+");
		    	menu.add(0, MENU_ITEM_UPDATE, 0, "auf Update prüfen");
		    	menu.add(0, MENU_ITEM_EXIT, 0, "fcoin schließen");
		    	menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
		    	//menu.add(0, MENU_ITEM_EINSTELLUNGEN, 0, "Einstellungen");
	    		
	    		
	        }
			//menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
			
			
			return super.onCreateOptionsMenu(menu);
		}
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	
			switch (item.getItemId()) {
			case R.id.close:
		    	
	            Start.this.finish();

		        Toast.makeText(this, "fcoin | FairCoin", Toast.LENGTH_LONG).show();
		        break;
		    
				case R.id.marktplatz:
					Toast.makeText(this, "fcoin Marktplatz | {BETA}-Phase...", Toast.LENGTH_LONG).show();
			        
					Intent marktplatz = new Intent(Start.this, Marktplatz.class);
		            Start.this.startActivity(marktplatz);
					break;
			case MENU_ITEM_EINSTELLUNGEN:
				//AlertDialog.Builder builder2 = new AlertDialog.Builder(this);  
		        //builder2.setMessage("Einstellungen noch nicht verfügbar!")
		        //.setTitle("Hinweis")
		        //.setPositiveButton("OK", null);
		        //builder2.show();
				//break;
				Intent mainIntent = new Intent(Start.this, Einstellungen.class);
	            Start.this.startActivity(mainIntent);
				break;
			case MENU_ITEM_UPDATE:
				Intent upd = new Intent(Start.this, Update.class);
	            Start.this.startActivity(upd);
				break;
			case MENU_ITEM_MARKT:
				Toast.makeText(this, "fcoin Marktplatz | {BETA}-Phase...", Toast.LENGTH_LONG).show();
		        
				Intent markt = new Intent(Start.this, Marktplatz.class);
	            Start.this.startActivity(markt);
				break;
			
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
		        
		        clearApplicationData();
				break;
			case MENU_ITEM_EXIT:
				finish();
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
	    
	    /**
	     * Exit the app if user select yes.
	     */
	    private void doExit() {

	        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
	                Start.this);

	        alertDialog.setPositiveButton("Ja", new OnClickListener() {

	            public void onClick(DialogInterface dialog, int which) {
	            	onDestroy();
	                finish();
	            }
	        });

	        alertDialog.setNegativeButton("Nein", null);

	        alertDialog.setMessage("Möchten Sie fcoin wirklich beenden?");
	        alertDialog.setTitle("fcoin | Hinweis");
	        alertDialog.show();
	    }
	    
	    
	    @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {

	        if(keyCode == KeyEvent.KEYCODE_BACK) {
	            doExit();
	        }

	        return super.onKeyDown(keyCode, event);
	    }
	    
	}