package islamic.sira.dr_ragheb;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

public class PlayAudio extends Activity implements  OnPreparedListener, MediaController.MediaPlayerControl  {

    public static final String AUDIO_FILE_NAME = "audioFileName";
    NotificationManager notification;
	Notification notify;
	String lessonName;
    private MediaPlayer mediaPlayer;
    private MediaController mediaController;
    private String audioFile;
	int currentPosition, duration, list_position, currentTab;
    private Handler handler = new Handler();
    SharedPreferences makkahFinishedVideos, madinahFinishedVideos, fota7atFinishedVideos;
    boolean isVideoWatched;
    String category;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
        	mediaPlayer.release();
        }catch (Exception e) {
			// TODO: handle exception
		}
        setContentView(R.layout.activity_playaudio);
        

/*        if(!isOnline()){
        	Toast.makeText(this, "من فضلك تأكد من الاتصال بالانترنت", Toast.LENGTH_LONG).show();

        }*/
        Button finish = (Button) findViewById(R.id.btn_finish);
        finish.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
				mediaPlayer.release();
				notification.cancelAll();
				}catch (Exception e) {
					// TODO: handle exception
				}finally{
					PlayAudio.this.finish();
				}
			}
		});
        ImageView videoWatchedMark = (ImageView) findViewById(R.id.iv_videoWatched);
        TextView videoWatchedText = (TextView) findViewById(R.id.tv_videoWatched);


        TextView title = (TextView) findViewById(R.id.tv_title);
        

        Intent intent = getIntent();
        audioFile = intent.getExtras().getString("URL");
        list_position = intent.getExtras().getInt("LIST_POSITION");
        currentTab = intent.getExtras().getInt("TAB");

        switch (currentTab) {
		case 1:
			category = "العهد المكى";
			break;
		case 2:
			category = "العهد المدنى";
			break;
		case 3:
			category = "الفتح والتمكين";	
			break;
		default:
			break;
		}
        
        lessonName = intent.getExtras().getString("LESSON_NAME");
        title.setText(lessonName);
        isVideoWatched = intent.getExtras().getBoolean("VIDEOWATCHED");
        
        if(isVideoWatched){
        	videoWatchedMark.setVisibility(0); //visible
        	videoWatchedText.setVisibility(0);
        }else{
        	videoWatchedMark.setVisibility(4); //invisible - 8 for gone
        	videoWatchedText.setVisibility(4);
        }
        
        
       
       
        

        Uri myUri = Uri.parse(audioFile); // initialize Uri here
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
			mediaPlayer.setDataSource(getApplicationContext(), myUri);
			mediaPlayer.prepare();
	        mediaPlayer.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "من فضلك تأكد من الإتصال بالانترنت", Toast.LENGTH_LONG).show();	
			} 

        mediaController = new MediaController(this);
        
        
 mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mediaPlayer.release();
				notification.cancelAll();
				switch (currentTab) {
				case 1:
					finishVideo(makkahFinishedVideos, "makkah", list_position);
					break;
				case 2:
					finishVideo(madinahFinishedVideos, "madinah", list_position);
					break;
				case 3:
					finishVideo(fota7atFinishedVideos, "fota7at", list_position);
					break;
				default:
					break;
				}
				PlayAudio.this.finish();
			}
		});

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	// TODO Auto-generated method stub
    	mediaController.show();
    	return false;
    }
    
   
/*    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	mediaPlayer.pause();
    	duration = mediaPlayer.getDuration();
    	currentPosition = mediaPlayer.getCurrentPosition();
        mediaController.hide();
    }*/

    
    public void finishVideo(SharedPreferences sharedPref, String prefName, int videoNumber){
        sharedPref = getSharedPreferences(prefName, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("Video" + videoNumber, true);
        editor.commit();

    }

    public void showNotification(){
      notification = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    	notify = new Notification(R.drawable.ic_launcher, getResources().getString(R.string.app_name), System.currentTimeMillis());
    	
    	
    	Context context = this;
    	CharSequence title = getResources().getString(R.string.app_name);
    	CharSequence details = category + " - " + lessonName;
    	Intent notifyIntent = new Intent(Intent.ACTION_MAIN);
    	notifyIntent.setClass(this, PlayAudio.class);
    	
    	PendingIntent pending = PendingIntent.getActivity(context, 0, notifyIntent, 0);


    	notify.setLatestEventInfo(context, title, details, pending);
    	notification.notify(0, notify);
    }

	public void onPrepared(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		 mediaController.setMediaPlayer(this);
		    mediaController.setAnchorView(findViewById(R.id.main_audio_view));

		    handler.post(new Runnable() {
		      public void run() {
		        mediaController.setEnabled(true);
		        mediaController.show();
		      }
		    });
		  
	}

	 public void start() {
	        mediaPlayer.start();
	      }

	      public void pause() {
	        mediaPlayer.pause();
	      }

	      public int getDuration() {
	        return mediaPlayer.getDuration();
	      }

	      public int getCurrentPosition() {
	        return mediaPlayer.getCurrentPosition();
	      }

	      public void seekTo(int i) {
	        mediaPlayer.seekTo(i);
	      }

	      public boolean isPlaying() {
	        return mediaPlayer.isPlaying();
	      }

	      public int getBufferPercentage() {
	        return 0;
	      }

	      public boolean canPause() {
	        return true;
	      }

	      public boolean canSeekBackward() {
	        return true;
	      }

	      public boolean canSeekForward() {
	        return true;
	      }
    /**
     * Checks if the device has Internet connection.
     * 
     * @return <code>true</code> if the phone is connected to the Internet.
 
    public boolean isOnline() {
        ConnectivityManager cm =
            (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    */
	      @Override
	    public void onBackPressed() {
	    	// TODO Auto-generated method stub
	    	   Intent setIntent = new Intent(Intent.ACTION_MAIN);
	    	   setIntent.addCategory(Intent.CATEGORY_HOME);
	    	   setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    	   showNotification();
	    	   startActivity(setIntent);
	    }
}
