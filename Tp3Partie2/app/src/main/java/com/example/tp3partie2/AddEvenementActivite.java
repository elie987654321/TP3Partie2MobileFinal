package com.example.tp3partie2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;

import java.time.LocalDateTime;

import okhttp3.Headers;

public class AddEvenementActivite extends AppCompatActivity
{
    private TextView inputTitre;
    private TextView inputDescription;
    private TextView inputEquipe;
    private Spinner inputType;

    private Button buttonSend;

    String[] types = {"Dechet" , "Accident", "Grafitis"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evenement);

        inputTitre = findViewById(R.id.addEvenementInputTitre);
        inputDescription = findViewById(R.id.addEvenementInputDescription);
        inputEquipe = findViewById(R.id.addEvenementInputEquipe);
        inputType = findViewById(R.id.addEvenementSpinnerType);

        ArrayAdapter<String> adapterTypes = new ArrayAdapter<>(this, com.codepath.asynchttpclient.R.layout.support_simple_spinner_dropdown_item);
    }

    private void SendEvent() {
        AsyncHttpClient client = new AsyncHttpClient();

        LocalDateTime localDateTime = LocalDateTime.now();
        String dateString =localDateTime.getDayOfMonth() + " " + localDateTime.getMonth() + " " + localDateTime.getYear();

        RequestParams params = new RequestParams();
        params.put("features", "  [{ " +
                "                       \"attributes\": { " +
                "                 \"Titre\": \"" + inputTitre.getText().toString() + "\", " +
                "                 \"Description\": \"" + inputDescription.getText().toString() + "\"," +
                "                 \"Date\": \""  + dateString  +"\"," +
                "                 \"Equipe\": \"" + inputEquipe.getText().toString() + "\", " +
                "                 \"Type\":\"Dechet\"  }, " +
                "                 \"geometry\": { " +
                "                 \"spatialReference\": {" +
                "                 \"wkid\": 4326  }," +
                "                 \"x\":-73.5, " +
                "                 \"y\": 46  } " +
                "                 }]");

        client.post("https://services3.arcgis.com/F77upWE9kmPKRMqm/ArcGIS/rest/services/Evenements/FeatureServer/0/addFeatures", params, ""
                , new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        int t = 0;
                    }

                    @Override
                    public void onFailure(int statusCode, @Nullable Headers headers, String errorResponse, @Nullable Throwable throwable) {
                        int t = 0;
                    }

                });
    }
}
