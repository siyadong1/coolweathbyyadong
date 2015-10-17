package com.coolweather.apps.service;

import com.coolweather.apps.receive.AutoUpdateReceive;
import com.coolweather.apps.util.HttpCallbackListener;
import com.coolweather.apps.util.HttpUtil;
import com.coolweather.apps.util.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

public class AutoUpdateService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				updateWeather();
			}
		}).start();
		
		/**
		 * 定时3小时更新天气
		 */
		AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		int delayTime = 1000*60*60*3;
		long triggerAtMillis = SystemClock.elapsedRealtime() + delayTime;
		Intent intentToReceive = new Intent(AutoUpdateService.this,AutoUpdateReceive.class);
		PendingIntent operation = PendingIntent.getBroadcast(this, 0, intentToReceive, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, operation);
		
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	/**
	 * 更新天气信息
	 */
	
	private void updateWeather(){
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String weatehrCode = preferences.getString("weather_code", "");
		String address = "http://www.weather.com.cn/data/cityinfo/" + weatehrCode + ".html";
		
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				Utility.handleWeatherResponse(AutoUpdateService.this, response);
			}
			
			@Override
			public void onError(Exception e) {
				e.printStackTrace();
			}
		});
	}

	
	
}
