package org.efit.mobile.activity.layanan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.efit.mobile.R;
import org.efit.mobile.core.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityInLayanan extends BaseActivity {
    @BindView(R.id.spin_layananijin)
    Spinner spinLayananijin;
    List<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lay_ijin);
        ButterKnife.bind(this);
        init();
        spinLayananijin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("id layanan", "onItemClick: "+list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void init() {
        list.add("Cuti Tahunan");
        list.add("Cuti Besar");
        list.add("Cuti Sakit");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinLayananijin.setAdapter(dataAdapter);
    }
}
