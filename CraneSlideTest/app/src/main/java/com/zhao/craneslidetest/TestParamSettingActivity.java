package com.zhao.craneslidetest;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class TestParamSettingActivity extends AppCompatActivity {

    private Spinner craneSpinner;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_param_setting);
        initData();
        initView();
    }

    private void initData() {
        mArrayAdapter = ArrayAdapter.createFromResource(this, R.array.craneType, android.R.layout.simple_spinner_item);
    }

    private void initView() {
        craneSpinner = findViewById(R.id.crane_spinner);
        craneSpinner.setAdapter(mArrayAdapter);
    }
}
