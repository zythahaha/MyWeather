package com.myweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.myweather.db.City;
import com.myweather.db.County;
import com.myweather.db.Province;
import com.myweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
/* *
 解析和处理服务器返回的省级数据
*
* */
public static boolean handleProvinceResponse(String response){
    if (!TextUtils.isEmpty(response)){
        try {
            JSONArray allPronvince=new JSONArray(response);
            for (int i=0;i<allPronvince.length();i++){
                JSONObject provinceObject = allPronvince.getJSONObject(i);
                Province province=new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode((provinceObject.getInt("id")));
                province.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    return false;
}
/* *
 解析和处理服务器返回的市级数据
*
* */
public static boolean handleCityResponse(String response,int provinceId){
    if (!TextUtils.isEmpty(response)){
        try {
            JSONArray allCities=new JSONArray(response);
            for (int i=0;i<allCities.length();i++){
                JSONObject cityObjetct = allCities.getJSONObject(i);
                City city=new City();
                city.setCityName(cityObjetct.getString("name"));
                city.setCityCode((cityObjetct.getInt("id")));
                city.save();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    return false;
}
    /* *
     解析和处理服务器返回的县级数据
    *
    * */
    public static boolean handleCountyResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try {
                JSONArray allCountoes=new JSONArray(response);
                for (int i=0;i<allCountoes.length();i++){
                    JSONObject countyObjetct = allCountoes.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyObjetct.getString("name"));
                    county.setWeatherId((countyObjetct.getString("weather_id")));
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     *将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String reponse){
        try {
            JSONObject jsonObject=new JSONObject(reponse);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather");
            String weatherContent=jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
