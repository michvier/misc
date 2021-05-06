package at.jku.se.transit.datadrop.parser;

import java.util.List;
import java.util.Map;

/**
 * 
 * Interface for Artifact Processors for extracting meta-data from a specific
 * Artifact(Type)
 * 
 * @author Michael Vierhauser
 *
 */
public interface IArtifactMetaDataProcessor {

	/**
	 * 
	 * @param inputfile : The input file as a string
	 * @return A map of key-value pairs meta-data
	 * @throws DataProcessorException when parsing fails
	 */
	public Map<String, String> getMetaData(String inputfile) throws DataProcessorException;

	/**
	 * 
	 * @param inputfile :     The input file as a string
	 * @param mandatoryKeys : The list of mandatory meta-data keys.<br>
	 *                      If {@code null} is passed then no mandatory checks are
	 *                      performed
	 * @return A map of key-value pairs meta-data
	 * @throws DataProcessorException when parsing fails or a key is missing
	 */
	public Map<String, String> getMetaData(String inputfile, List<String> mandatoryKeys) throws DataProcessorException;
	
	/**
	 * 
	 * @return The ID of the processor
	 */
	public String getID();

}
