package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.*;

import static org.junit.Assert.fail;


public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r = new Resume("uuid1");
        storage.update(r);
        Assert.assertEquals("uuid1", r.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotException() throws Exception {
        storage.get("TEST");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotException() {
        storage.delete("TEST");
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        storage.get(UUID_4);
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            storage.save(new Resume());
            fail();
        }
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void get() throws Exception {
        Resume r = new Resume(UUID_1);
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}