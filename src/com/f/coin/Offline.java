package com.f.coin;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Offline extends Activity {
	
	Button buttonreload;
	Button buttonclose;
	private static final int MENU_ITEM_EINSTELLUNGEN = Menu.FIRST;
	private static final int MENU_ITEM_MARKT = MENU_ITEM_EINSTELLUNGEN +1;
	
	private static final int MENU_ITEM_GOOGLE = MENU_ITEM_MARKT +1;
	private static final int MENU_ITEM_UPDATE = MENU_ITEM_GOOGLE +1;
	
	private static final int MENU_ITEM_CACHE = MENU_ITEM_UPDATE +1;
	private static final int MENU_ITEM_EXIT = MENU_ITEM_CACHE +1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offline);
		addListenerOnButton();
	}

	
	public void addListenerOnButton() {
		 
		buttonreload = (Button) findViewById(R.id.reload);
 
		buttonreload.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
			  Intent browserIntent = 
					  new Intent(Offline.this, Start.class);
			    startActivity(browserIntent);
			    Offline.this.finish();
 
			}
 
		});
		
		buttonclose = (Button) findViewById(R.id.close);
		 
		buttonclose.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
			  
			    Offline.this.finish();
 
			}
 
		});
 
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
	    	//menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
    		
    		
        }
		if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD) { 
			
	    	menu.add(0, MENU_ITEM_GOOGLE, 0, "FCOIN bei G+");
	    	menu.add(0, MENU_ITEM_UPDATE, 0, "auf Update prüfen");
	    	menu.add(0, MENU_ITEM_EXIT, 0, "fcoin schließen");
	    	//menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
	    	//menu.add(0, MENU_ITEM_EINSTELLUNGEN, 0, "Einstellungen");
    		
    		
        }
		//menu.add(0, MENU_ITEM_CACHE, 0, "! Cache zurücksetzen !");
		
		
		return super.onCreateOptionsMenu(menu);
	}
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	
		switch (item.getItemId()) {
		case R.id.close:
	    	
            Offline.this.finish();

	        Toast.makeText(this, "fcoin | FairCoin wurde beendet", Toast.LENGTH_LONG).show();
	        break;
	    
			case R.id.marktplatz:
				Toast.makeText(this, "fcoin Marktplatz | {BETA}-Phase...", Toast.LENGTH_LONG).show();
		        
				Intent marktplatz = new Intent(Offline.this, Marktplatz.class);
	            Offline.this.startActivity(marktplatz);
				break;
			case R.id.reload:
		    	
	            
		        Toast.makeText(this, "fcoin | erneuter Verbindungsversuch", Toast.LENGTH_LONG).show();
		        Intent reload = new Intent(Offline.this, Start.class);
	            Offline.this.startActivity(reload);
		        break;
		case MENU_ITEM_EINSTELLUNGEN:
			//AlertDialog.Builder builder2 = new AlertDialog.Builder(this);  
	        //builder2.setMessage("Einstellungen noch nicht verfügbar!")
	        //.setTitle("Hinweis")
	        //.setPositiveButton("OK", null);
	        //builder2.show();
			//break;
			Intent mainIntent = new Intent(Offline.this, Einstellungen.class);
            Offline.this.startActivity(mainIntent);
			break;
		case MENU_ITEM_UPDATE:
			Intent upd = new Intent(Offline.this, Update.class);
            Offline.this.startActivity(upd);
			break;
		case MENU_ITEM_MARKT:
			Toast.makeText(this, "fcoin Marktplatz | {BETA}-Phase...", Toast.LENGTH_LONG).show();
	        
			Intent markt = new Intent(Offline.this, Marktplatz.class);
            Offline.this.startActivity(markt);
			break;
		
		case MENU_ITEM_GOOGLE:		
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/102390642796132093917"));
		     startActivity(Intent.createChooser(intent, "Öffnen mit ?"));
			break;
		
		case MENU_ITEM_EXIT:
			finish();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
