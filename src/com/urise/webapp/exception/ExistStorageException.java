package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("Данное резюме " + uuid + " уже существует", uuid);
    }
}
