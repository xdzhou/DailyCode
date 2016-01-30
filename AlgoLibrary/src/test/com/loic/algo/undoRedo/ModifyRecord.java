package com.loic.algo.undoRedo;

public class ModifyRecord extends AbstractRecord
{
	private final Model model;
	private final int oldValue;
	private final int newValue;
	
	public ModifyRecord(Model model, int newValue)
	{
		this.model = model;
		this.oldValue = model.getValue();
		this.newValue = newValue;
		model.setValue(newValue);
	}

	@Override
	public void undo()
	{
		model.setValue(oldValue);
	}

	@Override
	public void redo()
	{
		model.setValue(newValue);
	}

}
