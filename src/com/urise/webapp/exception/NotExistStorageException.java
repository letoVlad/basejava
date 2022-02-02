package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Данного резюме " + uuid + " нет в списке", uuid);
    }



}

