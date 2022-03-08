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
    public void deleteResume(Object index, String uuid) {
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
    public Resume getResume(int index, String uuid) {
        return list.get(index);
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[list.size()]);
    }

    @Override
    public void saveResume(int index, Resume resume) {
        list.add(resume);
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
