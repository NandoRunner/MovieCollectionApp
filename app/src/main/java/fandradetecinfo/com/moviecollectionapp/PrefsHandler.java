package fandradetecinfo.com.moviecollectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PrefsHandler {

    Context context;
    SharedPreferences shp;

    public PrefsHandler(Context ctx) {
        this.context = ctx;
        String MINHAS_PREFS = "MOVIECOLLECTIONAPP_PREFS";
        shp = context.getSharedPreferences(MINHAS_PREFS, 0);
    }

    public String getBaseURL()
    {
        return shp.getString("base_url", "");
    }

    public void salvar(String base_url)
    {
        SharedPreferences.Editor editor = shp.edit();
        editor.putString("base_url", base_url);
        MainActivity.baseUrl = base_url;
        editor.apply();
    }
}
