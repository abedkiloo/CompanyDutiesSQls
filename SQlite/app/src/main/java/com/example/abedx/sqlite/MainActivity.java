package com.example.abedx.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //intilize db object
    DbSqiteAdapter dbSqiteAdapter;
    //declare rhe edit texts
    EditText F_Name, S_Name, specLoad;
    //declare the array adapter
    ArrayAdapter adapter;
    //declare the array adapter
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //create a spinner
        //intialize the spinner
        spinner = (Spinner) findViewById(R.id.listView);
        //create the spinner from the string array
        adapter = ArrayAdapter.createFromResource(this, R.array.listJobs,
                android.R.layout.simple_list_item_1);
        //make it be a drop drop down list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //map the adapter
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "You From the " + item + " Department", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //intialize DbSqlite obk=ject
        dbSqiteAdapter = new DbSqiteAdapter(this);
        //intialize and get reference to first name and second name
        F_Name = (EditText) findViewById(R.id.editTextFName);
        S_Name = (EditText) findViewById(R.id.editTextSecondName);
        specLoad = (EditText) findViewById(R.id.editTextSecondName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnSave(View view) {
        //get the first name and second name
        String Fname = F_Name.getText().toString();
        String Sname = S_Name.getText().toString();
        long id = dbSqiteAdapter.Insert(Fname, Sname);
        if (id < 0) {
            MessageClass.message(this, "Nothing was inserted");
        } else {
            MessageClass.message(this, "Insersion was Succeful");
            F_Name.setText("");
            S_Name.setText("");
        }
    }

    public void btnLoadData(View view) {
        String data = dbSqiteAdapter.getAllData();
        MessageClass.message(this, data);
    }

    public void btnLoadSpecific(View view) {
        String name = specLoad.getText().toString();
        String data = dbSqiteAdapter.getSpecificData(name);
        MessageClass.message(this, data);
    }

    public void btnUpdate(View view) {
        String oldnAme = "";
        int dbcount = dbSqiteAdapter.Update(oldnAme, "");
        MessageClass.message(this, " " + dbcount);
    }

    public void btnDelete(View view) {
        int dbcount = dbSqiteAdapter.Delete("");
        MessageClass.message(this, "" + dbcount);
    }

    public void btnDepartmentList(View view) {
    startActivity(new Intent(MainActivity.this,DepartmentList.class));
    }
}
