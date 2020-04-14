package throwables;

import resource.HealthService;

/**
 * 
 * This class represents the exception thrown whenever a health-service tries to serve a patient using the wrong resource.
 *
 */

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HealthService service;

	/**
	 * @param service : The health-service where the exception has occured.
	 */
	
	//Constructor 
	
	public ServiceException(HealthService service) {
		super();
		this.service = service;
	}
	
	
	// Getter and the Setter of the parameter service (of the type HealthService)
	
	public HealthService getService() {
		return service;
	}

	public void setService(HealthService service) {
		this.service = service;
	}
}
