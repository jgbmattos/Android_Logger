package br.com.gpslogger;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jgbma on 23/10/2016.
 */
class UpdateThingSpeakTask extends AsyncTask<Void, Void, String> {

    private Exception exception;
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_API_KEY_STRING = "api_key";
    private static final String THINGSPEAK_API_KEY = ""; //PUT YOUT API KEY HERE
    private static final String THINGSPEAK_LATITUDE = "field1";
    private static final String THINGSPEAK_LONGITUDE = "field2";
    private static final String THINGSPEAK_DATAHORA = "field3";
    private String latitude;
    private String longitude;
    private String dataHora;

    public UpdateThingSpeakTask(String latitude, String longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        dataHora = sdf.format(calendar.getTime());
    }
    protected void onPreExecute() {
    }

    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL(THINGSPEAK_UPDATE_URL + THINGSPEAK_API_KEY_STRING + "=" +
                    THINGSPEAK_API_KEY + "&" + THINGSPEAK_LATITUDE + "=" + latitude +
                    "&" + THINGSPEAK_LONGITUDE + "=" + longitude+"&" + THINGSPEAK_DATAHORA + "=" + dataHora);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        // We completely ignore the response
        // Ideally we should confirm that our update was successful
    }
}