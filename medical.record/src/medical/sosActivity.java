package medical;


import my.medical.record.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class sosActivity extends Activity {
	
	private Button CallButton;
	 
	public void onCreate(Bundle savedInstanceState) {
 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
 
		CallButton = (Button) findViewById(R.id.SOSbutton);
 
		// add button listener
		CallButton.setOnClickListener(new OnClickListener() {
 
			public void onClick(View arg0) {
 
				Intent CallIntent = new Intent(Intent.ACTION_CALL);
				CallIntent.setData(Uri.parse("tel:8089676546"));
				startActivity(CallIntent);
 
			}
 
		});
 
	}

	protected static void setData(Uri parse) {
		// TODO Auto-generated method stub
		
	}}

	//private Button findViewById(int sosbutton) {
		// TODO Auto-generated method stub
		//return null;
	//}
//}
