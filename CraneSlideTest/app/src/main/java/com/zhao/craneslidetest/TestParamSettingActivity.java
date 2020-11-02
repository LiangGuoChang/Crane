package com.zhao.craneslidetest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.zhao.craneslidetest.beans.CraneType;

import androidx.appcompat.app.AppCompatActivity;

public class TestParamSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner craneSpinner;
    private ArrayAdapter mArrayAdapter;
    private CraneType mCraneType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_param_setting);
        initData();
        initView();
    }

    private void initData() {
        mArrayAdapter = ArrayAdapter.createFromResource(this, R.array.craneType, R.layout.spinner_item_layout);
        mArrayAdapter.setDropDownViewResource(R.layout.spinner_drop_item_layout);
        mCraneType = new CraneType();
    }

    private void initView() {
        craneSpinner = findViewById(R.id.crane_spinner);
        craneSpinner.setAdapter(mArrayAdapter);
        findViewById(R.id.btn_last_step).setOnClickListener(this);
        findViewById(R.id.btn_next_step).setOnClickListener(this);
        craneSpinner.setSelection(0);
        //类型下拉选择
        craneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemSelected: " + position);
                mCraneType.setCraneType((String) craneSpinner.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //输入框监听

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_last_step:
                //上一步
                this.finish();
                break;

            case R.id.btn_next_step:
                //下一步
                Intent intent = new Intent(this, LineChartActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
