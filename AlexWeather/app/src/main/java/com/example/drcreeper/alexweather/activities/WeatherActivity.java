package com.example.drcreeper.alexweather.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.models.WeatherData;
import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;
import com.example.drcreeper.alexweather.utils.PicturePicker;
import com.example.drcreeper.alexweather.utils.Utils;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        TextView locationName = (TextView)findViewById(R.id.location_name);
        final TextView status = (TextView)findViewById(R.id.status);
        final TextView temp = (TextView)findViewById(R.id.temp);
        final TextView tempMin = (TextView)findViewById(R.id.temp_min);
        final TextView tempMax = (TextView)findViewById(R.id.temp_max);
        final TextView pressure = (TextView)findViewById(R.id.pressure);
        final TextView humidity = (TextView)findViewById(R.id.humidity);
        final TextView windSpeed = (TextView)findViewById(R.id.wind);
        final ImageView picture = (ImageView) findViewById(R.id.picture);
        final ImageView windImage = (ImageView)findViewById(R.id.compas);
        Utils.getWeatherServise().getData(Utils.APPID,Utils.LOCALE,Utils.UNITS,625144).enqueue(new Callback<WeatherAnswer>() {
            @Override
            public void onResponse(Call<WeatherAnswer> call, Response<WeatherAnswer> response) {
                if(response.body()!=null){
                    WeatherData currentWeather = response.body().getWeatherData(625114);
                    status.setText(currentWeather.getState());
                    temp.setText(Double.toString(currentWeather.getTemperature()));
                    tempMin.setText(String.format(Locale.ENGLISH,"%.1f",currentWeather.getMinTemperature()));
                    tempMax.setText(String.format(Locale.ENGLISH,"%.1f",currentWeather.getMaxTemperature()));
                    pressure.setText(String.format("%.0f",currentWeather.getPressure()));
                    humidity.setText(String.format("%.0f",currentWeather.getHumidity()));
                    windSpeed.setText(String.format("%.1f",currentWeather.getWindSpeed()));
                    windImage.setRotation((float) currentWeather.getWindAngle());
                    picture.setImageResource(PicturePicker.getPicture(currentWeather.getPicture()));
                }
            }

            @Override
            public void onFailure(Call<WeatherAnswer> call, Throwable t) {

            }
        });




    }
}
