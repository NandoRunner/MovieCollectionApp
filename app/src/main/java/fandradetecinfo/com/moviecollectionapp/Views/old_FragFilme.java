package fandradetecinfo.com.moviecollectionapp.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fandradetecinfo.com.moviecollectionapp.old_BackgroundTask;
import fandradetecinfo.com.moviecollectionapp.R;

public class old_FragFilme extends Fragment {

    Button btGetJson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.old_fragment_filme, container, false);

        btGetJson = (Button) vw.findViewById(R.id.btnGetJsonFilme);

        btGetJson.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     old_BackgroundTask bgt = new old_BackgroundTask(view.getRootView(), "FILME");
                     bgt.execute();
                 }
             }
        );
        return vw;
    }

    
	public static old_FragFilme newInstance(String param1) {
        old_FragFilme f = new old_FragFilme();
        Bundle args = new Bundle();
        args.putString("msg", param1);

        f.setArguments(args);
        return f;
    }
	

}
