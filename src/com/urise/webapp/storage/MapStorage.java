package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateResume(Object searchKey, Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey, String uuid) {
        map.remove(uuid);
    }

    @Override
    public Object getSearchKey(String index) {
        return map.get(index) == null ? null : index;
    }

    @Override
    public Resume getResume(Object searchKey, String uuid) {
        return map.get(searchKey);

    }

    @Override
    public void saveResume(Object searchKey, Resume resume) {
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
