package io.mobitech.content_ui_demo.ui;

import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import io.mobitech.content_ui.fragments.MixedContentFragment;
import io.mobitech.content_ui.fragments.MixedVideoContentFragment;
import io.mobitech.content_ui.fragments.OrganicContentFragment;
import io.mobitech.content_ui.fragments.PromotedContentFragment;
import io.mobitech.content_ui.fragments.VideoContentFragment;
import io.mobitech.content_ui.interfaces.OnNewsClickListener;
import io.mobitech.content_ui_demo.R;
import io.mobitech.content_ui_demo.util.CustomTabHelper;

public class MainActivity extends AppCompatActivity implements OnNewsClickListener {

    private CustomTabsIntent.Builder customTabBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomTabHelper.bindCustomTab(this);
        customTabBuilder = CustomTabHelper.createTabBuilder(this);


        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] contentTypes = getResources().getStringArray(R.array.contentTypes);
                String selectedType = contentTypes[i];

                switch (selectedType) {
                    case "Organic":
                        showOrganicContentFragment();
                        break;
                    case "Promoted":
                        showPromotedContentFragment();
                        break;
                    case "Video":
                        showVideoContentFragment();
                        break;
                    case "Organic + Promoted":
                        showMixedContentFragment();
                        break;
                    case "Organic + Promoted + Video":
                        showMixedVideoContentFragment();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });
    }

    private void showOrganicContentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_ui_container, OrganicContentFragment.newInstance(), OrganicContentFragment.TAG);
        ft.commit();
    }

    private void showVideoContentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_ui_container, VideoContentFragment.newInstance(), VideoContentFragment.TAG);
        ft.commit();
    }

    private void showPromotedContentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_ui_container, PromotedContentFragment.newInstance(), PromotedContentFragment.TAG);
        ft.commit();
    }

    private void showMixedContentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_ui_container, MixedContentFragment.newInstance(), MixedContentFragment.TAG);
        ft.commit();
    }

    private void showMixedVideoContentFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_ui_container, MixedVideoContentFragment.newInstance(), MixedVideoContentFragment.TAG);
        ft.commit();
    }

    @Override
    public void onNewsClick(String url) {
        CustomTabHelper.openCustomTab(this, customTabBuilder, getResources(), url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
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
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        super.onBackPressed();
    }
}
