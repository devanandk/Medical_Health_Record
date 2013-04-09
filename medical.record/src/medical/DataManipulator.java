package medical;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DataManipulator {
	
	private static final  String DATABASE_NAME = "mydatabase.db";
	private static final int DATABASE_VERSION = 1;
	static final String TABLE_NAME = "newtable";
	private static Context context;
	static SQLiteDatabase db;

	private SQLiteStatement insertStmt;
	
    private static final String INSERT = "insert into "
		+ TABLE_NAME + " (name,dateofbirth,address,residancenumber,officenumber,doctornumber," +
				"bloodgroup,height,weight,bloodpressure,surgery,medications,vaccination,allergy, password) " +
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String TAG = "DataManipulator";

	public DataManipulator(Context savedataActivity) {
		DataManipulator.context = savedataActivity;
		OpenHelper openHelper = new OpenHelper(DataManipulator.context);
		DataManipulator.db = openHelper.getWritableDatabase();
		this.insertStmt = DataManipulator.db.compileStatement(INSERT);

	}
	public long insert(String name,String dateofbirth,String address,String residancenumber,
			String officenumber,String doctornumber,String bloodgroup,String height,String weight,
			String bloodpressure, String surgery, String medications, String vaccination, String allergy,String password) 
	{
		Log.d(TAG, "Inside save method...");
		this.insertStmt.bindString(1, name);
		this.insertStmt.bindString(2, dateofbirth);
		this.insertStmt.bindString(3, address);
		this.insertStmt.bindString(4, residancenumber);
		this.insertStmt.bindString(5, officenumber);
		this.insertStmt.bindString(6, doctornumber);
		this.insertStmt.bindString(7, bloodgroup);
		this.insertStmt.bindString(8, height);
		this.insertStmt.bindString(9, weight);
		this.insertStmt.bindString(10, bloodpressure);
		this.insertStmt.bindString(11, surgery);
		this.insertStmt.bindString(12, medications);
		this.insertStmt.bindString(13, vaccination);
		this.insertStmt.bindString(14, allergy);
		this.insertStmt.bindString(15, password);
		return this.insertStmt.executeInsert();
	}

	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}

	public List<String> selectAll()
	{

		List<String> list = new ArrayList<String>();
		Cursor cursor = db.query(TABLE_NAME, new String[] { "name","dateofbirth","address","residancenumber",
				"officenumber","doctornumber","bloodgroup","height","weight","bloodpressure","surgery",
				"medications","vaccination","allergy", "password" },  null, null, null, null, "ROWID desc"); 

		int x=0;
		if (cursor.moveToFirst()) {
			do {
				String b1=new String(cursor.getString(x));//,cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8)};

				list.add(b1);

				x=x+1;
			} while (x<15);
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		} 
		cursor.close();

		return list;
	}


	public void delete(int rowId) {
		db.delete(TABLE_NAME, null, null); 
	}



	private static class OpenHelper extends SQLiteOpenHelper {

		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		//public OpenHelper(savedataActivity context) {
			// TODO Auto-generated constructor stub
		//}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " ( name TEXT,dateofbirth TEXT,address TEXT,residancenumber TEXT,doctornumber TEXT,officenumber TEXT,bloodgroup TEXT,height TEXT,weight TEXT,bloodpressure TEXT,surgery TEXT,medications TEXT,vaccination TEXT,allergy TEXT, password TEXT)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}
  


	


}



