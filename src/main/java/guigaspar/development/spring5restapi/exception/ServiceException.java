package guigaspar.development.spring5restapi.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String service;

	public ServiceException() {
	}
	 
    public ServiceException(final String service) {
        super();
        this.service = service;
    }

    public ServiceException(final String message, final String service) {
        super(message);
        this.service = service;
    }

    public ServiceException(final String message, final Throwable cause,
                            final String service) {
        super(message, cause);
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setService(final String service) {
        this.service = service;
    }
}
