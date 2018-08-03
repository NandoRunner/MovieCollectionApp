package fandradetecinfo.com.moviecollectionapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import fandradetecinfo.com.moviecollectionapp.Views.FragAtor;
import fandradetecinfo.com.moviecollectionapp.Views.FragConfig;
import fandradetecinfo.com.moviecollectionapp.Views.FragDiretor;
import fandradetecinfo.com.moviecollectionapp.Views.FragFilme;
import fandradetecinfo.com.moviecollectionapp.Views.FragFilmeVisto;
import fandradetecinfo.com.moviecollectionapp.Views.FragGenero;

public class MainActivity extends AppCompatActivity {

    public static String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        PrefsHandler prefs = new PrefsHandler(getBaseContext());

        baseUrl = prefs.getBaseURL();
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
                    return FragConfig.newInstance("Config");
                default:
                    return FragConfig.newInstance("Filmes");
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    }

}
