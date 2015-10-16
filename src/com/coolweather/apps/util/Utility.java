package com.coolweather.apps.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.apps.db.CoolWeatherDB;
import com.coolweather.apps.model.City;
import com.coolweather.apps.model.County;
import com.coolweather.apps.model.Province;

public class Utility {

	/**
	 * 解析和处理服务器返回的省级数据
	 * 
	 * @param coolWeatherDB
	 * @param response
	 * @return
	 */

	public synchronized static boolean handleProvincesResponse(
			CoolWeatherDB coolWeatherDB, String response) {

		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析和处理服务器返回的市级数据
	 * @param coolWeatherDB
	 * @param response
	 * @param provinecId
	 * @return
	 */
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinecId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String p: allCities) {
					String[] array = p.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinecId);
					coolWeatherDB.saveCity(city);
				}
				
				return true;
			}
		}
		return false;
	}

	/**
	 * 解析和处理服务器返回的县级数据
	 * @param coolWeatherDB
	 * @param response
	 * @param cityId
	 * @return
	 */
	
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] cities = response.split(",");
			if (cities != null && cities.length > 0) {
				for (String p:cities) {
					String[] array = p.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}return false;
	}
}
