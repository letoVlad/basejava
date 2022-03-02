package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected void updateResume(int index, Resume resume) {
        list.set(index, resume);
    }

    @Override
    public void deleteResume(Object index) {
        list.remove((int) index);
    }

    @Override
    protected Integer getIndex(String uuid) {
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
    public void saveResume(Resume r) {
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
