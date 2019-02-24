package sha.com.qrlogin.dao;

import java.util.List;

import sha.com.qrlogin.model.User;

public interface IUserDao extends IGenericDao<User>{

	List<User> getAllUsers();

	User find(User user1);

	Boolean isSavedBefore(User user1);

	User find(String mail);

	User findBySSO(String ssoId);

	User findIfExist(User user1);

	User findByUniqueId(String uniqueId);

}
