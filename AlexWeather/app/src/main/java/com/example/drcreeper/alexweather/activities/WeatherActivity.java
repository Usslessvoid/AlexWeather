package com.example.drcreeper.alexweather.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drcreeper.alexweather.R;
import com.example.drcreeper.alexweather.fragments.WeekListFragment;
import com.example.drcreeper.alexweather.models.WeatherData;
import com.example.drcreeper.alexweather.models.generated.WeatherAnswer;
import com.example.drcreeper.alexweather.utils.PicturePicker;
import com.example.drcreeper.alexweather.utils.Utils;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {
    TextView locationName;
    TextView status;
    TextView temp;
    TextView tempMin;
    TextView tempMax;
    TextView pressure;
    TextView humidity;
    TextView windSpeed;
    ImageView picture;
    ImageView windImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        final int townId = getIntent().getIntExtra("id",625144);

        locationName = (TextView)findViewById(R.id.location_name);
        status = (TextView)findViewById(R.id.status);
        temp = (TextView)findViewById(R.id.temp);
        tempMin = (TextView)findViewById(R.id.temp_min);
        tempMax = (TextView)findViewById(R.id.temp_max);
        pressure = (TextView)findViewById(R.id.pressure);
        humidity = (TextView)findViewById(R.id.humidity);
        windSpeed = (TextView)findViewById(R.id.wind);
        picture = (ImageView) findViewById(R.id.picture);
        windImage = (ImageView)findViewById(R.id.compas);
        Utils.getWeatherServise().getData(Utils.APPID,Utils.LOCALE,Utils.UNITS,townId).enqueue(new Callback<WeatherAnswer>() {
            @Override
            public void onResponse(Call<WeatherAnswer> call, Response<WeatherAnswer> response) {
                if(response.body()!=null){
                    WeatherData currentWeather = response.body().getWeatherData(townId);
                    showWeather(currentWeather);
                    WeekListFragment fragment = (WeekListFragment)getSupportFragmentManager().findFragmentById(R.id.list_weather);
                    currentWeather.setTime(0);
                    fragment.setCurrentWeather(currentWeather);
                }

            }
            @Override
            public void onFailure(Call<WeatherAnswer> call, Throwable t) {

            }
        });




    }

    public void showWeather(WeatherData currentWeather){
        locationName.setText(getIntent().getStringExtra("name"));
        status.setText(currentWeather.getState());
        temp.setText(Utils.normalizeNumber(currentWeather.getTemperature()));
        tempMin.setText(String.format(Locale.ENGLISH,"%.1f",currentWeather.getMinTemperature()));
        tempMax.setText(String.format(Locale.ENGLISH,"%.1f",currentWeather.getMaxTemperature()));
        pressure.setText(String.format("%.0f",currentWeather.getPressure()));
        humidity.setText(String.format("%.0f",currentWeather.getHumidity()));
        windSpeed.setText(String.format("%.1f",currentWeather.getWindSpeed()));
        windImage.setRotation((float) currentWeather.getWindAngle());
        picture.setImageResource(PicturePicker.getPicture(currentWeather.getPicture()));
    }
}
