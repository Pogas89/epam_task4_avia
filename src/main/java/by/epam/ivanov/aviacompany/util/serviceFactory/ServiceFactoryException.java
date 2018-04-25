package by.epam.ivanov.aviacompany.util.serviceFactory;

public class ServiceFactoryException extends Exception {
    public ServiceFactoryException() {
    }

    public ServiceFactoryException(String message) {
        super(message);
    }

    public ServiceFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceFactoryException(Throwable cause) {
        super(cause);
    }
}
