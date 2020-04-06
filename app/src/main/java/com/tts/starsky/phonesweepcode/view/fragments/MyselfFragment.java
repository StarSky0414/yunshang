package com.tts.starsky.phonesweepcode.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tts.starsky.phonesweepcode.R;

import org.jetbrains.annotations.Nullable;

public class MyselfFragment extends Fragment {

    private View fragment_myself;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_myself = inflater.inflate(R.layout.fragment_myself, null);
        return fragment_myself;
    }
}
