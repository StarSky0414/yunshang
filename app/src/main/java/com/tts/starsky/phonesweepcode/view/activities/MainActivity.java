package com.tts.starsky.phonesweepcode.view.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.view.fragments.BottomFragment;
import com.tts.starsky.phonesweepcode.view.fragments.FirstPageFragment;

public class MainActivity extends Activity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_content, new FirstPageFragment());
        transaction.replace(R.id.fl_bottom, new BottomFragment());
        transaction.commit();
    }
}
