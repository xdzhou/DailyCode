package com.sky.designpattern.chainOfResponsibility;

public class GeneralManage extends Handle
{

	@Override
	public boolean handleRequest(String user, float fee)
	{
		if (fee < 1500)
		{
			System.out.println("General Manager is agree for fee:" + fee);
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