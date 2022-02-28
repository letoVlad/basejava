package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        int index = (int) goGetIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        }
        goUpdate(resume);
    }

    @Override
    public void save(Resume resume) {
        int index = (int) goGetIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        goSave(resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = (int) goGetIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return goGet(index);
    }

    @Override
    public void delete(String uuid) {
        Object index = goGetIndex(uuid);
        if ((int) index < 0) {
            throw new NotExistStorageException(uuid);
        }
        goDelete(index);
    }


    protected abstract void goUpdate(Resume resume);

    protected abstract void goDelete(Object index);

    protected abstract Object goGetIndex(String uuid);

    protected abstract Resume goGet(int resume);

    protected abstract void goSave(Resume resume);

}
