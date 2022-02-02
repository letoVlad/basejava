package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deletedElement(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void saveElement(int index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
