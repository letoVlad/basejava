package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object searchKey = getNotExistingSearchKey(uuid);
        saveResume(searchKey, resume);
    }

    public void delete(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        deleteResume(searchKey);
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object searchKey = getExistingSearchKey(uuid);
        updateResume(searchKey, resume);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistingSearchKey(uuid);
        return getResume(searchKey, uuid);
    }

    private Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getExistingSearchKey (String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void updateResume(Object searchKey, Resume resume);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume getResume(Object searchKey, String uuid);

    protected abstract void saveResume(Object searchKey, Resume resume);

}
