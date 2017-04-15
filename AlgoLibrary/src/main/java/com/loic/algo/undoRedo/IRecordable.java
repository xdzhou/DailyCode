package com.loic.algo.undoRedo;

public interface IRecordable {
    void undo();

    void redo();

    IRecordable reverse();

    IRecordable merge(IRecordable other) throws UnsupportedOperationException;
}
