package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
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

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Данного резюме " + resume.getUuid() + " нет в массиве");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        System.out.println(index);
        if (index < 0) {
            System.out.println("Данного резюме " + uuid + " нет в массиве");
        } else {
            deletedElement(index);
            size--;
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("Резюме " + r.getUuid() + " есть в массиве");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Массив переполнен");
        } else {
            saveElement(index, r);
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("Данного резюме " + uuid + " нет в списке");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void deletedElement(int index);

    protected abstract void saveElement(int index, Resume r);

    protected abstract int getIndex(String uuid);
}