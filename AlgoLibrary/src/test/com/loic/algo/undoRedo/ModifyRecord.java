package com.loic.algo.undoRedo;

public class ModifyRecord extends AbstractRecord {
	private final Model model;
	private int oldValue;
	private int newValue;

	public ModifyRecord(Model model, int newValue) {
		this.model = model;
		this.oldValue = model.getValue();
		this.newValue = newValue;
	}

	@Override
	public void undo() {
		model.setValue(oldValue);
	}

	@Override
	public void redo() {
		model.setValue(newValue);
	}

	@Override
	public IRecordable merge(IRecordable otherRecord) {
		if (otherRecord instanceof ModifyRecord) {
			this.newValue = ((ModifyRecord) otherRecord).newValue;
			return this;
		}
		return super.merge(otherRecord);
	}

}
