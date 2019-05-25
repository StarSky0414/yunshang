package com.tts.starsky.phonesweepcode;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class TraceOnClickListener implements View.OnClickListener {

    private final Context context;

    TraceOnClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvAcceptTime:
                int tag = (int) v.getTag();
                Toast.makeText(context, "点击了~~~"+tag, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
