package com.example.abedx.sqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import SQLITELIST.DepartmentSQL;

public class SQLITEDepartmentsList extends AppCompatActivity {
    //declare spinner
    Spinner spinner = null;
    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner = (Spinner) findViewById(R.id.departspinner);
        input = (EditText) findViewById(R.id.input_deaprt);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String departpos = parent.getItemAtPosition(position).toString().trim();
                MessageClass.message(getApplicationContext(), "Selected department " + departpos);
                if(departpos.equals("ICT"))
                {
startActivity(new Intent(SQLITEDepartmentsList.this,ICT.class));
                }
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
    }

    public void btnAdd(View view) {
        String deprtString = input.getText().toString().trim();
        if (deprtString.length() > 0) {
            DepartmentSQL db = new DepartmentSQL(getApplicationContext());
            //insertion of a department
           long id= db.insert(deprtString);
            if(id>0)
            {
                MessageClass.message(getApplicationContext(),"Insertion was succesful");
            }
            else
            {
                MessageClass.message(getApplicationContext(),"Insertion of data was not succesful");
                input.setText("");
            }

            //hidiing the keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
            loadSpinnerDate();
        }
    }

    private void loadSpinnerDate() {
        DepartmentSQL db = new DepartmentSQL(getApplicationContext());
        List<String> listofDeparments=db.getAllDeprtments();
        ArrayAdapter<String> departAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,listofDeparments);
        departAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attaching the data adapter to the spinner
        spinner.setAdapter(departAdapter);
    }
}
