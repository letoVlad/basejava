package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.*;

import static org.junit.Assert.fail;
import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;
    private static final String UUID_1 = "UUID_1";
    private static final Resume resume_1 = new Resume("UUID_1");

    private static final String UUID_2 = "UUID_2";
    private static final Resume resume_2 = new Resume("UUID_2");

    private static final String UUID_3 = "UUID_3";
    private static final Resume resume_3 = new Resume("UUID_3");

    private static final String UUID_4 = "UUID_4";
    private static final Resume resume_4 = new Resume("UUID_4");

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        Resume r = new Resume(UUID_1);
        storage.update(r);
        assertSame(r, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("TEST");
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotException() {
        storage.delete("TEST");
    }

    @Test
    public void save() {
        storage.save(resume_4);
        assertEquals(4, storage.size());
        assertEquals(resume_4, storage.get(UUID_4));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("переполнение произошло раньше времени");
        }
        storage.save(new Resume());
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void get() {
        Resume r = new Resume(UUID_1);
        assertEquals(r, storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] expected = {resume_1, resume_2, resume_3};
        assertArrayEquals(expected, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }
}