package com.zhao.craneslidetest;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.zhao.craneslidetest.databinding.ActivityLineChartBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class LineChartActivity extends AppCompatActivity {

    private ActivityLineChartBinding mBinding;
    private MutableLiveData<Boolean> mIsShowResult = new MutableLiveData<>();
    //图标相关
    private LineChart mLineChart; // 折线表，存线集合
    private LineData mLineData; // 线集合，所有折现以数组的形式存到此集合中
    private XAxis mXAxis; //X轴
    private YAxis mLeftYAxis; //左侧Y轴
    private YAxis mRightYAxis; //右侧Y轴
    private Legend mLegend; //图例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_line_chart);

        initView();
    }

    private void initView() {
        mIsShowResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mBinding.setIsShowResult(aBoolean);
            }
        });
    }


}
