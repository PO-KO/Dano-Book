package com.dano.dano_book.utilities;

public class CustomResponseBody<T> {
    private T message;
    private int status;

    public CustomResponseBody(T message, int status) {
        this.message = message;
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
