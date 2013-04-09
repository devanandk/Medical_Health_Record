package medical;

import my.medical.record.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class recordActivity extends Activity {
	
	private static final String TAG = "recordActivity";
	//private static int progress = 0;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    
    protected static final Context packageContext = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
       
        Log.v(TAG, "Inside onCreate in recordActivity class");
        //super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
       
        try{
            new Thread(){
                public void run() {
                    while(progressStatus < 10)
                    {
                	initializeApp();
                	progressBar.setProgress(progressStatus);}
                    handler.post( new Runnable(){
                        

						public void run() {
                            
							
                                Log.d(TAG, "Starting HomeActivity");
                                startActivity( new Intent(recordActivity.this, HomeActivity.class) );
                               
                                finish();
                           
                                
                        }
                    } );
                }
                public void initializeApp(){
                    try{
                    	Thread.sleep(250);
                    	++progressStatus;
                    }
                    catch(Exception err)
                    {
                    	Log.e(TAG, err.getMessage());
                    }
                }
        }.start();
        }catch (Exception e) {}
        }
       
        
    }
