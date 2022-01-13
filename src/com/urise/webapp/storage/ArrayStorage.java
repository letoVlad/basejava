package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class ArrayStorage {
    private Resume[] storage = new Resume[10];
    private int size;

    public int check(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }

    public void update(Resume resume) {
        if (check(resume.getUuid()) != -1) {
            System.out.println("Резюме " + resume.getUuid() + " есть в списке");
            for (int i = 0; i < size; i++) {
                if (resume.getUuid() == storage[i].getUuid()) {
                    storage[i] = resume;
                }
            }
        }
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void save(Resume r) {
        if (check(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " есть в списке");
        } else if (size == storage.length) {
            System.out.println("Переполнен");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = check(uuid);
        if (index != -1) {
            System.out.println("Резюме " + uuid + " есть в списке");
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int index = check(uuid);
        if (index != -1) {
            System.out.println("Резюме " + uuid + " есть в списке");
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}

