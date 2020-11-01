package com.zhao.craneslidetest.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @Author : LiangGuoChang
 * @Date : 2020/11/1
 * @描述 : 对应起重机的图标的坐标单点数据
 */
@Entity
public class CraneResultChartData {

    @Id(autoincrement = true)
    private long id;

    //时间戳
    private long timeStamp;
    //起重机类型
    private String craneType;
    //起重机编号
    private String craneNum;
    //实时速度(左Y轴)
    private String readSpeedLeft;
    //下滑距离(右Y轴)
    private String slipDistanceRight;
    @Generated(hash = 1702222058)
    public CraneResultChartData(long id, long timeStamp, String craneType,
            String craneNum, String readSpeedLeft, String slipDistanceRight) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.craneType = craneType;
        this.craneNum = craneNum;
        this.readSpeedLeft = readSpeedLeft;
        this.slipDistanceRight = slipDistanceRight;
    }
    @Generated(hash = 175385171)
    public CraneResultChartData() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getTimeStamp() {
        return this.timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getCraneType() {
        return this.craneType;
    }
    public void setCraneType(String craneType) {
        this.craneType = craneType;
    }
    public String getCraneNum() {
        return this.craneNum;
    }
    public void setCraneNum(String craneNum) {
        this.craneNum = craneNum;
    }
    public String getReadSpeedLeft() {
        return this.readSpeedLeft;
    }
    public void setReadSpeedLeft(String readSpeedLeft) {
        this.readSpeedLeft = readSpeedLeft;
    }
    public String getSlipDistanceRight() {
        return this.slipDistanceRight;
    }
    public void setSlipDistanceRight(String slipDistanceRight) {
        this.slipDistanceRight = slipDistanceRight;
    }

}
