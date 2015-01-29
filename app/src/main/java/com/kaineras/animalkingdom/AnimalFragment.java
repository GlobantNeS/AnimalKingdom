package com.kaineras.animalkingdom;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AnimalFragment extends Fragment {

    View v=null;
    ImageButton ibAnimal=null;
    ImageButton ibDonkey=null;
    ImageButton ibCat=null;
    ImageButton ibSuri=null;
    ImageButton ibSnake=null;
    ImageButton ibTiger=null;
    boolean Tablet=false;

    public AnimalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_animal, container, false);
        prepareButtons();
        return v;
    }



    public void prepareButtons()
    {
        preparedButtonListener(ibAnimal,"0");
        preparedButtonListener(ibDonkey,"1");
        preparedButtonListener(ibCat,"2");
        preparedButtonListener(ibSuri,"3");
        preparedButtonListener(ibSnake,"4");
        preparedButtonListener(ibTiger,"5");
    }

    private void preparedButtonListener(ImageButton btmemp,final String val) {
        switch (val)
        {
            case "0":
                btmemp=(ImageButton)v.findViewById(R.id.imageButton);
                break;
            case "1":
                btmemp=(ImageButton)v.findViewById(R.id.imageButton2);
                break;
            case "2":
                btmemp=(ImageButton)v.findViewById(R.id.imageButton3);
                break;
            case "3":
                btmemp=(ImageButton)v.findViewById(R.id.imageButton4);
                break;
            case "4":
                btmemp=(ImageButton)v.findViewById(R.id.imageButton5);
                break;
            case "5":
                btmemp=(ImageButton)v.findViewById(R.id.imageButton6);
                break;

        }

        btmemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DescFragment nf=new DescFragment();
                Bundle bundle=new Bundle();
                bundle.putString("INDEX", val);
                Tablet=getArguments().getBoolean("TABLET");
                bundle.putBoolean("TABLET",Tablet);
                nf.setArguments(bundle);
                if(Tablet)
                {
                    loadFragment(nf, R.id.container_desc);
                }
                else
                {
                    loadFragment(nf, R.id.main_container);
                }

            }
        });
    }

    public void loadFragment(Fragment f,int container)
    {
        FragmentTransaction fragmentTransaction;
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(container, f);
        transaction.addToBackStack("Start");
        transaction.commit();
    }

}
