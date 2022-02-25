package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> list = new ArrayList<>();

    @Override
    protected int goSize() {
        return list.size();
    }

    @Override
    protected void goUpdate(Resume resume) {
        list.set(goGetIndex(resume.getUuid()), resume);
    }

    @Override
    protected void goDelete(String uuid) {
        list.remove(uuid);
    }

    @Override
    protected void goSave(Resume resume) {
        list.add(resume);
    }

    @Override
    protected int goGetIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void goClear() {
        list.clear();
    }

    @Override
    protected Object[] goGetAll() {
        return list.toArray();
    }
}
