package com.loic.algo.undoRedo;

public interface IRecordable
{
	void undo();
	void redo();
	
	IRecordable reverse();
	
	public enum RecordRelation
	{
		Record_offset, 		//
		Record_Replace, 	//replace other record
		Record_No_Effet; 	//has no effet
	}
}
