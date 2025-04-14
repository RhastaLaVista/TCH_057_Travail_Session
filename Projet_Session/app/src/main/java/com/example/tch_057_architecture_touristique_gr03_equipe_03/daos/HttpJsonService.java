package com.example.tch_057_architecture_touristique_gr03_equipe_03.daos;


import com.example.tch_057_architecture_touristique_gr03_equipe_03.entite.Client;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpJsonService {
    private static final String URL_BASE = "http://10.0.2.2:3000/livres/";

    //is an array in case there are 2 similarly emailed accounts.
    //TODO: make sure only 1 account is fetched and that no others can exist with the same email.
    //Current fix: Max array size is 1, If more than 1, It'l return null and a console print will say there is more than 1 account with the same email.
    public List<Client> rechercherClient(String email) throws IOException, JSONException {
        String url = URL_BASE + "?";
        //finds and fetches specific email account data
        if (email != null && !email.isEmpty()) url += "id=" + email + "&";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        if (responseBody != null) {
            String jsonStr = responseBody.string();
            ObjectMapper mapper = new ObjectMapper();
            if(Arrays.asList((mapper.readValue(jsonStr, Client[].class))).size() <= 1) {
                return Arrays.asList(mapper.readValue(jsonStr, Client[].class));
            }
        }
        return null;
    }
}
