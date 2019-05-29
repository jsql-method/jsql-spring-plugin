package it.jsql.connector.service;

import com.google.gson.Gson;
import it.jsql.connector.dto.JSQLConfig;
import it.jsql.connector.exceptions.JSQLException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSQLConnector {

    private static final String API_URL = "https://provider.jsql.it/api/jsql";

    public static String callSelect(Object data, JSQLConfig jsqlConfig) throws JSQLException {
        return call(API_URL + "/select", data, jsqlConfig);
    }

    public static String callDelete(Object data, JSQLConfig jsqlConfig) throws JSQLException {
        return call(API_URL + "/delete", data, jsqlConfig);
    }

    public static String callUpdate(Object data, JSQLConfig jsqlConfig) throws JSQLException {
        return call(API_URL + "/update", data, jsqlConfig);
    }

    public static String callInsert(Object data, JSQLConfig jsqlConfig) throws JSQLException {
        return call(API_URL + "/insert", data, jsqlConfig);
    }

    public static String callRollback(Object data, JSQLConfig jsqlConfig) throws JSQLException {
        return call(API_URL + "/rollback", data, jsqlConfig);
    }

    public static String callCommit(Object data, JSQLConfig jsqlConfig) throws JSQLException {
        return call(API_URL + "/commit", data, jsqlConfig);
    }
    public static String call(String fullUrl, Object request, JSQLConfig jsqlConfig) throws JSQLException {

        HttpURLConnection conn = null;

        try {

            URL url = new URL(fullUrl);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Api-Key", jsqlConfig.getApiKey());
            conn.setRequestProperty("Dev-Key", jsqlConfig.getDevKey());
            conn.setUseCaches(false);

            OutputStream os = conn.getOutputStream();

            if(request != null){
                os.write(new Gson().toJson(request).getBytes());
            }

            System.out.println("request: " + new Gson().toJson(request));

            os.flush();

            System.out.println("fullUrl: " + fullUrl);
            System.out.println("conn.getResponseCode(): " + conn.getResponseCode());

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {

                System.out.println("conn: " + conn);

                System.out.println("conn.getErrorStream(): " + conn.getErrorStream());

                InputStream inputStream = conn.getErrorStream();

                if (inputStream == null) {
                    conn.disconnect();
                    throw new JSQLException("HTTP error code : " + conn.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
                StringBuilder builder = new StringBuilder();
                while (br.ready()) {
                    builder.append(br.readLine());
                }

                conn.disconnect();

                String response = builder.toString().trim();

                if (response.length() > 0 && response.contains("<div>")) {
                    response = response.substring(response.lastIndexOf("</div><div>") + 11, response.lastIndexOf("</div></body></html>"));
                }

                throw new JSQLException("HTTP error code : " + conn.getResponseCode() + "\nHTTP error message : " + response);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            StringBuilder builder = new StringBuilder();

            while (br.ready()) {
                builder.append(br.readLine());
            }

            conn.disconnect();

            return builder.toString();


        } catch (Exception e) {
            e.printStackTrace();
            throw new JSQLException("IOException JSQLConnector.call: " + e.getMessage());
        } finally {

            if (conn != null) {
                conn.disconnect();
            }

        }

    }

}
