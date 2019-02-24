package sha.com.qrlogin.service;

import java.util.List;

import sha.com.qrlogin.model.User;

public interface IUserService {

	User find(Long id);

	User save(User kln);
	
	User update(User kln);

	User findByUser(User selectedKullanici);
	
	User findByMail(String mail);
	
	boolean isSaved(User selectedKullanici);
	
	List<User> findAllUsers();

	void deleteUser(User usr);

	User changeUserRole(User kln);

	User chnageUserActiveState(User kln);

	User changeUserApproveState(User kln);

	User findBySso(String ssoId);

	User findIfExist(User user1);

	void deleteUserById(Long id);

	User findByUniqueId(String uniqueId);

}
