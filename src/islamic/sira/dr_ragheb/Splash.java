package islamic.sira.dr_ragheb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
/*	      AdBuddiz.setPublisherKey("a41882dc-6cc8-41d0-9cbe-3b57094fcc8c");
	      AdBuddiz.cacheAds(this); // this = current Activity
	      */
	      
		 Thread timer = new Thread(){
		     	@Override
		     	public void run() {
		     		try{
		     			sleep(3000);
		     		}catch(InterruptedException e){
		     			e.printStackTrace();
		     		}finally{
		     			startActivity(new Intent(Splash.this, Main.class));
		     		}
		     	}

		     };
		     timer.start();
		     
		 }
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
	


}
