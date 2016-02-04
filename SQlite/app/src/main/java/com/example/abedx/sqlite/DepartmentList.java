package com.example.abedx.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DepartmentList extends AppCompatActivity {
    private static final String depList[] = {"Accounts", "Finance", "Loan", "ICT", "IT", "HEALTH",
            "WATER", "WPRK SHOP", "LABROTARY"};
    //array adpter to hlod the list
    private static ArrayAdapter<String> departmentsadapter = null;
    //intialize a list view
    private static ListView depListView = null;
    private static EditText depsearcheditText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //intialize list view and edit texts from layout view
        depListView = (ListView) findViewById(R.id.list_view);
        depsearcheditText = (EditText) findViewById(R.id.inputSearch);
        //intialize the arraylist
        departmentsadapter = new ArrayAdapter<String>(this, R.layout.list_item,
                R.id.dep_name, depList);
        //map the list view to thius adapter
        depListView.setAdapter(departmentsadapter);
        //enabling seraching
        depsearcheditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence textinput, int start, int count, int after) {
                //when user changes the text
                DepartmentList.this.departmentsadapter.getFilter().filter(textinput);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        depListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "You Selected Item" + itemSelected, Toast.LENGTH_LONG).show();
                if(itemSelected.equals("ICT"))
                {
                    startActivity(new Intent(DepartmentList.this,ICT.class));
                }
            }
        });
    }

}
