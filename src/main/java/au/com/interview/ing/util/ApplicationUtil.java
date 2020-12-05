package au.com.interview.ing.util;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import au.com.interview.ing.entity.AddressEntity;
import au.com.interview.ing.entity.UserEntity;
import au.com.interview.ing.model.Address;
import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;

/**
 * Utility class for User Service
 *
 * @author Yagna
 *
 */
public class ApplicationUtil {

	private static String notFound = "Given Employee Id: {0} not found";
	public static final String PATTERN = "^[0-9]{1,15}$";
	public static final String PATTERN_ERROR = "Employee Id must be a number and be between 1 & 15 digits.";

	private ApplicationUtil() {

	}

	/**
	 * Utility function to either retrieve the value from optional of throw 404
	 * error.
	 *
	 * @param <T>
	 * @param t
	 * @param empId
	 * @return
	 */
	public static <T> T getOrElseThrow(Optional<T> t, long empId) {

		return t.orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, MessageFormat.format(notFound, empId)));
	}

	/**
	 * Utility function to set UserEntity data to Response Object.
	 *
	 * @param userEntity
	 * @return {@link ResponseData}
	 */
	public static ResponseData setResponse(UserEntity userEntity) {

		ResponseData response = new ResponseData();

		response.setEmpId(userEntity.getEmpId());
		response.setFirstname(userEntity.getFirstName());
		response.setLastname(userEntity.getLastName());
		response.setGender(userEntity.getGender());
		response.setTitle(userEntity.getTitle());
		response.setAddress(null == userEntity.getAddress() ? null : setAddressInResponse(userEntity.getAddress()));

		return response;
	}

	/**
	 * Utility function to set addressEntity data to Address model.
	 *
	 * @param addressEntity
	 * @return {@link Address}
	 */
	private static Address setAddressInResponse(AddressEntity addressEntity) {

		Address address = new Address();

		address.setCity(addressEntity.getCity());
		address.setPostcode(addressEntity.getPostCd());
		address.setState(addressEntity.getState());
		address.setStreet(addressEntity.getStreet());

		return address;
	}

	/**
	 * Utility function to set Address data to AddressEntity.
	 *
	 * @param addressEntity
	 * @return {@link AddressEntity}
	 */
	public static AddressEntity setDataToAddressEntity(Address address) {

		AddressEntity addressEntity = new AddressEntity();

		addressEntity.setCity(address.getCity());
		addressEntity.setPostCd(address.getPostcode());
		addressEntity.setState(address.getState());
		addressEntity.setStreet(address.getStreet());

		return addressEntity;
	}

	/**
	 * Utility function to set requestData data to userEntity.
	 *
	 * @param addressEntity
	 */
	public static void setDataToUserEntity(RequestData requestData, UserEntity userEntity) {

		userEntity.setFirstName(requestData.getFirstname());
		userEntity.setGender(requestData.getGender());
		userEntity.setLastName(requestData.getLastname());
		userEntity.setTitle(requestData.getTitle());
	}
}
