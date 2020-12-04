package au.com.interview.ing.util;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ApplicationUtil {

	private static String notFound = "Given Employee Id: {0} not found";
	public static final String PATTERN = "^[0-9]{1,15}$";
	public static final String PATTERN_ERROR = "Employee Id must be a number and be between 1 & 15 digits.";

	private ApplicationUtil() {

	}

	public static <T> T getOrElseThrow(Optional<T> t, long empId) {

		return t.orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MessageFormat.format(notFound, empId)));
	}
}
