package medical;

import java.util.ArrayList;
import java.util.List;
import my.medical.record.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewInformationActivity extends Activity {
	public static final String TAG = "ViewInformationActivity";
	private static final int PASSWORD_CONFIRM_DIALOG = 1;
	private static int COUNTER =1;
	private DataManipulator names;
	private String password = "";
		private Button EditButton;
		private Button BackButton;
		//private Button SosButton;
		
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.viewinformation);
			List<String> UserData = new ArrayList<String>();
			
			this.names = new DataManipulator(this);
			final TextView Name =(TextView) findViewById(R.id.ShowName);
			final TextView DateOfBirth =(TextView) findViewById(R.id.ShowDob);
			final TextView Address =(TextView) findViewById(R.id.ShowAddress);
			final TextView ResidenceNumber =(TextView) findViewById(R.id.ShowResiNum);
			final TextView OfficeNumber =(TextView) findViewById(R.id.ShowOfficeNum);
			final TextView DoctorNumber =(TextView) findViewById(R.id.ShowDoctorNum);
			final TextView BloodGroup =(TextView) findViewById(R.id.ShowBloodgroup);
			final TextView Height =(TextView) findViewById(R.id.ShowHeight);
			final TextView Weight =(TextView) findViewById(R.id.ShowWeight);
			final TextView BloodPressure =(TextView) findViewById(R.id.ShowBp);
			final TextView SurgeryDetails =(TextView) findViewById(R.id.ShowSurgery);
			final TextView Medications =(TextView) findViewById(R.id.ShowMedication);
			final TextView Vaccination =(TextView) findViewById(R.id.ShowVaccination);
			final TextView Allergy =(TextView) findViewById(R.id.ShowAllergy);
			
			EditButton = (Button) findViewById(R.id.EditButton);
			BackButton = (Button) findViewById(R.id.BackButton);
			
			UserData = ViewInformationActivity.this.names.selectAll();
			
			Log.d(TAG, ""+UserData.size());
			
			if(UserData.size()!=0)
			{
			Name.setText(UserData.get(0).toString());
			DateOfBirth.setText(UserData.get(1).toString());
			Address.setText(UserData.get(2).toString());
			ResidenceNumber.setText(UserData.get(3).toString());
			OfficeNumber.setText(UserData.get(4).toString());
			DoctorNumber.setText(UserData.get(5).toString());
			BloodGroup.setText(UserData.get(6).toString());
			Height.setText(UserData.get(7).toString());
			Weight.setText(UserData.get(8).toString());
			BloodPressure.setText(UserData.get(9).toString());
			SurgeryDetails.setText(UserData.get(10).toString());
			Medications.setText(UserData.get(11).toString());
			Vaccination.setText(UserData.get(12).toString());
			Allergy.setText(UserData.get(13).toString());
			password = UserData.get(14).toString();
			}
			UserData.clear();
			
			
			EditButton.setOnClickListener(new View.OnClickListener() 
	        {

				public void onClick(View v) {
					Log.e(TAG, "Starting passwordAcitvity");
	                
					showDialog(PASSWORD_CONFIRM_DIALOG);
					
				}});
			BackButton.setOnClickListener(new View.OnClickListener() 
	        {

				public void onClick(View v) {
					
					Log.d(TAG, "Cancel Saving new Data");
	                startActivity( new Intent(getBaseContext(), HomeActivity.class) );
	                finish();
				}
	        });
		}
		protected Dialog onCreateDialog(int whichDialog)
		{
			switch(whichDialog)
			{
			case PASSWORD_CONFIRM_DIALOG:
			
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View layout = inflater.inflate(R.layout.password, (ViewGroup) findViewById(R.id.passwordlayout));
			
				AlertDialog.Builder builder = new AlertDialog.Builder(this);// .this);
				builder.setTitle("Enter Password");
				builder.setView(layout);
				builder.setNegativeButton(R.string.exitString, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					removeDialog(PASSWORD_CONFIRM_DIALOG);
				}
				});
				builder.setNeutralButton(R.string.ContinueButtonText, new DialogInterface.OnClickListener() {
				
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					EditText passwordField = (EditText) layout.findViewById(R.id.PasswordTextBox);
					TextView message = (TextView) layout.findViewById(R.id.dialogtext);
					while(true)
					{
					if(passwordField.getText().toString().contentEquals(password))
					{
						//code to check password comes here
						message.setText(R.string.validPwd);
						COUNTER=1;
						Log.e(TAG, "Starting savedataActivity");
						Intent savedataActivityIntent = new Intent();
						savedataActivityIntent.setAction(ACTIVITY_SERVICE);
						savedataActivityIntent.setClass(getBaseContext(), savedataActivity.class);
						//removeDialog(PASSWORD_CONFIRM_DIALOG);
						startActivity(savedataActivityIntent);
						finish();
						break;
						
						
					}
					else
					{
						if(COUNTER == 3)
								{//removeDialog(PASSWORD_CONFIRM_DIALOG);
								//finish();
							break;
							}
						COUNTER++;
						message.setText(R.string.invalidPwd);
						
					}
					
					}
					removeDialog(PASSWORD_CONFIRM_DIALOG);
					showDialog(PASSWORD_CONFIRM_DIALOG);

				}
			});
			AlertDialog passwordConfirm = builder.create();
			return passwordConfirm;
		}
			return (Dialog) null;
		}
		public static void setAction(String activityService) {
			// TODO Auto-generated method stub
			
		}
	}
