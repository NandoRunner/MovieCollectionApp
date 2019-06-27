package fandradetecinfo.com.moviecollectionapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        PrefsHandler prefs = new PrefsHandler(getBaseContext());

        baseUrl = prefs.getBaseURL();

        if (baseUrl.isEmpty() || baseUrl == null) {
            baseUrl = getString(R.string.url_base);
            prefs.salvar(baseUrl);
        }
        initDadosTela();
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
