package com.omar_hidrogo_local.micasa;

import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.omar_hidrogo_local.micasa.adaptador.PageAdapter;
import com.omar_hidrogo_local.micasa.fragment.Fragment_RecyclerView;

import java.util.ArrayList;

public class Splash_screen extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbarbar);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        //establecer informacion de accion bar
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        //quitar el titulo por defecto al actionbar
        if(toolbar != null){
            bar.setDisplayShowTitleEnabled(false);
        }
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment_RecyclerView());
        return fragments;
    }


    private void setUpViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()) {
        });
    }
}
