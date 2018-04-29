package edu.illinois.cs.cs125.uiuc_assistant;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class WeatherTab extends Fragment{

    private static RequestQueue requestQueue;
    private final LatLng champaign = new LatLng(40.1070, -88.2272);
    ImageView icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weathertab, container, false);

        requestQueue = Volley.newRequestQueue(getActivity());

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://api.openweathermap.org/data/2.5/weather?lat=" + champaign.latitude + "&lon=" + champaign.longitude + "&APPID=4a4841d8e4dca36b76d16202b3522794",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            Log.d("check", response.toString());
//                            //Log.d("check", GetWeatherData.getWeatherType(response.toString()));
//                            Log.d("check", GetWeatherData.getWeatherId(response.toString()) + "");
                            int id = GetWeatherData.getWeatherId(response.toString());
                            String description = GetWeatherData.getWeatherDescription(id);
                            String iconLocation = "@drawable/" + GetWeatherData.getWeatherIcon(id);
                            //Log.d("check", iconURL);

                            icon = (ImageView) getActivity().findViewById(R.id.weather_icon);

                            int imageResource = getResources().getIdentifier(iconLocation, null, getActivity().getPackageName());
                            icon.setImageResource(imageResource);

                            TextView descriptionBox = (TextView) getActivity().findViewById(R.id.description);
                            descriptionBox.setText(description);

                            TextView currentTemp = (TextView) getActivity().findViewById(R.id.currentTemp);
                            currentTemp.setText(GetWeatherData.getCurrentTemp(response.toString()) + "\u00b0C");
                            TextView currentTempF = (TextView) getActivity().findViewById(R.id.currentTemp2);
                            currentTempF.setText(((GetWeatherData.getCurrentTemp(response.toString()) * 9 / 5) + 32) + "\u00b0F");

                            TextView maxTemp = (TextView) getActivity().findViewById(R.id.maxTemp);
                            maxTemp.setText(GetWeatherData.getMaxTemp(response.toString()) + "\u00b0C");
                            TextView maxTempF = (TextView) getActivity().findViewById(R.id.maxTemp2);
                            maxTempF.setText(((GetWeatherData.getMaxTemp(response.toString()) * 9 / 5) + 32) + "\u00b0F");

                            TextView minTemp = (TextView) getActivity().findViewById(R.id.minTemp);
                            minTemp.setText(GetWeatherData.getMinTemp(response.toString()) + "\u00b0C");
                            TextView minTempF = (TextView) getActivity().findViewById(R.id.minTemp2);
                            minTempF.setText(((GetWeatherData.getMinTemp(response.toString()) * 9 / 5) + 32) + "\u00b0F");

                            TextView pressure = (TextView) getActivity().findViewById(R.id.pressure);
                            pressure.setText(GetWeatherData.getPressure(response.toString()) + " Bar");
                            TextView humidity = (TextView) getActivity().findViewById(R.id.humidity);
                            humidity.setText(GetWeatherData.getHumidity(response.toString()) + "%");


                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError error) {
                    Log.w("check", error.toString());
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootView;
    }


}