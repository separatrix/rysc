package is.hi.lucky7;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// TODO: Remove this activity when it is no longer needed.

public class FileWriteTestActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
    	setContentView(R.layout.filetest);
	}
	
	public void writeTest(View view) {
		EditText etStringInput = (EditText) findViewById(R.id.etStringInput);
		FileHandler.saveGamestate(1, etStringInput.getText().toString(), this);
	}
	
	public void readTest(View view) {
		TextView twReadFromFile = (TextView) findViewById(R.id.twReadFromFile);
		String text = FileHandler.readGamestate(this);
		twReadFromFile.setText(text);
	}
}
