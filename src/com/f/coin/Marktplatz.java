package com.f.coin;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Marktplatz extends Activity {

	final Activity activity = this;
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		
		setContentView(R.layout.activity_marktplatz);
		getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
	            Window.PROGRESS_VISIBILITY_ON);
		
		
		WebViewClient marktwebnew = new WebViewClient()
	       {
	           
			@Override
            public void onReceivedError(WebView view, int errorCode, String      description, String failingUrl){
				
				WebView webView = (WebView) findViewById(R.id.markt1);
		        webView.getSettings().setJavaScriptEnabled(true);
		        
		        webView.loadUrl("file:///android_asset/www/foffline.html");
		        }

			// Override page so it's load on my view only
	           @Override
	           public boolean shouldOverrideUrlLoading(WebView view, String  url)
	           {
	        	   view.loadUrl(url);
	        	   
	            // Return true to override url loading (In this case do nothing).
	            return true;
	           }
	       };
	       
	       final ProgressDialog progressDialog = new ProgressDialog(activity);
	       progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	       progressDialog.setMessage("Daten werden geladen...");
	       progressDialog.setCancelable(false);
   
	       
	    WebView marktweb = (WebView)findViewById(R.id.markt1);
		WebSettings webSettings = marktweb.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		marktweb.setWebViewClient(marktwebnew);
		marktweb.loadUrl("http://fcoin.prxenon.com/mmarket/index.php");//The file:// is the protocol and it's nedded for internal files
		
		// WebChromeClient give progress etc info
	    marktweb.setWebChromeClient(new WebChromeClient() {

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
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.marktplatz, menu);
		return true;
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case R.id.close:
	    	
            Marktplatz.this.finish();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	    //case R.id.update:
	    	
            //Einstellungen.this.finish();

	        //Toast.makeText(this, "fcoin | nach Update wird gesucht...", Toast.LENGTH_LONG).show();
	        
	    //    Intent mainIntent = new Intent(Marktplatz.this, Einstellungen.class);
        //    Marktplatz.this.startActivity(mainIntent);
	    //    break;

	    }

	    return true;
	}
	
	private Toast toast;
	private long lastBackPressTime = 0;
	@SuppressLint("ShowToast")
	@Override
	public void onBackPressed() {
	  if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
	    toast = Toast.makeText(this, "Nochmal drücken um den Marktplatz zu verlassen", 4000);
	    toast.show();
	    this.lastBackPressTime = System.currentTimeMillis();
	  } else {
	    if (toast != null) {
	    toast.cancel();
	  }
	  super.onBackPressed();
	 }
	}

	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.marktplatz, menu);
		//return true;
	//}
	
}
