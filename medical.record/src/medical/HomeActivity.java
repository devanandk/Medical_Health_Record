package medical;

import java.util.ArrayList;
import java.util.List;

import my.medical.record.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class HomeActivity extends Activity
{
public static final String TAG = "HomeActivity";
	
	public Button ViewButton;
	private Button SosButton;
	public Button exitLabel;
	private DataManipulator names;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		ViewButton =(Button) findViewById(R.id.viewbutton);
	    SosButton = (Button) findViewById(R.id.SOSbutton);
		exitLabel = (Button) findViewById(R.id.exitButton);
		this.names = new DataManipulator(this);
		
		exitLabel.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		SosButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				List<String> UserData = new ArrayList<String>();
				UserData = HomeActivity.this.names.selectAll();
				if(UserData.size()!=0)
				{
				String phoneUrl = "tel:"+UserData.get(3).toString();
				if(!phoneUrl.equals("tel:"))
				{
				initializeCallStatePreferences(phoneUrl);
				phoneCallListener callListener = new phoneCallListener();
				TelephonyManager callManager = (TelephonyManager)HomeActivity.this.getSystemService(Context.TELEPHONY_SERVICE);//getSystemService();
				callManager.listen(callListener, PhoneStateListener.LISTEN_CALL_STATE);
				
				
				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(phoneUrl));
				callIntent.setComponent(new ComponentName("com.android.phone","com.android.phone.OutgoingCallBroadcaster"));
				startActivity(callIntent);
				finish();
				}
				else
				{
					Toast savedMessage = Toast.makeText(getApplicationContext(), "No Residence Number is defined...", Toast.LENGTH_SHORT);
					savedMessage.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
					savedMessage.show();
				}
				}
				UserData.clear();
			}
		});
	    
	    ViewButton.setOnClickListener(new View.OnClickListener() 
        {

			public void onClick(View v) {
				Log.e(TAG, "Starting ViewInformation");
                //startActivity( new Intent(projectActivity.this, view.class) );
				Intent ViewInformationActivityIntent = new Intent();
				ViewInformationActivity.setAction(ACTIVITY_SERVICE);
				ViewInformationActivityIntent.setClass(getBaseContext(), ViewInformationActivity.class);
				startActivity(ViewInformationActivityIntent);
				finish();
				
			}});
	}
	
	private class phoneCallListener extends PhoneStateListener
	{
		private int LAUNCHED = -1;
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(HomeActivity.this.getBaseContext());
		SharedPreferences.Editor editor = prefs.edit();
		private static final String TAG = "phoneCallListener";
		public void onCallStateChanged(int state, String incomingNumber)
		{
			String prefKey = HomeActivity.this.getResources().getString(R.string.last_phone_call_state_key);
			//String bpNum = HomeActivity.this.getResources().getString(R.string.last_phone_call_bp_key);
			int callState = prefs.getInt(prefKey, LAUNCHED);
			
			editor.putInt(prefKey, state);
			
			editor.commit();
			if(TelephonyManager.CALL_STATE_RINGING == state)
			{
				Log.i(TAG, "Ringing at emergency number...");
			}
			
			if(TelephonyManager.CALL_STATE_OFFHOOK==state)
			{
				Log.i(TAG, "Call is off hook...");
			}
			if(TelephonyManager.CALL_STATE_IDLE==state & callState != LAUNCHED)
			{
				Log.i(TAG, "Call has gone idle...");
				Intent StartHomeActivity = HomeActivity.this.getPackageManager().getLaunchIntentForPackage("my.medical.record");//new Intent(getApplicationContext(),HomeActivity.class);//HomeActivity.this.getPackageManager().getLaunchIntentForPackage();//HomeActivity.this.getResources());
				StartHomeActivity.setAction(HomeActivity.this.getResources().getString(R.string.main_show_phone_call));
				HomeActivity.this.startActivity(StartHomeActivity);
				finish();
			}
		}
	}
	
	private void initializeCallStatePreferences(String phNo)
	{
		final int LAUNCHED = -1;
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		SharedPreferences.Editor myEditor = pref.edit();
		String prefKey = this.getString(R.string.last_phone_call_state_key);
		String bpKey = this.getString(R.string.last_phone_call_bp_key);
		
		myEditor.putInt(prefKey, LAUNCHED);
		myEditor.putString(bpKey, phNo);
		myEditor.commit();
	}
	
}
