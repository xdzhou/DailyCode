package com.sky.eventdriven;

public class Client {
	private int id;
	private int arriveTime;
	private int startServiceTime;
	private int serviceDuring;
	private int numWin;
	
	public Client(int id, int arriveTime,int serviceDuring) {
		this.id = id;
		this.arriveTime = arriveTime;
		this.serviceDuring = serviceDuring;
	}

	public int getStartServiceTime() {
		return startServiceTime;
	}

	public void setStartServiceTime(int startServiceTime) {
		this.startServiceTime = startServiceTime;
	}

	public int getId() {
		return id;
	}

	public int getArriveTime() {
		return arriveTime;
	}

	public int getServiceDuring() {
		return serviceDuring;
	}

	public int getNumWin() {
		return numWin;
	}

	public void setNumWin(int numWin) {
		this.numWin = numWin;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", arriveTime=" + arriveTime
				+ ", startServiceTime=" + startServiceTime + ", serviceDuring="
				+ serviceDuring + "]";
	}
	
	
}
