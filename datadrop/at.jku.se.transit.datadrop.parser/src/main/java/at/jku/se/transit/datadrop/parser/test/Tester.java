package at.jku.se.transit.datadrop.parser.test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import at.jku.se.transit.datadrop.parser.DataProcessorException;
import at.jku.se.transit.datadrop.parser.IFCMetaDataExtractor;

public class Tester {

	private static List<String> lines;

	public static void main(String[] args) {
		URL testfile = Tester.class.getClassLoader().getResource("Wall_1.ifc");

		try {
			File file = new File(testfile.toURI());
			lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			String filecontents = lines.stream().collect(Collectors.joining());

			// test no mandatory
			Map<String, String> metaData = new IFCMetaDataExtractor().getMetaData(filecontents, null);

			metaData.forEach((k, v) -> System.out.println(k + " - " + v));

			// Test with mandatory
			
			List<String> mand = Arrays.asList("mandatory-1","mandatory-2", "LOD", "Fachmodell");
			//List<String> mand = Arrays.asList("LOD", "Fachmodell");

			metaData = new IFCMetaDataExtractor().getMetaData(filecontents, mand);

			metaData.forEach((k, v) -> System.out.println(k + " - " + v));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataProcessorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
