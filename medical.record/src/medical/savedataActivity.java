package medical;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.medical.record.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class savedataActivity extends Activity {
	
	public static final String TAG = "savedataActivity";
	protected static final String ACTIVITY_SERVICE = null;
	private DataManipulator names;
	private Button SaveButton;
	private Button CancelButton;
	private int nameNotCorrect = 0;
	private int phoneNotCorrect = 0;
	//private DataManipulator names;
	//private Button ViewButton;
	//private Button SosButton;
	
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.savepage);
		List<String> UserData = new ArrayList<String>();
		
		this.names = new DataManipulator(this);
		final EditText Name =(EditText) findViewById(R.id.username);
		final EditText DateOfBirth =(EditText) findViewById(R.id.userdob);
		final EditText Address =(EditText) findViewById(R.id.useraddress);
		final EditText ResidenceNumber =(EditText) findViewById(R.id.userresidence);
		final EditText OfficeNumber =(EditText) findViewById(R.id.useroffice);
		final EditText DoctorNumber =(EditText) findViewById(R.id.userdoctor);
		final EditText BloodGroup =(EditText) findViewById(R.id.userbloodgroup);
		final EditText Height =(EditText) findViewById(R.id.userheight);
		final EditText Weight =(EditText) findViewById(R.id.userweight);
		final EditText BloodPressure =(EditText) findViewById(R.id.userbp);
		final EditText SurgeryDetails =(EditText) findViewById(R.id.usersurgery);
		final EditText Medications =(EditText) findViewById(R.id.usermedications);
		final EditText Vaccination =(EditText) findViewById(R.id.uservaccination);
		final EditText Allergy =(EditText) findViewById(R.id.userallergy);
		final EditText password =(EditText) findViewById(R.id.usereditpassword);
		
		//
		Name.addTextChangedListener(new TextValidator(Name, TextValidator.NAME));
		//DateOfBirth.addTextChangedListener(new TextValidator(DateOfBirth, TextValidator.DATE));
		ResidenceNumber.addTextChangedListener(new TextValidator(ResidenceNumber, TextValidator.PHONE));
		OfficeNumber.addTextChangedListener(new TextValidator(OfficeNumber, TextValidator.PHONE));
		DoctorNumber.addTextChangedListener(new TextValidator(DoctorNumber, TextValidator.PHONE));
		
		//Name.setKeyListener(new NameInputListener());
		SaveButton = (Button) findViewById(R.id.Savebutton);
	    CancelButton = (Button) findViewById(R.id.Cancelbutton);
	    
	    UserData = savedataActivity.this.names.selectAll();
	    
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
		password.setText(UserData.get(14).toString());
		}
		UserData.clear();
	    
	    SaveButton.setOnClickListener(new View.OnClickListener() 
        {

			public void onClick(View v) {
				
				if(nameNotCorrect==1)
				{
					Toast.makeText(getBaseContext(), "Please check the name you have entered and try again...", Toast.LENGTH_LONG).show();
				}
				else if(phoneNotCorrect == 1)
				{
					Toast.makeText(getBaseContext(), "Please check the phone numbers you have provided.\nAll phones numbers should consist of only digits with length between 7 and 13 digits...", Toast.LENGTH_LONG).show();
				}
				else
				{
					//String name = Name.getText().toString();
				
				Log.d(TAG, "Going to save new Data");
               // startActivity( new Intent(recordActivity.this, view.class) );
				
				savedataActivity.this.names.insert(Name.getText().toString(), DateOfBirth.getText().toString(), Address.getText().toString(), 
						ResidenceNumber.getText().toString(), OfficeNumber.getText().toString(), DoctorNumber.getText().toString(), 
						BloodGroup.getText().toString(), Height.getText().toString(), Weight.getText().toString(), BloodPressure.getText().toString(), 
						SurgeryDetails.getText().toString(), Medications.getText().toString(), Vaccination.getText().toString(), Allergy.getText().toString(),
						password.getText().toString());
				
				Toast savedMessage = Toast.makeText(getApplicationContext(), "Records updated Successfully...", Toast.LENGTH_SHORT);
				savedMessage.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,0);
				savedMessage.show();
				Log.d(TAG, "Called Insert method");
				Intent savedataActivityIntent = new Intent();
				savedataActivityIntent.setAction(ACTIVITY_SERVICE);
				savedataActivityIntent.setClass(getBaseContext(), HomeActivity.class);
				startActivity(savedataActivityIntent);
				finish();
			}
			}

			});
	
	    CancelButton.setOnClickListener(new View.OnClickListener() 
        {

			public void onClick(View v) {
				
				Log.d(TAG, "Cancel Saving new Data");
                startActivity( new Intent(savedataActivity.this, HomeActivity.class) );
                finish();
			}
        });
	
}

private class TextValidator implements TextWatcher
{
	private EditText BoxToWatch;
	private int ValidationType;
	private static final int NAME = 1;
	//private static final int DATE = 2;
	private static final int PHONE = 3;
	private static final String TAG = "TextValidator";
	private int duplicationFlag = 0;
	
	public TextValidator(EditText inputBox, int TypeOfText)
	{
		BoxToWatch = inputBox;
		ValidationType = TypeOfText;
		Log.d(TAG, "Text Validator Initialized...");
		Log.d(TAG, " Validation Type : "+ValidationType);
	}

	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
		
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		Log.d(TAG, "inside onTextChanged...");
		if(ValidationType == NAME)
		{
			if(BoxToWatch.getText().length()>0)
			{
			Log.d(TAG, "Going to check NAME...");
			//String pattern1 = "^[\\w\\s]+$ ^[[A-Z]\\s]";
			//String pattern2 = "[a-zA-Z]{2,}(/s+[a-zA-Z])*";
			//String pattern3 = "^((?:[A-Z](?:('|(?:[a-z]{1,3}))[A-Z])?[a-z]+)|(?:[A-Z]\\.))(?:([ -])((?:[A-Z](?:('|(?:[a-z]{1,3}))[A-Z])?[a-z]+)|(?:[A-Z]\\.)))?$";
			String pattern4 = "[a-zA-Z]+(\\s[a-zA-Z]+)*\\s*";
			Pattern namePattern = Pattern.compile(pattern4);
		
			Matcher checkName = namePattern.matcher(BoxToWatch.getText());
			if(!checkName.matches())
			{
				Log.d(TAG, "Name check returned negative..."+BoxToWatch.getText().toString());
				//BoxToWatch.setText(R.string.blank);
				if(duplicationFlag == 0)
					nameNotCorrect = 1;
			{Toast alert = Toast.makeText(getBaseContext(), "No special characters or numbers allowed in name. Please avoid . , - + \\, [0-9] etc.,", Toast.LENGTH_LONG);
				alert.show();
				duplicationFlag = 1;
				}
			}
			else
			{
				Log.d(TAG, "Name check returned positive..Name accepted...");
				duplicationFlag =0;
				nameNotCorrect = 0;
			}
		}
		}
	/*	if(ValidationType == DATE)
		{
			if(BoxToWatch.getText().length()==10)
			{
					Log.d(TAG, "Going to check DOB....");
			String DOBPattern1 = "([0123]{1}[0-9]{1}){1}[/](([0]{1}[[1-9]{1}){1}|([1]{1}[012]{1}){1}){1}[/](([1]{1}[9]{1}[0-9]{2})|([2]{1}[0]{1}[0-9]{2})){1}";
			Log.d(TAG, "DOB regular expression identified...");
			Pattern DOBPattern = Pattern.compile(DOBPattern1);
			Log.d(TAG, "Compiled DOB Pattern for matching...");
			Matcher checkDOB = DOBPattern.matcher(BoxToWatch.getText());
			if(!checkDOB.matches())
			{
				Log.d(TAG, "DOB check returned negative...");//+BoxToWatch.getText().toString());
				//BoxToWatch.setText(R.string.blank);
				if(duplicationFlag == 0)
					{Toast alert = Toast.makeText(getBaseContext(), "Date of Birth should be entered in the format DD/MM/YYYY", Toast.LENGTH_LONG);
				alert.show();
				duplicationFlag = 1;
					}
			}
			else
			{
				Log.d(TAG, "DOB check returned positive.. DOB accepted...");
				duplicationFlag=0;
			}
		}
		}*/
			if(ValidationType == PHONE)
			{
				if(BoxToWatch.getText().length()>0)
				{
				Log.d(TAG, "Going to check Phone no....");
				String phonePattern1 = "[0-9]{7,12}";
				
				Pattern phonePattern = Pattern.compile(phonePattern1);
			
				Matcher checkPhone = phonePattern.matcher(BoxToWatch.getText());
				if(!checkPhone.matches())
				{
					Log.d(TAG, "Phone no check returned negative..."+BoxToWatch.getText().toString());
					//BoxToWatch.setText(R.string.blank);
					phoneNotCorrect = 1;
					if(duplicationFlag == 0)
						{Toast alert = Toast.makeText(getBaseContext(), "Phone number should contain only digits with length between 7 to 13 characters", Toast.LENGTH_LONG);
					alert.show();
					duplicationFlag = 1;
						}
				}
				else
				{
					Log.d(TAG, "Phone check returned positive..Phone no. accepted...");
					duplicationFlag=0;
					phoneNotCorrect = 0;
				}
				}
		}
	}
	
}
}
