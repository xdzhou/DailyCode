package com.sky.designpattern.chainOfResponsibility;

public class DepartmentManager extends Handle
{

	@Override
	public boolean handleRequest(String user, float fee)
	{
		if (fee < 1000)
		{
			System.out.println("Department Manager is agree for fee:" + fee);
			return true;
		} else
		{
			if (successHandle != null)
				return successHandle.handleRequest(user, fee);
			else
				return false;
		}
	}

}
