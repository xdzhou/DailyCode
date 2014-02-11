package com.sky.designpattern.chainOfResponsibility;

public abstract class Handle {
	protected Handle successHandle = null;
	
	public abstract boolean handleRequest(String user, float fee);

	public Handle getSuccessHandle() {
		return successHandle;
	}

	public void setSuccessHandle(Handle successHandle) {
		this.successHandle = successHandle;
	}
}
