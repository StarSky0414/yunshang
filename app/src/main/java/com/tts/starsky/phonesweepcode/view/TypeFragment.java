package com.tts.starsky.phonesweepcode.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.adapter.TypeContentAdapter;
import com.tts.starsky.phonesweepcode.adapter.TypeNameAdapter;
import com.tts.starsky.phonesweepcode.bean.TypeConcreteInfo;
import com.tts.starsky.phonesweepcode.bean.TypeGeneralizeInfo;
import com.tts.starsky.phonesweepcode.controller.TypeContraller;
import com.tts.starsky.phonesweepcode.db.bean.GoodsTypeInfo;
import com.tts.starsky.phonesweepcode.utile.OnItemClickListener;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TypeFragment extends Fragment implements AdapterView.OnItemClickListener,OnItemClickListener,View.OnClickListener {

    private View fragment_type;
    private ListView lv_type_name;
    private RecyclerView rv_type_content;
    private TypeContraller typeContraller;
    private List<GoodsTypeInfo> generalizeTypeList;
    private List<GoodsTypeInfo> concreteTypeList;
    private RelativeLayout rl_search;
    private ImageView iv_search;
    private TextView tv_search;
    private ImageView iv_scan;
    private int firstType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_type = inflater.inflate(R.layout.fragment_type, null);
        initView();
        initData();
        setOnClickListener();
        return fragment_type;
    }

    private void setOnClickListener() {

    }

    public void setFirstType(int firstType) {
        this.firstType = firstType;
    }

    private void initData() {
        typeContraller = new TypeContraller(getActivity());
        generalizeTypeList = typeContraller.getGeneralizeTypeList(firstType);
        TypeNameAdapter typeNameAdapter = new TypeNameAdapter(getActivity(), generalizeTypeList, rv_type_content);
        lv_type_name.setAdapter(typeNameAdapter);
        concreteTypeList = typeContraller.getConcreteTypeList(generalizeTypeList.get(0).getType_concrete_id());
        TypeContentAdapter typeContentAdapter = new TypeContentAdapter(getActivity(), concreteTypeList);
        rv_type_content.setAdapter(typeContentAdapter);
        typeContentAdapter.setOnItemClickListener(this);
    }

    private void initView() {
        lv_type_name = fragment_type.findViewById(R.id.lv_type_name);
        lv_type_name.setOnItemClickListener(this);
        rv_type_content = fragment_type.findViewById(R.id.rv_type_content);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rv_type_content.setLayoutManager(gridLayoutManager);
        iv_scan = (ImageView) fragment_type.findViewById(R.id.iv_scan);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        concreteTypeList = typeContraller.getConcreteTypeList(generalizeTypeList.get(position).getType_concrete_id());
        TypeContentAdapter typeContentAdapter = new TypeContentAdapter(getActivity(), concreteTypeList);
        rv_type_content.setAdapter(typeContentAdapter);
        typeContentAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra("type",concreteTypeList.get(position).getType_concrete_id());
        getActivity().startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
