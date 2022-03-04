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

    @Override
    protected void updateResume(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    public void deleteResume(Object index) {
        deleteFromArray((int) index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected void saveResume(int index, Resume resume) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Массив переполнен", resume.getUuid());
        }
        saveToArray(index, resume);
        size++;
    }

    @Override
    public Resume goGet(int index) {
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void deleteFromArray(int index);

    protected abstract void saveToArray(int index, Resume r);

    protected abstract Integer getIndex(String uuid);
}

