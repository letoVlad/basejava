package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage {
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
    protected void deleteResume(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    public Object getSearchKey(String uuid) {
        return map.containsKey(uuid) ? uuid : null;
    }

    @Override
    public Resume getResume(Object searchKey) {
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
    public List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}
