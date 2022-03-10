package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteFromArray(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void saveToArray(int index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected Integer getSearchKey(String index) {
        for (int i = 0; i < size; i++) {
            if (index.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
