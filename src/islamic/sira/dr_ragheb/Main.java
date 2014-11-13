package islamic.sira.dr_ragheb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity implements OnClickListener {
	Button sera, contactUs;
    SharedPreferences makkahFinishedVideos, madinahFinishedVideos, fota7atFinishedVideos;
    Intent intent;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	
/*	final Handler handler = new Handler();
	handler.postDelayed(new Runnable() {
		
		public void run() {
			// TODO Auto-generated method stub
			AdBuddiz.showAd(Main.this);
		}
	}, 2200);*/
	
	sera = (Button) findViewById(R.id.btn_sera);
	contactUs = (Button) findViewById(R.id.btn_contact);
	
	sera.setOnClickListener(this);
	contactUs.setOnClickListener(this);
	
	//================

	
	makkahFinishedVideos = getSharedPreferences("makkah", 0);
	madinahFinishedVideos = getSharedPreferences("madinah", 0);
	fota7atFinishedVideos = getSharedPreferences("fota7at", 0);
    


}
public void onClick(View buttonClicked) {
	// TODO Auto-generated method stub
	switch (buttonClicked.getId()) {
	case R.id.btn_sera:
		intent = new Intent(this, SiraList.class);
		startActivity(intent);
		break;
	case R.id.btn_contact:
		intent = new Intent(this, About.class);
		startActivity(intent);
		break;
	default:
		break;
	}
}



@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// TODO Auto-generated method stub
	menu.add("Delete history").setIcon(android.R.drawable.ic_menu_recent_history);
	return super.onCreateOptionsMenu(menu);
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// TODO Auto-generated method stub


	SharedPreferences.Editor editor = makkahFinishedVideos.edit();
	editor.clear();
	editor.commit();
	editor = madinahFinishedVideos.edit();
	editor.clear();
	editor.commit();
	editor = fota7atFinishedVideos.edit();
	editor.clear();	
	editor.commit();
	return super.onOptionsItemSelected(item);
}



}
