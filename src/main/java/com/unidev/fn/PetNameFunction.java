package com.unidev.fn;

import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;

public class PetNameFunction {

    public Map handleRequest(String input) {

        try {
            OkHttpClient client = createHttpClient();
            Request request = createRequest("gfhfghfg");
            Response response = client.newCall(request).execute();
            String body = response.body().string();
            return parseValues(body);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of();
        }
    }


    private OkHttpClient createHttpClient() {
        return new OkHttpClient();
    }

    private Request createRequest(String seedName) {
        HttpUrl.Builder urlBuilder
            = HttpUrl.parse("http://gangstaname.com/names/pet/generate").newBuilder();
        urlBuilder.addQueryParameter("name", seedName);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        return request;
    }

    private Map<String, String> parseValues(String body) {
        String name = StringUtils.substringBetween(body, "<h2 class='name'>", "</h2>");
        String usage = StringUtils.substringBetween(body, "<p class='usage'>Usage: &quot;", "&quot;</p>");
        return Map.of("name", name, "usage", usage);
    }


}