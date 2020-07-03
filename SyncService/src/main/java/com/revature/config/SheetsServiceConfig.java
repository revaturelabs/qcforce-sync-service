package com.revature.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

/**
 * Main configuration class for connecting to google sheets
 * 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Configuration
public class SheetsServiceConfig {

	/**
	 * This {@link String} represents the google service account for the google
	 * sheet being accessed.
	 */
	private final String CLIENT_ID = "projectsync@projectsync-281422.iam.gserviceaccount.com";
	/**
	 * This {@link List}<{@link String}> is used to represent the scope of a google
	 * service account.
	 */
	private final List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");
	/**
	 * This {@link String} represents the name of the configuration file used to
	 * access google sheets.
	 */
	public static final String PREFIX = "projectsync-281422-0ea6cec11520";
	/**
	 * This {@link String} represents the format of the configuration file used to
	 * access google sheets.
	 */
	public static final String SUFFIX = ".p12";
	/**
	 * This {@link String} represents id of the spreadsheet being accessed.
	 */
	public static final String SPREAD_SHEET_ID = "161u5xTW-Llo90hbpfpffPs6tvgS3-JIzJfsdHk8F3ms";

	/**
	 * Reads the google security file and returns it as a {@link File}}.
	 * 
	 * @param inputStream takes in an {@link InputStream} and turns it into a file.
	 * @return a file representing the input stream.
	 * @throws IOException when input stream not found.
	 */
	public static File inputStreamToFile(InputStream inputStream) throws IOException {
		final File tempFile = File.createTempFile(PREFIX, SUFFIX);
		tempFile.deleteOnExit();
		FileUtils.copyInputStreamToFile(inputStream, tempFile);
		return tempFile;
	}

	/**
	 * Configures and sets the necessary parameters to be able to access a google
	 * sheet and returns a {@link GoogleCredential}.
	 * 
	 * @return a {@link GoogleCredential} used to access Google Sheets.
	 * @throws GeneralSecurityException when there is a problem with google
	 *                                  credentials.
	 * @throws IOException              when credential file is not found.
	 */
	@Bean
	public GoogleCredential googleCredentials() throws GeneralSecurityException, IOException {
		// Get an instance of a JSON Factory.
		JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

		// Creates a transport for google request.
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		// Gets an input stream to the google security file and converts it into a
		// usable format.
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PREFIX + SUFFIX);

		File credentialsFile = inputStreamToFile(inputStream);

		// Sets up all parameters needed to connect to Google Sheets
		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				.setJsonFactory(JSON_FACTORY).setServiceAccountId(CLIENT_ID)
				.setServiceAccountPrivateKeyFromP12File(credentialsFile).setServiceAccountScopes(SCOPES).build();
		// Makes sure connection token is up to date and active.
		credential.refreshToken();
		// Returns credentials
		return credential;
	}

	/**
	 * Creates a new {@link GoogleNetHttpTransport} instance to communicate with
	 * google services..
	 * 
	 * @return a new Google transport object.
	 * @throws GeneralSecurityException when credentials are not configured
	 *                                  properly.
	 * @throws IOException              when credentials file not found.
	 */
	@Bean
	public NetHttpTransport httpTransport() throws GeneralSecurityException, IOException {
		return GoogleNetHttpTransport.newTrustedTransport();
	}

	/**
	 * Creates bean to handle JSON data.
	 * 
	 * @return a {@link JsonFactory} instance.
	 */
	@Bean
	public JsonFactory jsonFactory() {
		return JacksonFactory.getDefaultInstance();
	}

	/**
	 * Build an authorized API service for Google Sheets.
	 * 
	 * @param httpTransport    secure connection between Google Sheets Service and
	 *                         the application.
	 * @param jsonFactory      is used to read and write data to and form the
	 *                         application to Google Sheets Service
	 * @param googleCredential represents the credentials required to access the
	 *                         Google Sheets Service.
	 * @return a {@link Sheets} object that allows various operations to be
	 *         performed on a google sheet.
	 */
	@Bean
	public Sheets sheetsService(NetHttpTransport httpTransport, JsonFactory jsonFactory,
			GoogleCredential googleCredential) {
		return new Sheets.Builder(httpTransport, jsonFactory, googleCredential).setApplicationName("My Application")
				.build();
	}

}
