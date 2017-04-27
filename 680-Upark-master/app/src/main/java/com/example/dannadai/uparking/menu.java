/*
 * This is an todolist application
 * save: the application will take the contents of the To Do List and store it as a text file with the name ‘list.txt’
 * add: add a new entry on the list
 * delete: remove the item from the list
 * update: modify the value in its place
 * Close: close the app and save the content to ‘list.txt’
 * Every time an item is added or deleted, the app will speak the item text and say whether it was added or deleted.
 * the save data is in com.course.menuinflater
 * Yu-Hsing Chang
 */

package com.example.dannadai.uparking;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;

public class menu extends Activity {


	//create option menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Uri uri;
		// Handle item selection
		switch (item.getItemId()) {

			case R.id.profile:
				Intent i1 = new Intent(this, profile.class);
				startActivity(i1);
				return true;

			case R.id.feedback:
				Uri uri2 = Uri.parse("mailto:customerservice@gmail.com");
				Intent i2 = new Intent(Intent.ACTION_SENDTO, uri2);
				startActivity(i2);

				return true;


			case R.id.close:

				finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}

	}
}
