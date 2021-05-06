package at.jku.se.transit.datadrop.parser;

/**
 * 
 * DataProcessorException is thrown when parsing fails or mandatory meta-data
 * keys are missing
 * 
 * @author Michael Vierhauser
 *
 */
public class DataProcessorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5195901773203931091L;

	public DataProcessorException(String message) {
		super(message);
	}

}
