package com.sky.designpattern.chainOfResponsibility;

public class ProjectManager extends Handle {

	@Override
	public boolean handleRequest(String user, float fee) {
		if (fee < 500) {
			System.out.println("Project Manager is agree for fee:" + fee);
			return true;
		} else {
			return getSuccessHandle() != null && getSuccessHandle().handleRequest(user, fee);
		}
	}

}
