package com.loic.algo.undoRedo;

import java.util.LinkedList;

public class GroupRecord extends AbstractRecord
{
	private static final int Max_Record_Count = 100;
	
	private final LinkedList<IRecordable> recordList = new LinkedList<>();
	private int curPosition = -1;
	private final int maxCount;
	
	public GroupRecord()
	{
		this(Max_Record_Count);
	}
	
	public GroupRecord(int maxCount)
	{
		this.maxCount = maxCount;
	}

	@Override
	public void undo()
	{
		for(int i = curPosition; i >= 0; i--)
		{
			recordList.get(i).undo();
		}
		curPosition = -1;
	}

	@Override
	public void redo()
	{
		for(int i = curPosition + 1; i < recordList.size(); i++)
		{
			recordList.get(i).redo();
		}
		curPosition = recordList.size() - 1;
	}
	
	public void addRecord(IRecordable record)
	{
		if(record != null)
		{
			for(int i = curPosition + 1; i < recordList.size(); i++)
			{
				recordList.removeLast();
			}
			recordList.addLast(record);
			if(recordList.size() > maxCount)
			{
				recordList.removeFirst();
			}
			curPosition = recordList.size() - 1;
		}
	}
	
	public void undoOneStep()
	{
		if(curPosition - 1 >= 0)
		{
			recordList.get(curPosition).undo();
			curPosition --;
		}
	}

	public void redoOneStep()
	{
		if(curPosition + 1 < recordList.size())
		{
			curPosition ++;
			recordList.get(curPosition).redo();
		}
	}
	
	public IRecordable simplifySelf()
	{
		for(int i = curPosition + 1; i < recordList.size(); i++)
		{
			recordList.remove(curPosition + 1);
		}
		curPosition = recordList.size() - 1;
		if(curPosition < 0 || recordList.isEmpty())
		{
			return null;
		}
		else if (recordList.size() == 1) 
		{
			return recordList.get(0);
		}
		return this;
	}
}
