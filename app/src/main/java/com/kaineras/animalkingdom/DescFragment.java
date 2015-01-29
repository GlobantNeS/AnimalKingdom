package com.kaineras.animalkingdom;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DescFragment extends Fragment {

    ArrayList habitat=new ArrayList();
    ArrayList desc=new ArrayList();
    ArrayList name=new ArrayList();
    View v=null;
    boolean Tablet=false;

    public DescFragment() {
        // Required empty public constructor
    }

    public void prepareHabitat()
    {
        habitat.add(getString(R.string.room));
        habitat.add(getString(R.string.all_world));
        habitat.add(getString(R.string.all_world));
        habitat.add(getString(R.string.africa));
        habitat.add(getString(R.string.africa));
        habitat.add(getString(R.string.asia));
    }

    public void prepareDesc()
    {
        desc.add(getString(R.string.animal));
        desc.add(getString(R.string.donkey));
        desc.add(getString(R.string.cat));
        desc.add(getString(R.string.suricate));
        desc.add(getString(R.string.snake));
        desc.add(getString(R.string.tiger));
    }

    public void prepareName()
    {
        name.add(getString(R.string.name_animal));
        name.add(getString(R.string.name_donkey));
        name.add(getString(R.string.name_cat));
        name.add(getString(R.string.name_suricate));
        name.add(getString(R.string.name_snake));
        name.add(getString(R.string.name_tiger));
    }

    public void fillFields(String index)
    {
        int i=Integer.parseInt(index);
        TextView tName=(TextView)v.findViewById(R.id.text_name);
        TextView tHab=(TextView)v.findViewById(R.id.text_desc_hab);
        TextView tDesc=(TextView)v.findViewById(R.id.text_desc);
        ImageView iAni=(ImageView)v.findViewById(R.id.imageDesc);
        tName.setText(String.valueOf(name.get(i)));
        tHab.setText(String.valueOf(habitat.get(i)));
        tDesc.setText(String.valueOf(desc.get(i)));
        String nameImage="";
        Bitmap bitmap=null;
        switch (index)
        {
            case "0":
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.animal);
            break;
            case "1":
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.donkey);
                break;
            case "2":
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
                break;
            case "3":
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.suri);
                break;
            case "4":
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.snake);
                break;
            case "5":
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tiger);
                break;
        }
        iAni.setImageBitmap(bitmap);
        Button button=(Button)v.findViewById(R.id.button);
        Tablet=getArguments().getBoolean("TABLET");
        if(Tablet)
        {
            button.setEnabled(false);
            button.setVisibility(View.INVISIBLE);
        }
        else {
            if(index.compareTo("5")==0) {
                button.setEnabled(false);
            }
            else {
                button.setEnabled(true);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loadFragment(new DescFragment(),R.id.main_container);
                    }
                    public void loadFragment(Fragment f,int container)
                    {
                        FragmentTransaction fragmentTransaction;
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("TABLET",getArguments().getBoolean("TABLET"));
                        bundle.putString("INDEX", String.valueOf((Integer.parseInt(getArguments().getString("INDEX")) + 1)));
                        f.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(container, f);
                        transaction.addToBackStack("Start");
                        transaction.commit();
                    }
                });
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_desc, container, false);
        prepareHabitat();
        prepareDesc();
        prepareName();
        fillFields(getArguments().getString("INDEX"));
        return v;
    }
}
