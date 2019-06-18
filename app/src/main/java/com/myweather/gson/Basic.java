package com.myweather.gson;

import com.google.gson.annotations.SerializedName;

public class Basic {
    @SerializedName("city")
     public String citynName;
    @SerializedName("id")
    public String weatherId;

    public Update update;
    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
