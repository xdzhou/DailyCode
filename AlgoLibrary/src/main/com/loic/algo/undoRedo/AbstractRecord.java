package com.loic.algo.undoRedo;

public abstract class AbstractRecord implements IRecordable
{
	private IRecordable reverseRecord;
	
	@Override
	public IRecordable reverse()
	{
		if(reverseRecord == null)
		{
			reverseRecord = new AbstractRecord()
			{
				@Override
				public void undo()
				{
					AbstractRecord.this.redo();
				}
				
				@Override
				public void redo()
				{
					AbstractRecord.this.undo();
				}
				
				@Override
				public IRecordable reverse()
				{
					return AbstractRecord.this;
				}
			};
		}
		return reverseRecord;
	}
	
	@Override
	public IRecordable merge(IRecordable otherRecord)
	{
		if(reverseRecord != null && reverseRecord == otherRecord)
		{
			return null;
		}
		else
		{
			throw new UnsupportedOperationException("Can't merge this recordable");
		}
	}
}
