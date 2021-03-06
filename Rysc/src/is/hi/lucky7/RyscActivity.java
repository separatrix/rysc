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
    
    public void StartGame(View view) {
    	Intent StartGame = new Intent(getApplicationContext(),
    			StartGameActivity.class);
    	startActivity(StartGame);
    } 
    
    public void JoinGame(View view) {
    	Intent JoinGame = new Intent(getApplicationContext(),
    			JoinGameActivity.class);
    	startActivity(JoinGame);
    } 
    
    public void StartMap(View view) {
    	Intent StartMap = new Intent(getApplicationContext(),
    			MapTest.class);
    	startActivity(StartMap);
    }
    
    public void UdpSend(View view) {
    	Intent UdpSend = new Intent(getApplicationContext(),
    			UdpSendActivity.class);
    	startActivity(UdpSend);
    }
        
    public void UdpReceive(View view) {
       	Intent UdpReceive = new Intent(getApplicationContext(),
       			UdpReceiveActivity.class);
       	startActivity(UdpReceive);
    } 
    
    public void TextGame(View view) {
       	Intent Play = new Intent(getApplicationContext(),
       			Play.class);
       	startActivity(Play);
    } 
    
    public void FileTest(View view) {
       	Intent FileWriteTestActivity = new Intent(getApplicationContext(),
       			FileWriteTestActivity.class);
       	startActivity(FileWriteTestActivity);
    } 
}