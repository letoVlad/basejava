package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage extends AbstractArrayStorage {

    protected int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }


}

