package com.example.tp3partie2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;

import okhttp3.Headers;

public class AddEvenementActivite extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evenement);

        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("features", "  [{ " +
                "                       \"attributes\": { " +
                        "                 \"Titre\": \"Exemple\", " +
                        "                 \"Description\": \"Description de l'exemple\"," +
                        "                 \"Date\": \"3 novembre 2025\","  +
                        "                 \"Equipe\": \"Elie\", " +
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
