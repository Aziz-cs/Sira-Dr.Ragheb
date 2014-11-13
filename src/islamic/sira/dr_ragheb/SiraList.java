package islamic.sira.dr_ragheb;


import islamic.sira.dr_ragheb.adapters.AdapterFota7at;
import islamic.sira.dr_ragheb.adapters.AdapterMadinah;
import islamic.sira.dr_ragheb.adapters.AdapterMakkah;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SiraList extends Activity   {
	ListView makkahListView, madinahListView, fota7atListView;
	public static final String KEY_TITLE = "title";
	public static final String KEY_DURATION = "duration";
	ArrayList<HashMap<String, String>> MakkahList;
	ArrayList<HashMap<String, String>> MadinahList; 
	ArrayList<HashMap<String, String>> Fota7atList; 

    AdapterMakkah MakkahAdapter;
	AdapterMadinah MadinahAdapter;
	AdapterFota7at Fota7atAdapter;
    Intent playAudioIntent;
    String lessonURL;
    TabHost th;
    int currentTab;
    SharedPreferences tabShared;
    SharedPreferences makkahFinishedVideos, madinahFinishedVideos, fota7atFinishedVideos;
    public static boolean[] makkahFinished, madinahFinished, fota7atFinished;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_siralist);
//==========
	makkahFinished = new boolean[14];
	madinahFinished = new boolean[14];
	fota7atFinished = new boolean[18];
	
	makkahFinishedVideos = getSharedPreferences("makkah", 0);
	madinahFinishedVideos = getSharedPreferences("madinah", 0);
	fota7atFinishedVideos = getSharedPreferences("fota7at", 0);
    
    fillFinishedVideosArray(makkahFinishedVideos, "makkah", makkahFinished);
    fillFinishedVideosArray(madinahFinishedVideos, "madinah", madinahFinished);
    fillFinishedVideosArray(fota7atFinishedVideos, "fota7at", fota7atFinished);
	
	
//==========	
	tabShared = getSharedPreferences("TAB", 0);
	
	makkahListView = (ListView) findViewById(R.id.lv_makkah);
	madinahListView = (ListView) findViewById(R.id.lv_madinah);
	fota7atListView = (ListView) findViewById(R.id.lv_foto7at);
	
	MakkahList = new ArrayList<HashMap<String, String>>();
	MadinahList = new ArrayList<HashMap<String, String>>();
	Fota7atList = new ArrayList<HashMap<String, String>>();

	th = (TabHost) findViewById(R.id.tabhost);
	th.setup();
	TabSpec specs = th.newTabSpec("tag1");
	specs.setContent(R.id.tab1);
	specs.setIndicator("الفتح والتمكين", getResources().getDrawable(R.drawable.fota7at_tab));
	th.addTab(specs);
	
	specs = th.newTabSpec("tag2");
	specs.setContent(R.id.tab2);
	specs.setIndicator("العهد المدنى", getResources().getDrawable(R.drawable.madinah_tab));
	th.addTab(specs);
	
	specs = th.newTabSpec("tag3");
	specs.setContent(R.id.tab3);
	specs.setIndicator("العهد المكى", getResources().getDrawable(R.drawable.makkah_icon));
	th.addTab(specs);
	
	th.setCurrentTab(tabShared.getInt("CURRENT_TAB", 2));
	
    
	prepareSiraLists();
	

    MakkahAdapter=new AdapterMakkah(this, MakkahList, R.drawable.makkah);        
    makkahListView.setAdapter(MakkahAdapter);
    
    MadinahAdapter=new AdapterMadinah(this, MadinahList, R.drawable.madinah_listrow);        
    madinahListView.setAdapter(MadinahAdapter);
    
    Fota7atAdapter =new AdapterFota7at(this, Fota7atList, R.drawable.fota7at_listrow);        
    fota7atListView.setAdapter(Fota7atAdapter);
    
	playAudioIntent = new Intent(this, PlayAudio.class);
    
    makkahListView.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			HashMap<String, String> lessonHash = MakkahList.get(position);
			String lessonName = lessonHash.get(KEY_TITLE);
			
			switch (position) {
			case 0:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/25/25.mp3";
				break;
			case 1:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/26/26.mp3";
				break;
			case 2:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/27/27.mp3";
				break;
			case 3:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/28/28.mp3";
				break;
			case 4:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/29/29.mp3";
				break;
			case 5:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/30/30.mp3";
				break;
			case 6:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/31/31.mp3";
				break;
			case 7:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/32/32.mp3";
				break;
			case 8:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/33/33.mp3";
				break;
			case 9:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/34/34.mp3";
				break;
			case 10:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/35/35.mp3";
				break;
			case 11:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/36/36.mp3";
				break;
			case 12:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/37/37.mp3";
				break;
			case 13:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/38/38.mp3";
				break;
			default:
				break;
			}
			
			playAudioIntent.putExtra("URL", lessonURL);
			playAudioIntent.putExtra("LESSON_NAME", lessonName);
			playAudioIntent.putExtra("LIST_POSITION", position);
			playAudioIntent.putExtra("TAB", 1);
			
			if(makkahFinished[position] == true){
				playAudioIntent.putExtra("VIDEOWATCHED", true);
			}else{
				playAudioIntent.putExtra("VIDEOWATCHED", false);
			}
			startActivity(playAudioIntent);

		}
	});
    
    madinahListView.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			HashMap<String, String> lessonHash = MadinahList.get(position);
			String lessonName = lessonHash.get(KEY_TITLE);
			switch (position) {
			case 0:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/39/39.mp3";
				break;
			case 1:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/40/40.mp3";
				break;
			case 2:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/41/41.mp3";
				break;
			case 3:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/42/42.mp3";
				break;
			case 4:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/43/43.mp3";
				break;
			case 5:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/44/44.mp3";
				break;
			case 6:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/45/45.mp3";
				break;
			case 7:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/46/46.mp3";
				break;
			case 8:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/47/47.mp3";
				break;
			case 9:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/48/48.mp3";
				break;
			case 10:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/49/49.mp3";
				break;
			case 11:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/50/50.mp3";
				break;
			case 12:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/51/51.mp3";
				break;
			case 13:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/52/52.mp3";
				break;
			default:
				break;
			}
			
			playAudioIntent.putExtra("URL", lessonURL);
			playAudioIntent.putExtra("LESSON_NAME", lessonName);
			playAudioIntent.putExtra("LIST_POSITION", position);
			playAudioIntent.putExtra("TAB", 2);
			if(madinahFinished[position] == true){
				playAudioIntent.putExtra("VIDEOWATCHED", true);
			}else{
				playAudioIntent.putExtra("VIDEOWATCHED", false);
			}
			startActivity(playAudioIntent);
			
		}
	});
    
    
    fota7atListView.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			// TODO Auto-generated method stub
			HashMap<String, String> lessonHash = Fota7atList.get(position);
			String lessonName = lessonHash.get(KEY_TITLE);
			switch (position) {
			case 0:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/53/53.mp3";
				break;
			case 1:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/54/54.mp3";
				break;
			case 2:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/55/55.mp3";
				break;
			case 3:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/56/56.mp3";
				break;
			case 4:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/57/57.mp3";
				break;
			case 5:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/58/58.mp3";
				break;
			case 6:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/59/59.mp3";
				break;
			case 7:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/60/60.mp3";
				break;
			case 8:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/61/61.mp3";
				break;
			case 9:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/62/62.mp3";
				break;
			case 10:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/63/63.mp3";
				break;
			case 11:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/64/64.mp3";
				break;
			case 12:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/65/65.mp3";
				break;
			case 13:
				lessonURL = "http://audio2.islamweb.net/lecturs/rajeb_alserjani/66/66.mp3";
				break;
			case 14:
				lessonURL = "http://download.media.islamway.net/lessons/raggeb/254_Alsera/Alsera_15.mp3";
				break;
			case 15:
				lessonURL = "http://download.media.islamway.net/lessons/raggeb/254_Alsera/Alsera_16.mp3";
				break;
			case 16:
				lessonURL = "http://download.media.islamway.net/lessons/raggeb/254_Alsera/Alsera_17.mp3";
				break;
			case 17:
				lessonURL = "http://download.media.islamway.net/lessons/raggeb/254_Alsera/Alsera_18.mp3";
				break;
			default:
				break;
			}
			
			playAudioIntent.putExtra("URL", lessonURL);
			playAudioIntent.putExtra("LESSON_NAME", lessonName);
			playAudioIntent.putExtra("LIST_POSITION", position);
			playAudioIntent.putExtra("TAB", 3);
			if(fota7atFinished[position] == true){
				playAudioIntent.putExtra("VIDEOWATCHED", true);
			}else{
				playAudioIntent.putExtra("VIDEOWATCHED", false);
			}
			startActivity(playAudioIntent);
		}
	});
}

public void createMakkahRow(String name, String duration){
	// looping through all song nodes <song>
	HashMap<String, String> map = new HashMap<String, String>();
	// adding each child node to HashMap key => value
	map.put(KEY_TITLE, name);
	map.put(KEY_DURATION, duration);

	MakkahList.add(map);		

}

public void createMadinahRow(String name, String duration){
	// looping through all song nodes <song>
	HashMap<String, String> map = new HashMap<String, String>();
	// adding each child node to HashMap key => value
	map.put(KEY_TITLE, name);
	map.put(KEY_DURATION, duration);

	MadinahList.add(map);		

}

public void createFota7atRow(String name, String duration){
	// looping through all song nodes <song>
	HashMap<String, String> map = new HashMap<String, String>();
	// adding each child node to HashMap key => value
	map.put(KEY_TITLE, name);
	map.put(KEY_DURATION, duration);
	
	Fota7atList.add(map);		

}

public void prepareSiraLists(){
	//Makkah
	createMakkahRow("السيرة وبناء الأمة", "49:59");//==
	createMakkahRow("من الظلمات إلى النور", "1:00:51");
	createMakkahRow("من هنا بدأ الإسلام", "1:02:16");
	createMakkahRow("بدء الوحي", "1:04:48");
	createMakkahRow("الدعوة سراً", "1:01:54");
	createMakkahRow("الدعوة جهراً", "57:36");
	createMakkahRow("تربية الثبات", "1:05:40");
	createMakkahRow("هجرة الحبشة الأولى", "58:52"); //==
	createMakkahRow("هجرة الحبشة الثانية", "57:08");
	createMakkahRow("إسلام عمر", "1:05:47");
	createMakkahRow("عام الحزن", "1:05:24");
	createMakkahRow("بيعة العقبة الأولى", "1:02:59");
	createMakkahRow("بيعة العقبة الثانية", "1:00:14");
	createMakkahRow("الهجرة إلى المدينة", "1:05:01");

	//Madinah
	createMadinahRow("قيام الدولة الإسلامية", "1:01:55");
	createMadinahRow("مجتمع المدينة", "59:34");
	createMadinahRow("المشركون والدولة الإسلامية", "45:44");
	createMadinahRow("اليهود والدولة الإسلامية", "58:43");
	createMadinahRow("الطريق إلى بدر", "59:32");
	createMadinahRow("أهل بدر", "55:20");
	createMadinahRow("يوم بدر", "55:47");
	createMadinahRow("نصر بدر", "1:05:31");
	createMadinahRow("ما بعد بدر", "1:00:20");
	createMadinahRow("الطريق إلى أحد", "57:21");
	createMadinahRow("يوم أحد", "1:02:27");
	createMadinahRow("الخروج من مصيبة أحد", "1:08:43");
	createMadinahRow("الطريق إلى الأحزاب", "1:01:05");
	createMadinahRow("الأحزاب", "1:07:51");

	//Tamkeen
	createFota7atRow("المسلمون بعد الأحزاب", "1:07:34");
	createFota7atRow("الطريق إلى الحديبية", "1:01:57");
	createFota7atRow("صلح الحديبية", "58:28");
	createFota7atRow("ما بعد الحديبية", "1:00:43");
	createFota7atRow("عالمية الإسلام", "1:07:29");
	createFota7atRow("فتح خيبر", "1:02:49");
	createFota7atRow("قوة الإسلام", "1:15:24");
	createFota7atRow("نصر مؤتة", "1:01:20");
	createFota7atRow("الطريق إلى مكة", "1:18:16");
	createFota7atRow("فتح مكة", "1:13:03");
	createFota7atRow("إسلام مكة", "1:14:24");
	createFota7atRow("يوم حنين", "1:21:23");
	createFota7atRow("بين حنين والطائف", "1:16:50");
	createFota7atRow("إسلام هوازن", "1:02:33");
	createFota7atRow("غزوة تبوك", "1:09:04");
	createFota7atRow("ما بعد تبوك", "1:05:29");
	createFota7atRow("الوداع", "59:16");
	createFota7atRow("خاتمة السيرة", "1:08:18");
	
}

@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	tabShared = getSharedPreferences("TAB", 0);
	SharedPreferences.Editor editor = tabShared.edit();
	editor.putInt("CURRENT_TAB", th.getCurrentTab());
	editor.commit();
}

@Override
protected void onRestart() {
	// TODO Auto-generated method stub
	super.onRestart();
    fillFinishedVideosArray(makkahFinishedVideos, "makkah", makkahFinished);
    fillFinishedVideosArray(madinahFinishedVideos, "madinah", madinahFinished);
    fillFinishedVideosArray(fota7atFinishedVideos, "fota7at", fota7atFinished);
}

@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	
}



public void fillFinishedVideosArray(SharedPreferences sharedPref,String prefName, boolean[] array){
    sharedPref = getSharedPreferences(prefName, 0);
    for(int i=0; i<array.length; i++){
        array[i] = sharedPref.getBoolean("Video" + i, false);
    }
}

}
