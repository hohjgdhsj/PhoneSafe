package com.example.safephone.entity;

public class BlackNumInfo {
private String num;
private int mode;
private int id;
//构造方法

public BlackNumInfo() {
	super();
}
public BlackNumInfo(String num, int mode) {
	super();
	this.num = num;
	this.mode = mode;
}
public String getNum() {
	return num;
}
public void setNum(String num) {
	this.num = num;
}
public int getMode() {
	return mode;
}
public void setMode(int mode) {
	this.mode = mode;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
@Override
public String toString() {
	return "BlackNumInfo [num=" + num + ", mode=" + mode + ", id=" + id + "]";
}


}
