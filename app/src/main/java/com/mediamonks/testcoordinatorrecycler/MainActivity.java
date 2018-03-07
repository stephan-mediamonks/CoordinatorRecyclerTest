package com.mediamonks.testcoordinatorrecycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.mediamonks.testcoordinatorrecycler.list.ListFragment;

import java.util.ArrayList;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements Host {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> items = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            items.add(getString(R.string.label, Integer.toString(i)));
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, ListFragment.newInstance(items), ListFragment.TAG).commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Timber.d("onAttachFragment: " + fragment);

        if (fragment instanceof GuestFragment) {
            ((GuestFragment) fragment).setHost(this);
        }
    }

    @Override
    public void navigateToDetail(String label) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(label), DetailFragment.TAG);
        transaction.addToBackStack(DetailFragment.TAG);
        transaction.commit();
    }
}
