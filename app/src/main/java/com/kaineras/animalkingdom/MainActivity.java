package com.kaineras.animalkingdom;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


public class MainActivity extends ActionBarActivity {

    FragmentTransaction fragmentTransaction;
    boolean Tablet=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.container_desc)!=null)
            Tablet=true;
        loadFragment(new AnimalFragment(), R.id.main_container);
    }

    public void loadFragment(Fragment f,int container)
    {
        Bundle bundle=new Bundle();
        bundle.putBoolean("TABLET",Tablet);
        f.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(container, f);
        transaction.addToBackStack("Start");
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (getFragmentManager().getBackStackEntryCount() == 1)
            {
                this.finish();
                return false;
            }
            else
            {
                if(!Tablet) {
                    getFragmentManager().popBackStack();
                    removeCurrentFragment();
                    return false;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void removeCurrentFragment()
    {

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        Fragment currentFrag =  getFragmentManager().findFragmentById(R.id.main_container);

        if (currentFrag!=null)
            transaction.remove(currentFrag);
        transaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
