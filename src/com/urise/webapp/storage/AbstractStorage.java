package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = (int) getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        updateResume(index, resume);
    }

    @Override
    public void save(Resume resume) {
        int index = (int) getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        saveResume(resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = (int) getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return goGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if ((int) index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
    }


    protected abstract void updateResume(int index, Resume resume);

    protected abstract void deleteResume(Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract Resume goGet(int resume);

    protected abstract void saveResume(Resume resume);

}
