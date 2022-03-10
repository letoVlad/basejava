package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteFromArray(int index) {
        int remainingElements = size - (index + 1);
        System.arraycopy(storage, index + 1, storage, index, remainingElements);
    }

    @Override
    protected void saveToArray(int index, Resume r) {
        int newIndex = -index - 1;
        System.arraycopy(storage, newIndex, storage, newIndex + 1, size - newIndex);
        storage[newIndex] = r;
    }

    @Override
    protected Integer getSearchKey(String index) {
        Resume searchKey = new Resume(index);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}