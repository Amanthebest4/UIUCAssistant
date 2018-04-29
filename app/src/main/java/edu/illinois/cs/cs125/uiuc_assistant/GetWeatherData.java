package edu.illinois.cs.cs125.uiuc_assistant;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetWeatherData {

    public static String getWeatherDescription(int id) {
//        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
//        JsonArray weather = jsonObject.getAsJsonArray("weather");
//        return weather.get(0).getAsJsonObject().get("main").getAsString();
        switch (id) {
            case 200:
                return "thunderstorm with light rain";
            case 201:
                return "thunderstorm with rain";
            case 202:
                return "thunderstorm with heavy rain";
            case 210:
                return "light thunderstorm";
            case 211:
                return "thunderstorm";
            case 212:
                return "heavy thunderstorm";
            case 221:
                return "ragged thunderstorm";
            case 230:
                return "thunderstorm with light drizzle";
            case 231:
                return "thunderstorm with drizzle";
            case 232:
                return "thunderstorm with heavy drizzle";
            case 300:
                return "light intensity drizzle";
            case 301:
                return "drizzle";
            case 302:
                return "heavy intensity drizzle";
            case 310:
                return "light intensity drizzle rain";
            case 311:
                return "drizzle rain";
            case 312:
                return "heavy intensity drizzle rain";
            case 313:
                return "shower rain and drizzle";
            case 314:
                return "heavy shower rain and drizzle";
            case 321:
                return "shower drizzle";
            case 500:
                return "light rain";
            case 501:
                return "moderate rain";
            case 502:
                return "heavy intensity rain";
            case 503:
                return "very heavy rain";
            case 504:
                return "extreme rain";
            case 511:
                return "freezing rain";
            case 520:
                return "light intensity shower rain";
            case 521:
                return "shower rain";
            case 522:
                return "heavy intensity shower rain";
            case 531:
                return "ragged shower rain";
            case 600:
                return "light snow";
            case 601:
                return "snow";
            case 602:
                return "heavy snow";
            case 611:
                return "sleet";
            case 612:
                return "shower sleet";
            case 615:
                return "light rain and snow";
            case 616:
                return "rain and snow";
            case 620:
                return "light shower snow";
            case 621:
                return "shower snow";
            case 622:
                return "heavy shower snow";
            case 701:
                return "mist";
            case 711:
                return "smoke";
            case 721:
                return "haze";
            case 731:
                return "sand, dust whirls";
            case 741:
                return "fog";
            case 751:
                return "sand";
            case 761:
                return "dust";
            case 762:
                return "volcanic ash";
            case 771:
                return "squalls";
            case 781:
                return "tornado";
            case 800:
                return "clear sky";
            case 801:
                return "few clouds";
            case 802:
                return "scattered clouds";
            case 803:
                return "broken clouds";
            case 804:
                return "overcast clouds";
                default:
                    return "unknown";

        }
    }

    public static String getWeatherIcon(int id) {
//        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
//        JsonArray weather = jsonObject.getAsJsonArray("weather");
//        return weather.get(0).getAsJsonObject().get("main").getAsString();
        switch (id) {
            case 200:
                return "thunderstorm";
            case 201:
                return "thunderstorm";
            case 202:
                return "thunderstorm";
            case 210:
                return "lightning";
            case 211:
                return "lightning";
            case 212:
                return "lightning";
            case 221:
                return "lightning";
            case 230:
                return "storm_showers";
            case 231:
                return "storm_showers";
            case 232:
                return "storm_showers";
            case 300:
                return "sprinkles";
            case 301:
                return "sprinkles";
            case 302:
                return "sprinkles";
            case 310:
                return "rain-mix";
            case 311:
                return "rainMix";
            case 312:
                return "rainMix";
            case 313:
                return "rainMix";
            case 314:
                return "rainMix";
            case 321:
                return "rainMix";
            case 500:
                return "rain";
            case 501:
                return "rain";
            case 502:
                return "rain";
            case 503:
                return "rain";
            case 504:
                return "rain";
            case 511:
                return "sleet";
            case 520:
                return "showers";
            case 521:
                return "showers";
            case 522:
                return "showers";
            case 531:
                return "showers";
            case 600:
                return "snow";
            case 601:
                return "snow";
            case 602:
                return "snow";
            case 611:
                return "sleet";
            case 612:
                return "sleet";
            case 615:
                return "sleet";
            case 616:
                return "sleet";
            case 620:
                return "sleet";
            case 621:
                return "sleet";
            case 622:
                return "sleet";
            case 701:
                return "dust";
            case 711:
                return "smoke";
            case 721:
                return "day_haze";
            case 731:
                return "dust";
            case 741:
                return "fog";
            case 751:
                return "dust";
            case 761:
                return "dust";
            case 762:
                return "volcano";
            case 771:
                return "strong_wind";
            case 781:
                return "tornado";
            case 800:
                return "day_sunny";
            case 801:
                return "day_sunny_overcast";
            case 802:
                return "day_sunny_overcast";
            case 803:
                return "cloud";
            case 804:
                return "cloudy";
            default:
                return "";

        }
    }

    public static int getWeatherId(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonArray weather = jsonObject.getAsJsonArray("weather");
        return Integer.parseInt(weather.get(0).getAsJsonObject().get("id").getAsString());
    }

    public static int getCurrentTemp(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject weather = jsonObject.getAsJsonObject("main");
        //Log.d("check",weather.getAsString());
        float temp = Float.parseFloat(weather.get("temp").getAsString());
        temp -= 273.15;
        return (int)temp;
    }

    public static int getMaxTemp(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject weather = jsonObject.getAsJsonObject("main");
        float temp = Float.parseFloat(weather.get("temp_max").getAsString());
        temp -= 273.14;
        return (int)temp;
    }

    public static int getMinTemp(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject weather = jsonObject.getAsJsonObject("main");
        float temp = Float.parseFloat(weather.get("temp_min").getAsString());
        temp -= 273.14;
        return (int)temp;
    }

    public static float getPressure(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject weather = jsonObject.getAsJsonObject("main");
        float pressure = Float.parseFloat(weather.get("pressure").getAsString());
        pressure /= 1013.2501;
        return pressure;
    }

    public static int getHumidity(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonObject weather = jsonObject.getAsJsonObject("main");
        return Integer.parseInt(weather.get("humidity").getAsString());
    }
}
