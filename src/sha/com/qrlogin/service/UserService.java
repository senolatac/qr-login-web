package sha.com.qrlogin.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.com.qrlogin.dao.IUserDao;
import sha.com.qrlogin.model.User;


@Service("userService")
@Transactional
public class UserService implements IUserService{
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	long tempId = 0;
	
	@Autowired
    private IUserDao userDao;
	
    @Override
    public User find(Long id){
    	return userDao.find(id);
    }
    
    public List<User> getAllEmployees() {
        return userDao.getAllUsers();
    }
    
    @Override
    public User findBySso(String ssoId){
    	return this.userDao.findBySSO(ssoId);
    }
    
    @Override
    public User findByUniqueId(String uniqueId){
    	return this.userDao.findByUniqueId(uniqueId);
    }
    
    @Override
    public List<User> findAllUsers(){
    	return this.userDao.findAll();
    }
    
    @Override
    public void deleteUser(User usr){
    	this.userDao.delete(this.userDao.getReference(usr.getId()));
    }
    
    @Override
    public void deleteUserById(Long id){
    	this.userDao.delete(this.userDao.getReference(id));
    }
    
    @Override
    public User save(User usr)
    {
    	usr.setPassword(passwordEncoder.encode(usr.getPassword()));
    	userDao.save(usr);
    	return usr;
    }
    
    @Override
    public User update(User usr)
    {
    	User daoUser = this.userDao.find(usr.getId());
    	daoUser.setName(usr.getName());
    	daoUser.setPassword(usr.getPassword());
    	daoUser.setEmail(usr.getEmail());
    	return userDao.update(daoUser);
    }
    
    @Override
    public User changeUserRole(User usr)
    {
    	User daoUser = this.userDao.find(usr.getId());
    	daoUser.setAdmin(usr.getAdmin());
    	daoUser.setActive(usr.getAdmin()?Boolean.TRUE:usr.getActive());
    	return userDao.update(daoUser);
    }
    
    @Override
    public User chnageUserActiveState(User usr)
    {
    	User daoUser = this.userDao.find(usr.getId());
    	daoUser.setActive(usr.getActive());
    	return userDao.update(daoUser);
    }
    
    @Override
    public User changeUserApproveState(User usr)
    {
    	User daoUser = this.userDao.find(usr.getId());
    	daoUser.setConfirm(usr.getConfirm());
    	return userDao.update(daoUser);
    }
    
    @Override
    public User findByUser(User user1)
    {
    	return userDao.find(user1);
    }
    
    @Override
    public User findByMail(String mail)
    {
    	return userDao.find(mail);
    }
    
    @Override
    public User findIfExist(User user1)
    {
    	return userDao.findIfExist(user1);
    }
    
    @Override
	public boolean isSaved(User user1) {
		return userDao.isSavedBefore(user1);
	}
    
}