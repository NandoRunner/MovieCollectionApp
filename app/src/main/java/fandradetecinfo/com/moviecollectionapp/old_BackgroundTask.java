package fandradetecinfo.com.moviecollectionapp;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Fernando on 03/05/2018.
 */

public class old_BackgroundTask extends AsyncTask<Void, Void, String> {
    String JSON_STRING;
    String JSON_URL;
    private View rootView;
    String tipo;
    TextView json;

    public old_BackgroundTask(View rootView, String tipo)
    {
        this.rootView = rootView;
        this.tipo = tipo;
    }
    @Override
    protected void onPreExecute() {

        switch (tipo)
        {
            case "FILME":
                JSON_URL ="http://nandorunner1-001-site1.htempurl.com/mc_filmes.php";
                json = (TextView) rootView.findViewById(R.id.txtJsonFilme);
                break;
        }

    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            StringBuilder JSON_DATA = new StringBuilder();
            URL url = new URL(JSON_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((JSON_STRING = reader.readLine())!=null) {
                JSON_DATA.append(JSON_STRING).append("\n");
            }
            return JSON_DATA.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        json.setText(result);
    }
}

