package org.cadenhead.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class SantaActivity extends Activity {
	public static final String TAG = "Santa";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void processClicks(View display) {
        Intent action = null;
        int id = display.getId();
        
        switch (id) {
        	case (R.id.imageButton1):
        		action = new Intent(Intent.ACTION_DIAL, 
        		    Uri.parse("tel:877-446-6723"));
        		break;
        	case (R.id.imageButton2):
        		action = new Intent(Intent.ACTION_VIEW,
        		    Uri.parse("http://www.noradsanta.org"));
        		break;
        	case (R.id.imageButton3):
        		action = new Intent(Intent.ACTION_VIEW,
        			Uri.parse("geo:0,0?q=101 Saint Nicholas Dr., North Pole, AK"));
        		break;
        	default:
        		break;
        }
        startActivity(action);
    }
}