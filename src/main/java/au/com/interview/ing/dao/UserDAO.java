package au.com.interview.ing.dao;

import au.com.interview.ing.model.RequestData;
import au.com.interview.ing.model.ResponseData;

public interface UserDAO {

	public ResponseData findUserByEmpId(long empId);

	public void updateUserByEmpid(long empId, RequestData requestData);
}
