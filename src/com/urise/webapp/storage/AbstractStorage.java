package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(index, resume);
    }

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index, uuid);
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if ((int) index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index, uuid);
    }


    protected abstract void updateResume(int index, Resume resume);

    protected abstract void deleteResume(Object index, String uuid);

    protected abstract Integer getIndex(String uuid);

    protected abstract Resume getResume(int index, String uuid);

    protected abstract void saveResume(int index, Resume resume);

}
