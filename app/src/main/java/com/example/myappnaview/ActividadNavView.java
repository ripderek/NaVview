package com.example.myappnaview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class ActividadNavView extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navView; DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_nav_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1 , menu);
        return true;
    }
    public void onClick(View view) {
        Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                 drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        if(menuItem.getItemId()==R.id.menu_opcion_1) {
            fragment = new FragmentProductos();
            fragmentTransaction = true;
        }
        else if (menuItem.getItemId()==R.id.menu_opcion_2) {
            fragment = new FragmentClientes();
            fragmentTransaction = true;
        }
        else if (menuItem.getItemId()==R.id.menu_opcion_3) {
            fragment = new FragmentVentas();
            fragmentTransaction = true;
        }
        else {
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.content_frame,fragment)
                   .commit();
            menuItem.setChecked(true);
            getSupportActionBar().setTitle(menuItem.getTitle());
        }
        /*
        switch (menuItem.getItemId()) {
            case R.id.menu_opcion_1:
                fragment = new FragmentProductos();
                fragmentTransaction = true;
                break;
            case R.id.menu_seccion_2:
                fragment = new FragmentClientes();
                fragmentTransaction = true;
                break;
            case R.id.menu_seccion_3:
                fragment = new FragmentVentas();
                fragmentTransaction = true;
                break;
        }
        */


        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
            menuItem.setChecked(true);
            getSupportActionBar().setTitle(menuItem.getTitle());
        }
        drawerLayout.closeDrawers();
        return true;
    }
}