package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deletedElement(int index) {
        int remainingElements = size - (index + 1);
        System.arraycopy(storage, index + 1, storage, index, remainingElements);
        storage[size - 1] = null;
    }

    @Override
    protected void saveElement(int index, Resume r) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
