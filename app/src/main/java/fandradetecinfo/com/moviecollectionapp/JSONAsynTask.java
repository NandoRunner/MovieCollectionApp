package fandradetecinfo.com.moviecollectionapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fandradetecinfo.com.moviecollectionapp.Models.DadosFilme;

/**
 * Created by Fernando on 03/05/2018.
 */

public class JSONAsynTask extends AsyncTask<String, Void, Boolean> {
    String result;
    ProgressDialog dialog;

    Activity activity;
    ArrayList<DadosFilme> list;
    DadosFilmeAdapter adapter;

    public JSONAsynTask(Activity activity, ArrayList<DadosFilme> list, DadosFilmeAdapter adapter) {
        this.activity = activity;
        this.list = list;
        this.adapter = adapter;
    }

    public JSONAsynTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(activity);
        if (BuildConfig.IS_DEBUG)
            if (adapter != null) {

                dialog.setMessage("Carregando, aguarde...");
                dialog.setTitle("Conectando ao Bando de Dados");
                dialog.show();
                dialog.setCancelable(false);
            }
    }

    @Override
    protected Boolean doInBackground(String... params) {

        try {
            // tela / campo / orderação
            String state = params[6] + "-" + params[1] + "-" + params[5] +(params[4].isEmpty() ? "" : "-" + params[4]);

            if (MainActivity.mDado.get(state) != null)
            {
                if (this.list != null)
                    this.buildList(MainActivity.mDado.get(state), params);
                return true;
            }

            String url = buildURL(params);

            HttpGet httppost = new HttpGet(url);
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);

            int status = response.getStatusLine().getStatusCode();

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity, HTTP.UTF_8);

                JSONObject jsono = new JSONObject(data);
                JSONArray jarray = jsono.getJSONArray("server_response");

                MainActivity.mDado.put(state, jarray);

                if (this.list != null)
                    this.buildList(jarray, params);

                return true;
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String buildURL(String... params)
    {
        String ret = params[0];

        if (params.length == 8)
        {
            ret += "/" + params[4].replace("+", "&nbsp;");
        }

        if (params[1] != null)
            ret += "/" + params[1] +"/" + params[5];

        return ret;
    }

    private void buildList(JSONArray jarray, String... params) {
        try {
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject object = jarray.getJSONObject(i);

                DadosFilme df = new DadosFilme();

                if (params[6].equals("mov") || params[6].equals("movw"))
                    df.setAno(object.getString("ano"));
                df.setNome(object.getString(params[2]));
                df.setFilmes(object.getString(params[3]));

                list.add(df);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void onPostExecute(Boolean result) {

        dialog.dismiss();
        try {
            if (this.list != null)
                adapter.notifyDataSetChanged();
        }
        catch (Exception ex)
        {
            Toast.makeText(activity, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        if(result == false)
            Toast.makeText(activity, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

    }
}
