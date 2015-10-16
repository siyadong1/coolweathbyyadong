package com.coolweather.apps.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpUtil {
	
	public static void sendHttpRequest(final String address,final HttpCallbackListener listener) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpURLConnection connection = null;
				try {
					
					URL url = new URL(address);
					connection = (HttpURLConnection)url.openConnection();
					connection.setRequestMethod("GET");
					connection.setReadTimeout(3000);
					connection.setConnectTimeout(3000);
					InputStream input = connection.getInputStream();
					StringBuilder response = new StringBuilder();
					String line;
					BufferedReader reader = new BufferedReader(new InputStreamReader(input));
					while ((line = reader.readLine()) != null) {
						response.append(line);
					}
					if (listener != null) {
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					if (listener != null) {
						listener.onError(e);
					}
				} 
					finally{
						if (connection != null) {
							connection.disconnect();
						}
					}
			}
		}).start();
		
	}

}




