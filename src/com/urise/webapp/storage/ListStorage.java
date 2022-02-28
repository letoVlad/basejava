package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> list = new ArrayList<>();

    @Override
    protected void goUpdate(Resume resume) {
        list.set(goGetIndex(resume.getUuid()), resume);
    }

    @Override
    public void goDelete(Object index) {
        list.remove((int) index);
    }

    @Override
    protected Integer goGetIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Resume goGet(int resume) {
        return list.get(resume);
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public void goSave(Resume r) {
        list.add(r);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }
}
