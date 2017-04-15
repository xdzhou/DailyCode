package com.loic.algo.undoRedo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UndoRedoTest {
    @Test
    public void test() {
        Model model = new Model();
        ModifyRecord record = new ModifyRecord(model, 100);
        record.redo();

        // undo redo test
        Assert.assertEquals(100, model.getValue());
        record.undo();
        Assert.assertEquals(0, model.getValue());
        record.redo();
        Assert.assertEquals(100, model.getValue());

        // reverse test
        IRecordable inverseRecord = record.reverse();
        Assert.assertEquals(record, record.reverse().reverse());
        Assert.assertEquals(inverseRecord, inverseRecord.reverse().reverse());
    }
}
