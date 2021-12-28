import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int i = 0;

    Resume check(int index) {
        return storage[index];
    }

    void clear() {
    }

    void save(Resume r) {
        storage[i] = r;
        i++;
    }

    Resume get(String uuid) {
        return storage[Integer.parseInt(uuid)];
    }

    void delete(String uuid) {
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, i);

    }

    int size() {
        return 0;
    }
}


