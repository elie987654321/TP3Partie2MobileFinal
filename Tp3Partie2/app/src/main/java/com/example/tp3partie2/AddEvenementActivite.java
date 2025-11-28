package com.example.tp3partie2;

import static android.widget.Toast.LENGTH_SHORT;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.time.LocalDateTime;

import okhttp3.Headers;

public class AddEvenementActivite extends AppCompatActivity
{
    private static final int COARSE_LOCATION_PERMISSION_CODE = 1;
    private static final int FINE_LOCATION_PERMISSION_CODE = 2;

    private TextView inputTitre;
    private TextView inputDescription;
    private TextView inputEquipe;
    private Spinner inputType;

    AsyncHttpClient client;

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
        buttonSend = findViewById(R.id.addEvenementBoutonEnvoyer);

        ArrayAdapter<String> adapterTypes = new ArrayAdapter<>(this, com.codepath.asynchttpclient.R.layout.support_simple_spinner_dropdown_item);
        adapterTypes.addAll(types);
        inputType.setAdapter(adapterTypes);

         client = new AsyncHttpClient();

         buttonSend.setOnClickListener((view) ->
         {
             ButtonSendPressed();
         });
    }

    private void ButtonSendPressed()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    FINE_LOCATION_PERMISSION_CODE);


            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_PERMISSION_CODE);

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                GetLocation();
            }
        }
        else
        {
            GetLocation();
        }
    }

    private void GetLocation() {

        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        try {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location ->
            {
                if (location != null) {
                    double lat = location.getLatitude();
                    double lng = location.getLongitude();

                    SendEvent(lat, lng);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void SendEvent(double lat, double lng)
    {

        LocalDateTime localDateTime = LocalDateTime.now();
        String dateString = localDateTime.getDayOfMonth() + " " + localDateTime.getMonth() + " " + localDateTime.getYear();

        RequestParams params = new RequestParams();
        params.put("features", "  [{ " +
                "                       \"attributes\": { " +
                "                 \"Titre\": \"" + inputTitre.getText().toString() + "\", " +
                "                 \"Description\": \"" + inputDescription.getText().toString() + "\"," +
                "                 \"Date\": \""  + dateString  +"\"," +
                "                 \"Equipe\": \"" + inputEquipe.getText().toString() + "\", " +
                "                 \"Type\":\"" + inputType.getSelectedItem() + "\"  }, " +
                "                 \"geometry\": { " +
                "                 \"spatialReference\": {" +
                "                 \"wkid\": 4326  }," +
                "                 \"x\":"  + -73.5 +  ", " +
                "                 \"y\": " +  46   + " } " +
                "                 }]");

        client.post("https://services3.arcgis.com/F77upWE9kmPKRMqm/ArcGIS/rest/services/Evenements/FeatureServer/0/addFeatures", params, ""
                , new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, String response) {
                        runOnUiThread(() ->
                                    {
                                        ShowMessage("L'evenement a bien été ajouté");
                                    }
                                );
                    }

                    @Override
                    public void onFailure(int statusCode, @Nullable Headers headers, String errorResponse, @Nullable Throwable throwable) {
                        runOnUiThread(() ->
                        {
                            ShowMessage("Erreur, l'evenement n'a pas pu être ajouté");
                        });
                    }
                });
    }

    private void ShowMessage(String message)
    {
        Toast.makeText(this, message, LENGTH_SHORT).show();
    }
}
