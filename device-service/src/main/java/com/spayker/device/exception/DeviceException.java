package com.spayker.device.exception;

public class DeviceException extends RuntimeException {

    public DeviceException() {
        super();
    }

    public DeviceException(String message) {
        super(message);
    }

    public DeviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceException(Throwable cause) {
        super(cause);
    }
}
