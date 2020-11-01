package com.zhao.craneslidetest.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author : LiangGuoChang
 * @Date : 2020/11/1
 * @描述 : 起重机类型表数据结构
 */
@Entity
public class CraneType {

    @Id(autoincrement = true)
    private long id;

    //时间戳
    private long timeStamp;
    //起重机类型
    private String craneType;
    //起重机编号
    private String craneNum;
    //额定起重量 kg
    private int rateQty;
    //最小上升速度 cm/s
    private float miniSpeed;
    //检验人员
    private String inspectors;
    //运行速度
    private String runSpeed;
    //制停距离
    private String stopDistance;
    //制停减速度
    private String stopDecrease;
    //检验结果
    private String testResult;


    @Generated(hash = 1115003367)
    public CraneType(long id, long timeStamp, String craneType, String craneNum,
                     int rateQty, float miniSpeed, String inspectors, String runSpeed,
                     String stopDistance, String stopDecrease, String testResult) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.craneType = craneType;
        this.craneNum = craneNum;
        this.rateQty = rateQty;
        this.miniSpeed = miniSpeed;
        this.inspectors = inspectors;
        this.runSpeed = runSpeed;
        this.stopDistance = stopDistance;
        this.stopDecrease = stopDecrease;
        this.testResult = testResult;
    }

    @Generated(hash = 1122336503)
    public CraneType() {
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRateQty() {
        return this.rateQty;
    }

    public void setRateQty(int rateQty) {
        this.rateQty = rateQty;
    }

    public float getMiniSpeed() {
        return this.miniSpeed;
    }

    public void setMiniSpeed(float miniSpeed) {
        this.miniSpeed = miniSpeed;
    }

    public String getInspectors() {
        return this.inspectors;
    }

    public void setInspectors(String inspectors) {
        this.inspectors = inspectors;
    }

    public String getRunSpeed() {
        return this.runSpeed;
    }

    public void setRunSpeed(String runSpeed) {
        this.runSpeed = runSpeed;
    }

    public String getStopDistance() {
        return this.stopDistance;
    }

    public void setStopDistance(String stopDistance) {
        this.stopDistance = stopDistance;
    }

    public String getStopDecrease() {
        return this.stopDecrease;
    }

    public void setStopDecrease(String stopDecrease) {
        this.stopDecrease = stopDecrease;
    }

    public String getTestResult() {
        return this.testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


}
