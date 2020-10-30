package com.zhao.craneslidetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.vilyever.socketclient.SocketClient;
import com.vilyever.socketclient.helper.SocketClientDelegate;
import com.vilyever.socketclient.helper.SocketClientReceivingDelegate;
import com.vilyever.socketclient.helper.SocketPacketHelper;
import com.vilyever.socketclient.helper.SocketResponsePacket;
import com.vilyever.socketclient.util.CharsetUtil;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class TcpService extends Service {

    private static final String TAG = "TcpService";
    private MutableLiveData<Integer> mConnectStatus = new MutableLiveData<>();
    private SocketClient mSocketClient;

    @Override
    public IBinder onBind(Intent intent) {
        mConnectStatus.setValue(AppConstants.CONNECT_INIT);
        return new TcpServiceBinder();
    }

    class TcpServiceBinder extends Binder {
        TcpService getTcpService() {
            return TcpService.this;
        }
    }

    //连接服务端
    public void connectTcpServer(final String ip, final String port) {
        mSocketClient = new SocketClient();
        mSocketClient.getAddress().setRemoteIP(ip);
        mSocketClient.getAddress().setRemotePort(port);
        mSocketClient.getAddress().setConnectionTimeout(8 * 1000);//8s
        mSocketClient.setCharsetName("UTF-8");//编码格式
        //设置自动读取包头、包尾
        setReadHeaderAndTrailer();
        //设置自动发送包头、包尾
        setSendHeaderAndTrailer();
        //设置相关监听
        mSocketClient.registerSocketClientDelegate(mSocketClientDelegate);
        mSocketClient.registerSocketClientReceiveDelegate(mReceivingDelegate);
        //连接
        mSocketClient.connect();
        mConnectStatus.setValue(AppConstants.CONNECTING);
    }

    //设置自动读取的固定包头、包尾
    private void setReadHeaderAndTrailer() {
        mSocketClient.getSocketPacketHelper().setReadStrategy(SocketPacketHelper.ReadStrategy.AutoReadToTrailer);
        mSocketClient.getSocketPacketHelper().setReceiveHeaderData(CharsetUtil.stringToData("EE", CharsetUtil.UTF_8));
        mSocketClient.getSocketPacketHelper().setReceiveTrailerData(CharsetUtil.stringToData("FFCFFFF", CharsetUtil.UTF_8));
        //mSocketClient.getSocketPacketHelper().setReceiveTimeout(10 * 1000);
        //mSocketClient.getSocketPacketHelper().setReceiveTimeoutEnabled(true);
    }

    //设置自动发送的固定包头、包尾
    private void setSendHeaderAndTrailer() {
        //mSocketClient.getSocketPacketHelper().setSendHeaderData(new byte[]{(byte) 0xEE});
        //mSocketClient.getSocketPacketHelper().setSendTrailerData(new byte[]{(byte) 0xFF, (byte) 0xFC, (byte) 0xFF, (byte) 0xFF});
        mSocketClient.getSocketPacketHelper().setSendHeaderData(CharsetUtil.stringToData("EE", CharsetUtil.UTF_8));
        mSocketClient.getSocketPacketHelper().setSendTrailerData(CharsetUtil.stringToData("FFCFFFF", CharsetUtil.UTF_8));
        mSocketClient.getSocketPacketHelper().setSendTimeout(10 * 1000);
        mSocketClient.getSocketPacketHelper().setSendTimeoutEnabled(true);
    }


    //连接回调监听
    private SocketClientDelegate mSocketClientDelegate = new SocketClientDelegate() {
        @Override
        public void onConnected(SocketClient client) {
            Log.d(TAG, "onConnected: ");

            mConnectStatus.setValue(AppConstants.CONNECT_SUCCESS);
        }

        @Override
        public void onDisconnected(SocketClient client) {
            Log.d(TAG, "onDisconnected: ");
            mConnectStatus.setValue(AppConstants.CONNECT_INIT);
        }

        @Override
        public void onResponse(SocketClient client, @NonNull SocketResponsePacket responsePacket) {
            Log.d(TAG, "onResponse: " + responsePacket.getMessage());
        }
    };


    //接收监听
    private SocketClientReceivingDelegate mReceivingDelegate = new SocketClientReceivingDelegate() {
        @Override
        public void onReceivePacketBegin(SocketClient client, SocketResponsePacket packet) {
            Log.d(TAG, "onReceivePacketBegin: ");
        }

        @Override
        public void onReceivePacketEnd(SocketClient client, SocketResponsePacket packet) {
            Log.d(TAG, "onReceivePacketEnd: " + CharsetUtil.dataToString(packet.getHeaderData(), CharsetUtil.UTF_8));
            Log.d(TAG, "onReceivePacketEnd: " + CharsetUtil.dataToString(packet.getData(), CharsetUtil.UTF_8));
            Log.d(TAG, "onReceivePacketEnd: " + CharsetUtil.dataToString(packet.getTrailerData(), CharsetUtil.UTF_8));

        }

        @Override
        public void onReceivePacketCancel(SocketClient client, SocketResponsePacket packet) {
            Log.d(TAG, "onReceivePacketCancel: ");
        }

        @Override
        public void onReceivingPacketInProgress(SocketClient client, SocketResponsePacket packet, float progress, int receivedLength) {

            Log.d(TAG, "onReceivingPacketInProgress: ");
        }
    };

    public MutableLiveData<Integer> getConnectStatus() {
        return mConnectStatus;
    }

    private void sendMsg(String msg) {
        if (null != mSocketClient && mSocketClient.isConnected()) {
            mSocketClient.sendString(msg);
        } else {
            Toast.makeText(this, "请连接测试", Toast.LENGTH_SHORT).show();
        }
    }

    //发送开始测试指令
    public void sendStartTestMsg() {
        sendMsg("000000");
    }

    //停止测试指令
    public void sendStopTestMsg() {
        sendMsg("010000");
    }

    //状态不改变指令
    public void sendStatusNoChangeMsg() {
        sendMsg("020000");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mSocketClient) {
            mSocketClient.removeSocketClientDelegate(mSocketClientDelegate);
        }
    }
}
