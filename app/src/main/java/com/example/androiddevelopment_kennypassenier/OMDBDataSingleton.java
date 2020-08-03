package com.example.androiddevelopment_kennypassenier;// Inspiratie komt uit het voorbeeld werkcollege te vinden op https://ehb.instructure.com/courses/14750/files/644855?module_item_id=103899

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OMDBDataSingleton {

    private OMDBDataSingleton(){
        // Private constructor to prevent instantiation
    }

    private static class OMDBSingletonHelper{
        private static final OMDBDataSingleton INSTANCE = new OMDBDataSingleton();
    }

    public static OMDBDataSingleton getInstance(){
        return OMDBSingletonHelper.INSTANCE;
    }

    protected String downloadPlainText(String uri){
        StringBuilder content = new StringBuilder();
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(uri);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        }
        catch(IOException e){
            return null;
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return content.toString();
    }






}
