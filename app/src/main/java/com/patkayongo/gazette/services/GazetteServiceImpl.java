package com.patkayongo.gazette.services;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.patkayongo.gazette.domain.Gazette;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GazetteServiceImpl implements GazetteService {

    private RequestQueue requestQueue;

    public GazetteServiceImpl(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void searchGazettes(String searchTerm, final GazetteSearchDelegate gazetteSearchDelegate) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, this.getUrl(searchTerm), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    List<Gazette> gazettes = new ArrayList<>();

                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i ++) {
                        JSONObject jsonObject = results.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        String sourceUrl = jsonObject.getString("source_url");

                        JSONObject resultDetail = jsonObject.getJSONObject("records").getJSONArray("results").getJSONObject(0);
                        String text = resultDetail.getString("text");
                        gazettes.add(new Gazette(title, text, sourceUrl));

                    }

                    gazetteSearchDelegate.searchComplete(gazettes);

                } catch (Exception ex) {
                    gazetteSearchDelegate.searchComplete(null);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                gazetteSearchDelegate.searchComplete(null);
            }
        });

        this.requestQueue.add(jsonObjectRequest);
    }

    private String getUrl(String searchTerm) {
        return String.format("https://search.opengazettes.org.za/api/1/query?facet=entities&facet=collections&limit=20&offset=0&q=%s&snippet=140", searchTerm);
    }
}
