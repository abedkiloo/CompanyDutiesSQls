package SQLITELIST;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.abedx.sqlite.MessageClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abedx on 2/4/2016.
 */
public class DepartmentSQL extends SQLiteOpenHelper {
    /*
    deaclare variable to usae in creation of the dsqlite database
     */
    private Context mcontext;//the class conext
    private static final String DB_NAME = "DEPARTMENTS_DATA_BASE";
    private static final String DB_TABLE_NAME = "DEPARMENTS_TABLE";
    private static final int DB_VERSION_NUMBER = 1;
    private static final String C_DEPARTMENTS = "DEPARTMENT_NAME";
    private static final String C_id = "_ID";
    private static final String C_DEP_DESCRIPTION = "DEPDESCRIPTION";
    //sql statement to create a table
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE_NAME + "("
            + C_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + C_DEPARTMENTS + " VARCHAR (255) "
            + ")";
    //sql statemebt to drop a table
    private static final String DROP_TABLE = "DROP DATATABLE IF EXISTS" + DB_TABLE_NAME;

    public DepartmentSQL(Context context) {
        //constructor called to create a databsae
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
        this.mcontext = context;
        //showing that the database has been created
        MessageClass.message(context, "DataBase Employees was Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);//execute the table creation statement
        //show that the table has been created
        MessageClass.message(mcontext, "Table Departments was created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);//dropping the  table
        onCreate(db);
    }

    public long insert(String Department) {
        //call the method whcih is to help you insertion of ekements
        SQLiteDatabase db = this.getWritableDatabase();
        //create a content values which helps you insert elements into a database created
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_DEPARTMENTS, Department);//get the value to insert into the database
        //insert a values into department column
        long id = db.insert(DB_TABLE_NAME, null, contentValues);
        db.close();//close the database connection
        return id;

    }

    public List<String> getAllDeprtments() {
        //create an array list to help you populate the data
        ArrayList<String> listofDepartments = new ArrayList<String>();
        //select statement to help you obtain data from the database
        String selectallDepartments = "SELECT * FROM " + DB_TABLE_NAME;
        //string to hold the columns that you you want to work with
        String[] columns = {C_id, C_DEPARTMENTS};
        //make a reference to the object of database
        SQLiteDatabase db = this.getWritableDatabase();
        //create a cursor to help you query  data vakues in a databse
//        Cursor cursor = db.query(DB_TABLE_NAME, columns, selectallDepartments, null, null, null, null);
        Cursor cursor=db.rawQuery(selectallDepartments,null);
        //populate the list with items from the data base
        if (cursor.moveToNext()) {
            do {
                //get the index of both id anddepartment
                int indexofID = cursor.getColumnIndex(C_id);
                int indexofDeprtColumn = cursor.getColumnIndex(C_DEPARTMENTS);
                //get values from the specified column index
                int gotDepgetID = cursor.getInt(indexofID);
                String deprtGot = cursor.getString(indexofDeprtColumn);
                listofDepartments.add(deprtGot);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listofDepartments;
    }

}
