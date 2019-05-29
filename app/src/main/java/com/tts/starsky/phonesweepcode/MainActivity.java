package com.tts.starsky.phonesweepcode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.tts.starsky.phonesweepcode.R;

/**
 * @author ZMC
 * 三级联动主要是灵活的应用三维数组 
 */
public class MainActivity extends Activity {
    private String province[] = new String[]{"江西","湖南"};
    private Spinner spinner1,spinner2,spinner3;
    private int provinceindex;
    private String city [][] = {{"南昌","赣州"},{"长沙","湘潭"}};
    private String counstryside [][][] = {{{"青山湖区","南昌县"},{"章贡区","赣县"}},{{"长沙县","沙县"},{"湘潭县","象限"}}};
    ArrayAdapter<String> adapter1,adapter2,adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        spinner1 = (Spinner) findViewById(R.id.spn);
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,province);
        spinner1.setAdapter(adapter1);

        spinner2  = (Spinner)findViewById(R.id.city);
        adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,city[0]);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner)findViewById(R.id.counstryside);
        adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line,counstryside[0][0]);
        spinner3.setAdapter(adapter3);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub  
                provinceindex = position;
                adapter2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,city[position]);
                spinner2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub  

            }
        });

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub  

                adapter3 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,counstryside[provinceindex][position]);
                //adapter3.notifyDataSetChanged();  
                spinner3.setAdapter(adapter3);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub  
                //当时据为空的时候触发的  
            }
        });


    }


}  