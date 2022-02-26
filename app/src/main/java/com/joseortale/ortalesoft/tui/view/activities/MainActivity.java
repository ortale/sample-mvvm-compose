package com.joseortale.ortalesoft.tui.view.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.joseortale.ortalesoft.tui.R;
import com.joseortale.ortalesoft.tui.view.fragments.CodeChallengesFragment;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CodeChallengesFragment fragment = new CodeChallengesFragment();
        setFragment(fragment);
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 0) {
            super.onBackPressed();
        } else  {
            getFragmentManager().popBackStack();
        }
    }
}
