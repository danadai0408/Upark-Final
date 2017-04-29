package com.example.dannadai.uparking;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Space extends AppCompatActivity {
    SQLiteOpenHelper dbhelper2;
    SQLiteDatabase db;
    Cursor cursor;
    private View mContentView;
    private View mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Referencing UserEmail, Password EditText and TextView for SignUp Now
        final EditText add = (EditText) findViewById(R.id.editText);
        final EditText contact = (EditText) findViewById(R.id.editText2);
        final EditText phone = (EditText) findViewById(R.id.editText3);
        Button map = (Button) findViewById(R.id.showonmap);
        Button dial = (Button) findViewById(R.id.dial);

        //Opening SQLite Pipeline
        dbhelper2 = new SQLiteDBHelper2(this);
        db = dbhelper2.getReadableDatabase();

        String stradd = add.getText().toString();//...

        cursor = db.rawQuery("SELECT *FROM "+SQLiteDBHelper2.TABLE_NAME+" WHERE "+SQLiteDBHelper2.COLUMN_ADD+"=? ", new String[] {stradd});
        if (cursor != null) {
            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                //Retrieving User FullName and Email after successfull login and passing to UserArea
                String contactname = cursor.getString(cursor.getColumnIndex(SQLiteDBHelper2.COLUMN_CONTACTNAME));
                final String contactphone= cursor.getString(cursor.getColumnIndex(SQLiteDBHelper2.COLUMN_CONTACTPHONE));
                contact.setText(contactname);
                phone.setText(contactphone);

                map.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //...
                    }
                });
                dial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+Uri.encode(contactphone.trim())));
                        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(callIntent);
                    }
                });
                    }
                    else {

                        //I am showing Alert Dialog Box here for system errors
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Space.this);
                        builder.setTitle("Alert");
                        builder.setMessage("No such spot");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //-------Alert Dialog Code Snippet End Here
                    }
                }
    }
}