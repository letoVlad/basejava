package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected void updateResume(int index, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object index, String uuid) {
        map.remove(uuid);
    }

    @Override
    public Integer getIndex(String uuid) {
        for (String key : map.keySet()) {
            if (key.equals(uuid)) {
                return 1;
            }
        }
        return -1;
    }

    @Override
    public Resume getResume(int index, String uuid) {
        return map.get(uuid);
    }

    @Override
    public void saveResume(int index, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
