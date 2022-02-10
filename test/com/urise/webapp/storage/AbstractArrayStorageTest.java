package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static org.junit.Assert.fail;


public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @org.junit.Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @org.junit.Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @org.junit.Test
    public void update() {
        Resume r = new Resume("uuid1");
        storage.update(r);
        Assert.assertEquals("uuid1", r.getUuid());
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void updateNotException() throws Exception {
        storage.get("TEST");
    }

    @org.junit.Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void deleteNotException() {
        storage.delete("TEST");
    }

    @org.junit.Test
    public void save() {
        storage.save(new Resume(UUID_4));
    }

    @org.junit.Test(expected = AssertionError.class)
    public void saveCrowded() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT - 1; i++) {
                storage.save(new Resume());
            }
            fail("Переполнен раньше времени ");
        } catch (AssertionError e) {
            storage.save(new Resume());
            Assert.assertEquals(storage.size(), AbstractArrayStorage.STORAGE_LIMIT);
            fail("Переполнен");
        }
    }

    @org.junit.Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(new Resume(UUID_3));
    }

    @org.junit.Test
    public void get() throws Exception {
        Resume r = new Resume(UUID_1);
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @org.junit.Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());
    }

    @org.junit.Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}