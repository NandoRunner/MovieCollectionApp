package fandradetecinfo.com.moviecollectionapp;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fandradetecinfo.com.moviecollectionapp.Models.DadosFilme;

public class DadosFilmeAdapter extends ArrayAdapter<DadosFilme> {
    ArrayList<DadosFilme> list;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;
    String _tela;

    public DadosFilmeAdapter(Context context, int resource, ArrayList<DadosFilme> objects, String nomeTela) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        list = objects;
        _tela = nomeTela;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.txtNome = (TextView) v.findViewById(R.id.txtNome);
            holder.txtAno = (TextView) v.findViewById(R.id.txtAno);
            holder.txtFilmes = (TextView) v.findViewById(R.id.txtFilmes);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        if (holder.txtAno != null)
            holder.txtAno.setText(list.get(position).getAno());

        holder.txtNome.setText(list.get(position).getNome());
        holder.txtFilmes.setText(list.get(position).getFilmes());

        holder.txtNome.setTextColor(Color.BLACK);
        holder.txtFilmes.setTextColor(v.getResources().getColor(R.color.colorColZGFilme));

        if (position % 2 != 1) {

            int i = MainActivity.mTela.get(_tela).getList_row_bg();
            if (i != 0)
            {
                v.setBackgroundColor(v.getResources().getColor(MainActivity.mTela.get(_tela).getList_row_bg()));
            }
            else {
                v.setBackgroundColor(v.getResources().getColor(R.color.colorBGFilme));
            }
        } else {
            v.setBackgroundColor(Color.WHITE);
        }

        return v;

    }

    static class ViewHolder {

        public TextView txtNome;
        public TextView txtAno;
        public TextView txtFilmes;
    }
}
