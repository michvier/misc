package at.jku.se.transit.datadrop.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IFCMetaDataExtractor implements IArtifactMetaDataProcessor {

	/**
	 * Regex for IFC File to extract meta-data: DATA_DROP(....)
	 */
	static final String REGEX = "DATA_DROP\\(([^\\)]+)\\);";
	static final Pattern PATTERN = Pattern.compile(REGEX, Pattern.MULTILINE);

	@Override
	public Map<String, String> getMetaData(String inputfile, List<String> mandatoryKeys) throws DataProcessorException {
		Map<String, String> metaData = new TreeMap<>();
		if (inputfile == null) {
			return Collections.emptyMap();
		}
		Matcher matcher = PATTERN.matcher(inputfile);

		while (matcher.find()) {

			if (matcher.groupCount() != 1) {
				return Collections.emptyMap();
			}
			metaData.putAll(extractMetaData(matcher.group(1)));
		}
		if (mandatoryKeys == null) {
			return metaData;
		}
		checkMetaData(metaData, mandatoryKeys);
		return metaData;

	}

	private void checkMetaData(Map<String, String> metaData, List<String> mandatoryKeys) throws DataProcessorException {
		List<String> toCheck = new ArrayList<>(mandatoryKeys);
		toCheck.removeAll(metaData.keySet());

		if (!toCheck.isEmpty()) {
			String missing = toCheck.stream().collect(Collectors.joining(","));
			throw new DataProcessorException(String.format("Mandatory meta-data missing: %s", missing));
		}

	}

	private Map<String, String> extractMetaData(String metadataString) throws DataProcessorException {
		Map<String, String> metaData = new TreeMap<>();
		if (metadataString == null) {
			return Collections.emptyMap();
		}
		String[] split = metadataString.split(",");
		if (split.length % 2 != 0) {
			throw new DataProcessorException("Key/Value pairs invalid");
		}
		for (int i = 0; i < split.length - 1; i += 2) {
			metaData.put(processString(split[i]), processString(split[i + 1]));
		}

		return metaData;
	}

	private String processString(String string) {
		if (string == null || string.isEmpty()) {
			return "";
		}

		string = string.trim(); // remove whitespace
		string = string.replaceAll("^'+", ""); // remove leading single quote
		string = string.replaceAll("'+$", ""); // remove trailing single quote
		return string;

	}

	@Override
	public Map<String, String> getMetaData(String inputfile) throws DataProcessorException {
		return getMetaData(inputfile, null);
	}

}
