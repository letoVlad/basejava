
package com.urise.webapp.storage;

import com.urise.webapp.exception.MyException;
import com.urise.webapp.model.Resume;

public interface Storage {

    void update(Resume resume);

    void clear();

    void save(Resume r);

    Resume get(String uuid) throws Exception;

    void delete(String uuid) throws MyException;

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll();

    int size();
}