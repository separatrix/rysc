package is.hi.lucky7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RyscActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void ViewRules(View view) {
    	Intent rulesgui = new Intent(getApplicationContext(),
    			rules.class);
    	startActivity(rulesgui);
    } 
}