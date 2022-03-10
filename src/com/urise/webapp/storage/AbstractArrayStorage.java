package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }


    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    public void deleteResume(Object searchKey, String uuid) {
        deleteFromArray((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void saveResume(Object searchKey, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен", resume.getUuid());
        }
        saveToArray((Integer) searchKey, resume);
        size++;
    }

    @Override
    public Resume getResume(Object searchKey, String uuid) {
        return storage[(Integer) searchKey];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void deleteFromArray(int index);

    protected abstract void saveToArray(int index, Resume r);

    protected abstract Integer getSearchKey(String index);
}

