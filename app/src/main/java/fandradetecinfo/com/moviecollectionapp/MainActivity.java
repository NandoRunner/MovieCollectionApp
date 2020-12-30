package fandradetecinfo.com.moviecollectionapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import fandradetecinfo.com.moviecollectionapp.Models.Tela;
import fandradetecinfo.com.moviecollectionapp.Views.FragAtor;
import fandradetecinfo.com.moviecollectionapp.Views.FragConfig;
import fandradetecinfo.com.moviecollectionapp.Views.FragDiretor;
import fandradetecinfo.com.moviecollectionapp.Views.FragFilme;
import fandradetecinfo.com.moviecollectionapp.Views.FragFilmeVisto;
import fandradetecinfo.com.moviecollectionapp.Views.FragGenero;

public class MainActivity extends AppCompatActivity {

    public static String baseUrl;

    public static Map<String, Tela> mTela = new Hashtable<>();

    public static Map<String, JSONArray> mDado = new HashMap<>();

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        PrefsHandler prefs = new PrefsHandler(getBaseContext());

//        baseUrl = prefs.getBaseURL();
//
//        if (baseUrl.isEmpty() || baseUrl == null) {

            if (getString(R.string.is_local).equals("true"))
            {
                baseUrl = getString(R.string.url_base_local);
            }
            else {
                baseUrl = getString(R.string.url_base);
            }
            prefs.salvar(baseUrl);
//        }
        initDadosTela();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//
//        SpannableString s = new SpannableString(item.getTitle().toString());
//        s.setSpan(new ForegroundColorSpan(Color.BLUE), 0, s.length(), 0);
//        item.setTitle(s);

        switch (item.getItemId()) {
            case R.id.menu_item_ver:
                pager.setCurrentItem(0);
                return true;
            case R.id.menu_item_visto:
                pager.setCurrentItem(1);
                return true;
            case R.id.menu_item_genero:
                pager.setCurrentItem(2);
                return true;
            case R.id.menu_item_diretor:
                pager.setCurrentItem(3);
                return true;
            case R.id.menu_item_ator:
                pager.setCurrentItem(4);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void initDadosTela()
    {
        initTela("ato", R.color.colorAtoBG, "","/actors/v1/GetMovieByName");
        initTela("dir", R.color.colorDirBG,"","/directors/v1/GetMovieByName");
        initTela("gen", R.color.colorGenBG,"","/genres/v1/GetMovieByName");
        initTela("mov", R.color.colorBGFilme,"","");
        initTela("movw", R.color.colorBGFilme,"","");
    }

    private void initTela(String nome, int list_row_bg, String url_main, String url_second)
    {
        Tela t = new Tela();
        t.setList_row_bg(list_row_bg);
        t.setUrl_main(url_main);
        t.setUrl_second(url_second);
        t.setLoaded(false);
        mTela.put(nome, t);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch (pos) {

                case 0:
                    return FragFilme.newInstance("Filmes");
                case 1:
                    return FragFilmeVisto.newInstance("FilmesVistos");
                case 2:
                    return FragGenero.newInstance("Generos");
                case 3:
                    return FragDiretor.newInstance("Diretores");
                case 4:
                    return FragAtor.newInstance("Atores");
                case 5:
                    if (BuildConfig.BUILD_TYPE.equals("debug")){
                        return FragConfig.newInstance("Config");
                    }
                    else
                    {
                        return FragAtor.newInstance("Atores");
                    }

                default:
                    return FragConfig.newInstance("Filmes");
            }
        }

        @Override
        public int getCount() {

            if (BuildConfig.BUILD_TYPE.equals("debug")){
                return 6;
            }
            else
            {
                return 5;
            }
        }
    }

}
