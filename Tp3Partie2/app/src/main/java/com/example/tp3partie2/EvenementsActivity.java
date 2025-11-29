package com.example.tp3partie2;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Headers;

public class EvenementsActivity extends AppCompatActivity
{
    private ListView listViewEvenement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenements);

        listViewEvenement = findViewById(R.id.listViewEvenements);

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("https://services3.arcgis.com/F77upWE9kmPKRMqm/ArcGIS/rest/services/Evenements/FeatureServer/0/query?where=equipe+%3D+'Elie'&objectIds=&geometry=&geometryType=esriGeometryEnvelope&inSR=&spatialRel=esriSpatialRelIntersects&resultType=none&distance=0.0&units=esriSRUnit_Meter&outDistance=&relationParam=&returnGeodetic=false&outFields=*&returnGeometry=true&featureEncoding=esriDefault&multipatchOption=xyFootprint&maxAllowableOffset=&geometryPrecision=&outSR=&defaultSR=&datumTransformation=&applyVCSProjection=false&returnIdsOnly=false&returnUniqueIdsOnly=false&returnCountOnly=false&returnExtentOnly=false&returnQueryGeometry=false&returnDistinctValues=false&cacheHint=false&collation=&orderByFields=&groupByFieldsForStatistics=&returnAggIds=false&outStatistics=&having=&resultOffset=&resultRecordCount=&returnZ=false&returnM=false&returnTrueCurves=false&returnExceededLimitFeatures=true&quantizationParameters=&sqlFormat=none&f=pjson&token=",
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        try
                        {
                            JSONObject jsonObject = json.jsonObject;
                            JSONArray jsonArrayFeatures = jsonObject.getJSONArray("features");

                            ArrayList<Evenement> listEvenement = new ArrayList<>();

                            for(int i = 0; i < jsonArrayFeatures.length(); i++)
                            {
                                JSONObject jsonEvenement = jsonArrayFeatures.getJSONObject(i);

                                JSONObject jsonAttributes = jsonEvenement.getJSONObject("attributes");

                                Evenement evenement = new Evenement();
                                evenement.setTitre(jsonAttributes.getString("Titre"));
                                evenement.setDescription(jsonAttributes.getString("Description"));
                                evenement.setDate(jsonAttributes.getString("Date"));
                                evenement.setEquipe(jsonAttributes.getString("Equipe"));
                                evenement.setType(jsonAttributes.getString("Type"));

                                JSONObject jsonGeometry =  jsonEvenement.getJSONObject("geometry");

                                evenement.setCoordoneeX(jsonGeometry.getDouble("x"));
                                evenement.setCoordoneeY(jsonGeometry.getDouble("y"));

                                listEvenement.add(evenement);


                            }

                            runOnUiThread(() ->
                            {
                                setListViewAdapter(listEvenement);
                            });
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                    }
                }
        );

    }

    private void setListViewAdapter(ArrayList<Evenement> listEvenements)
    {
        ItemEvenementAdapteur itemEvenementAdapter = new ItemEvenementAdapteur(this, listEvenements);
        listViewEvenement.setAdapter(itemEvenementAdapter);
    }
}
