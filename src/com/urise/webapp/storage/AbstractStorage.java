package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {


    @Override
    public void update(Resume resume) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public Resume get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    protected abstract void goUpdate(Resume resume);

    protected abstract void goDelete(String uuid);

    protected abstract int goGetIndex(String uuid);

    protected abstract void goSave(Resume resume);

    protected abstract void goClear();

    protected abstract int goSize();

    protected abstract Object[] goGetAll();

}
