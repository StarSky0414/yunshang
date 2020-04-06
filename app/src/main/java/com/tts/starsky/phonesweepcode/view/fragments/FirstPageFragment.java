package com.tts.starsky.phonesweepcode.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tts.starsky.phonesweepcode.R;

import org.jetbrains.annotations.Nullable;

public class FirstPageFragment extends Fragment {

    private View fragment_firstpage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_firstpage = inflater.inflate(R.layout.fragment_firstpage, null);
        return fragment_firstpage;
    }
}
