package com.f.coin;

import org.apache.cordova.DroidGap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebSettings;

public class Marktplatzfroyo extends DroidGap {
	
	
    @SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
 
    	
        super.onCreate(savedInstanceState);
        
        final ConnectivityManager conMgr =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        super.setIntegerProperty("backgroundColor", Color.BLACK);
        if (activeNetwork != null && activeNetwork.isConnected()) {
        	
        	super.loadUrl("http://fcoin.prxenon.com/mmarket/index.php");
        	WebSettings ws = super.appView.getSettings();
            ws.setSupportZoom(true);
            ws.setJavaScriptEnabled(true);
            
        	
        }
        else {
        	nointernet();
        }
    }
    
    private void nointernet(){
    	super.loadUrl("file:///android_asset/www/foffline.html");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  
        builder.setMessage("Ihre Internetverbindung ist nicht stabil. Bitte überprüfen Sie ihre Einstellungen.")  
        .setTitle("Internet Fehler.")   
        .setCancelable(false)  
             .setPositiveButton("Doch, ich hab Internet.", 
                  new DialogInterface.OnClickListener(){  
                  public void onClick(DialogInterface dialog, int id){  
                	  finish();
                	  Intent it = new Intent(Marktplatzfroyo.this,MainActivity.class);
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
}
