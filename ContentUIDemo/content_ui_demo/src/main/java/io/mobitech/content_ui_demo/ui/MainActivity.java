package io.mobitech.content_ui_demo.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import io.mobitech.content_ui.fragments.FullNewsFragment;
import io.mobitech.content_ui.fragments.UserNewsFragment;
import io.mobitech.content_ui.interfaces.OnNewsClickListener;
import io.mobitech.content_ui_demo.R;

public class MainActivity extends AppCompatActivity implements OnNewsClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showUserNewsFragment();
    }

    private void showUserNewsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_ui_container, UserNewsFragment.newInstance(), UserNewsFragment.TAG);
        ft.commit();
    }

    private void showFullNewsFragment(String url) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.content_ui_container, FullNewsFragment.newInstance(url), FullNewsFragment.TAG);
        ft.addToBackStack(FullNewsFragment.TAG);
        ft.commit();
    }

    @Override
    public void onNewsClick(String url) {
        showFullNewsFragment(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                }
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        super.onBackPressed();
    }
}
