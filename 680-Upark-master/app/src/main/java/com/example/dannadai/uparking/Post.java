package com.example.dannadai.uparking;

import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mac on 4/28/17.
 */

public class Post extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posted_parking_places);

        //To hide AppBar for fullscreen.
        ActionBar ab = getSupportActionBar();
        ab.hide();

        openHelper = new SQLiteDBHelper2(this);

        //Referencing EditText widgets and Button placed inside in xml layout file
        final EditText locationDisplay = (EditText) findViewById(R.id.locationDisplay);
        final EditText contactName = (EditText) findViewById(R.id.contactName);
        final EditText phoneDisplay = (EditText) findViewById(R.id.phoneDisplay);
        Button save = (Button) findViewById(R.id.button);

        //Register Button Click Event
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db = openHelper.getWritableDatabase();

                String location = locationDisplay.getText().toString();
                String contact = contactName.getText().toString();
                String phone = phoneDisplay.getText().toString();

                //Calling InsertData Method - Defined below
                InsertData(location, contact, phone);

                //Alert dialog after clicking the Register Account
                final AlertDialog.Builder builder = new AlertDialog.Builder(Post.this);
                builder.setTitle("Information");
                builder.setMessage("Your post has been saved.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Finishing the dialog and removing Activity from stack.
                        dialogInterface.dismiss();
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    //Inserting Data into database - Like INSERT INTO QUERY.
    public void InsertData(String location, String contact, String phone) {

        ContentValues values = new ContentValues();
        values.put(SQLiteDBHelper2.COLUMN_ADD,location);
        values.put(SQLiteDBHelper2.COLUMN_CONTACTNAME,contact);
        values.put(SQLiteDBHelper2.COLUMN_CONTACTPHONE,phone);
        Log.d("Spot", location + " added");
    }

}