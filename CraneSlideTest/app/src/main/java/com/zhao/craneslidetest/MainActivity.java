package com.zhao.craneslidetest;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhao.craneslidetest.commonui.CLoadingDialogManager;
import com.zhao.craneslidetest.databinding.ActivityMainBinding;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TcpService mTcpService;
    //是否绑定了服务
    private boolean isBound = false;
    //连接状态
    private MutableLiveData<Integer> mConnectStatus;
    private ActivityMainBinding mBinding;
    private CLoadingDialogManager mLoadingDialogManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mLoadingDialogManager = new CLoadingDialogManager(this);
        initView();

        //绑定服务
        Intent intent = new Intent(this, TcpService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private void initConnectObservable() {
        if (null != mConnectStatus) {
            mConnectStatus.observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    Log.d(TAG, "onChanged: " + integer);
                    switch (integer) {
                        case AppConstants.CONNECT_INIT://未连接
                            mBinding.btnConnectTest.setBackgroundResource(R.drawable.connect_test_connect_fail_bg);
                            mLoadingDialogManager.dismissLoadingDialog();
                            break;
                        case AppConstants.CONNECTING://连接中
                            mBinding.btnConnectTest.setBackgroundResource(R.drawable.connect_test_connecting_bg);
                            break;
                        case AppConstants.CONNECT_SUCCESS://连接成功
                            mBinding.btnConnectTest.setBackgroundResource(R.drawable.connect_test_connect_success_bg);
                            mLoadingDialogManager.dismissLoadingDialog();
                            Toast.makeText(MainActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    private void initView() {
        mBinding.setListener(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TcpService.TcpServiceBinder binder = (TcpService.TcpServiceBinder) service;
            mTcpService = binder.getTcpService();
            isBound = true;
            mConnectStatus = mTcpService.getConnectStatus();
            initConnectObservable();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mConnection) {
            unbindService(mConnection);
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.btn_connect_test) {
            if (null != mConnectStatus.getValue()) {
                switch (mConnectStatus.getValue()) {
                    case AppConstants.CONNECT_INIT://未连接
                        connectServer();
                        break;

                    case AppConstants.CONNECTING://连接中
                        Toast.makeText(this, "连接中...", Toast.LENGTH_SHORT).show();
                        break;

                    case AppConstants.CONNECT_SUCCESS://连接成功
                        checkDisConnectDialog();
                        break;
                    default:
                        break;
                }
            }

        } else if (viewId == R.id.btn_start_test) {
            //if (null != mTcpService && isBound) {
            //    mTcpService.sendStartTestMsg();
            //}

            Intent intent = new Intent(this, TestParamSettingActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.btn_search_data) {
            LineChartDemo.startActivity(this);
        }
    }

    //连接服务端
    private void connectServer() {
        if (null != mTcpService && isBound) {
            mLoadingDialogManager.showLoadingDialog(getString(R.string.loading_msg));
            mTcpService.connectTcpServer("172.20.10.4", "8080");
        }
    }

    //断开连接
    private void disconnectServer() {
        if (null != mTcpService && isBound) {
            mTcpService.disconnectTcpServer();
        }
    }

    //弹出是否断开连接窗
    private void checkDisConnectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("已连接,是否要断开?");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                disconnectServer();
            }
        });
        builder.show();
    }

}
