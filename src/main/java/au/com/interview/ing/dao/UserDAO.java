package au.com.interview.ing.dao;

import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;

public interface UserDAO {

	/**
	 * Gets users details (and address) for a given employee Id. Will return 404 is
	 * no Id is found.
	 *
	 * @param empId
	 * @return {@link ResponseData}
	 */
	public ResponseData findUserByEmpId(long empId);

	/**
	 * Updates User and address (if provided) for a given employee id.
	 *
	 * @param empId
	 * @param {@link requestData}
	 */
	public void updateUserByEmpid(long empId, RequestData requestData);
}
