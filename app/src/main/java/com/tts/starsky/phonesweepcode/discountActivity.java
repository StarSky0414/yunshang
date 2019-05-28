package com.tts.starsky.phonesweepcode;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class discountActivity extends Activity implements RadioGroup.OnCheckedChangeListener  {

    private RadioGroup radioGroup_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);
        radioGroup_gender = (RadioGroup) findViewById(R.id.radioGroup_gender);
        radioGroup_gender.setOnCheckedChangeListener(this);
    }

    /**
     * 当单选按钮的状态发生变化时自动调用的方法
     * @param group 单选按钮所在的按钮组的对象
     * @param checkedId 用户选中的单选按钮的id值
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //得到用户选中的 RadioButton 对象
        RadioButton radioButton_checked= (RadioButton) group.findViewById(checkedId);
        String gender=radioButton_checked.getText().toString();
        Toast.makeText(this,gender,Toast.LENGTH_LONG).show();
        switch (checkedId){
            case R.id.rb_ercentage_discount:
                //当用户点击男性按钮时执行的代码
                System.out.println("===男性===");
                break;
        }
        System.out.println("===onCheckedChanged(RadioGroup group="+group+", int checkedId="+checkedId+")==");
    }
}
