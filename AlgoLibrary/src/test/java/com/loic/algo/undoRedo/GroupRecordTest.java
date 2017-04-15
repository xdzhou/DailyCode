package com.loic.algo.undoRedo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupRecordTest {
    @Test
    public void test() {
        Model model = new Model();

        GroupRecord groupRecord = new GroupRecord(5);

        groupRecord.addRecord(new ModifyRecord(model, 11));
        Assert.assertEquals(11, model.getValue());
        groupRecord.addRecord(new ModifyRecord(model, 22));
        Assert.assertEquals(22, model.getValue());
        groupRecord.addRecord(new ModifyRecord(model, 33));
        Assert.assertEquals(33, model.getValue());
        groupRecord.addRecord(new ModifyRecord(model, 44));
        Assert.assertEquals(44, model.getValue());
        groupRecord.addRecord(new ModifyRecord(model, 55));
        Assert.assertEquals(55, model.getValue());
        // step undo
        groupRecord.undoOneStep();
        Assert.assertEquals(44, model.getValue());
        groupRecord.redoOneStep();
        Assert.assertEquals(55, model.getValue());

        groupRecord.undo();
        Assert.assertEquals(0, model.getValue());
        groupRecord.redo();
        Assert.assertEquals(55, model.getValue());

        groupRecord.addRecord(new ModifyRecord(model, 66));
        groupRecord.undo();
        Assert.assertEquals(0, model.getValue());
    }
}
