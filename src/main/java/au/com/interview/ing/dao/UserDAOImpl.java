package au.com.interview.ing.dao;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import au.com.interview.ing.entity.UserEntity;
import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;
import au.com.interview.ing.repositories.AddressRepository;
import au.com.interview.ing.repositories.UserRepository;
import au.com.interview.ing.util.ApplicationUtil;

/**
 * DAO class implementing the get and update features for User service.
 *
 * @author Yagna
 *
 */
@Service
public class UserDAOImpl implements UserDAO {

	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	private final UserRepository userRepository;

	private final AddressRepository addressRepository;

	public UserDAOImpl(UserRepository userRepository, AddressRepository addressRepository) {

		super();
		this.userRepository = userRepository;
		this.addressRepository = addressRepository;
	}

	/**
	 * Implementation for retrieving user details based on employee id. The function
	 * will search for the emp id and if present, will return User details.
	 */
	@Override
	public ResponseData findUserByEmpId(long empId) {

		log.debug("UserDAOImpl.findUserByEmpId() for Employee Id {}", empId);
		return ApplicationUtil.setResponse(getUserDetails(empId));
	}

	/**
	 * Implementation for updating user and address details based on employee id.
	 * The function will search for the emp id and if present, will update User
	 * details.
	 */
	@Transactional
	@Override
	public void updateUserByEmpid(long empId, RequestData requestData) {

		log.debug("UserDAOImpl.updateUserByEmpid() for Employee Id {}", empId);

		UserEntity userEntity = getUserDetails(empId);

		log.debug("found User details for Employee Id {}", empId);

		// updating user entity from request object.

		ApplicationUtil.setDataToUserEntity(requestData, userEntity);

		// if address object in request is populated, insert address data first, which
		// will provide the primary key which can be used to update the user details
		// table.
		if (null != requestData.getAddress()) {

			log.debug("Request has address details, inserting new record into Address table with current details.");

			// insert new record into address table and set the id into use entity.
			userEntity.setAddressId(
					addressRepository.save(ApplicationUtil.setDataToAddressEntity(requestData.getAddress())).getId());

			log.debug("New record has been created in Address table and the resulting Id is set into User entity.");
		}

		// saving user details to DB

		userRepository.save(userEntity);

		log.debug("User table has been udpated successfully. Exiting function for Employee Id {}", empId);
	}

	private UserEntity getUserDetails(long empId) {
		return ApplicationUtil.getOrElseThrow(userRepository.findById(empId), empId);
	}
}
