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
        int index = check(resume.getUuid());
        if (index == -1) {
            System.out.println("Данного резюме " + resume.getUuid() + " нет в списке");
        } else {
            storage[index] = resume;
        }
    }

    public void delete(String uuid) {
        int index = check(uuid);
        if (index == -1) {
            System.out.println("Данного резюме " + uuid + " нет в списке");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    public void save(Resume r) {
        if (check(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " есть в списке");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Переполнен");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = check(uuid);
        if (index == -1) {
            System.out.println("Данного резюме " + uuid + " нет в списке");
            return null;
        } else {
            return storage[index];
        }
    }

    protected abstract int check(String uuid);
}

