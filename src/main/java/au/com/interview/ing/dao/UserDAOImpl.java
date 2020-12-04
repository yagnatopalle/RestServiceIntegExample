package au.com.interview.ing.dao;

import org.springframework.stereotype.Service;

import au.com.interview.ing.entity.AddressEntity;
import au.com.interview.ing.entity.UserEntity;
import au.com.interview.ing.model.Address;
import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;
import au.com.interview.ing.repositories.UserRepository;
import au.com.interview.ing.util.ApplicationUtil;

@Service
public class UserDAOImpl implements UserDAO {

	private final UserRepository userRepository;

	// Personal preference of constructor injection
	public UserDAOImpl(UserRepository userRepository) {

		super();
		this.userRepository = userRepository;
	}

	@Override
	public ResponseData findUserByEmpId(long empId) {

		return setResponse(ApplicationUtil.getOrElseThrow(userRepository.findById(empId), empId));
	}

	@Override
	public void updateUserByEmpid(long empId, RequestData requestData) {
		// TODO Auto-generated method stub

	}

	private ResponseData setResponse(UserEntity userEntity) {

		ResponseData response = new ResponseData();

		response.setEmpId(userEntity.getEmpId());
		response.setFirstname(userEntity.getFirstName());
		response.setLastname(userEntity.getLastName());
		response.setGender(userEntity.getGender());
		response.setTitle(userEntity.getTitle());
		response.setAddress(setAddressInResponse(userEntity.getAddress()));

		return response;
	}

	private Address setAddressInResponse(AddressEntity addressEntity) {

		Address address = new Address();

		address.setCity(addressEntity.getCity());
		address.setPostcode(addressEntity.getPostCd());
		address.setState(addressEntity.getState());
		address.setStreet(addressEntity.getStreet());

		return address;
	}

}
