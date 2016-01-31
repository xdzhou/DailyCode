package com.loic.algo.undoRedo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimplifyGroupRecordTest
{
	@Test
	public void test()
	{
		GroupRecord groupRecord = new GroupRecord();
		Model model = new Model();
		ModifyRecord record = new ModifyRecord(model, 11);
		groupRecord.addRecord(record);
		Assert.assertEquals(model.getValue(), 11);
		Assert.assertEquals(groupRecord.simplifySelf(), record);
		
		groupRecord.addRecord(record.reverse());
		Assert.assertEquals(model.getValue(), 0);
		Assert.assertEquals(groupRecord.simplifySelf(), null);
		
		groupRecord.addRecord(record);
		groupRecord.addRecord(new ModifyRecord(model, 22));
		Assert.assertEquals(model.getValue(), 22);
		groupRecord.undo();
		Assert.assertEquals(model.getValue(), 0);
		groupRecord.redo();
		Assert.assertEquals(model.getValue(), 22);
		
		IRecordable simplyRecord = groupRecord.simplifySelf();
		Assert.assertTrue(simplyRecord instanceof ModifyRecord);
		simplyRecord.undo();
		Assert.assertEquals(model.getValue(), 0);
		simplyRecord.redo();
		Assert.assertEquals(model.getValue(), 22);
	}
}
