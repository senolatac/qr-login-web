package sha.com.qrlogin.dao;


import java.util.List;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sha.com.qrlogin.model.User;
import sha.com.qrlogin.utils.DaoUtil;

@Repository
@Transactional
public class UserDao extends AbstractGenericDao<User> implements IUserDao {
	
	@Override
	public List<User> getAllUsers(){
		return em.createQuery("Select a From User a", User.class).getResultList();
	}

	@Override
	public User find(User user1)
	{
		String hql = "Select a from User a where ( ";
		boolean flag=false;
		if(user1.getEmail()!=null){
			hql+=" a.email=:pemail ";
			flag=true;
		}
		if(user1.getSsoId()!=null){
			if(flag){
				hql+=" or ";
			}
			hql+=" a.ssoId=:pUsername ";
			flag=true;
		}
		if(user1.getUniqueId()!=null){
			if(flag){
				hql+=" or ";
			}
			hql+=" a.uniqueId=:pUniqueId ";
		}
		hql+=")";
		Query query1 = em.createQuery(hql);
		if(user1.getEmail()!=null){
			query1.setParameter("pemail", user1.getEmail());
		}
		if(user1.getSsoId()!=null){
			query1.setParameter("pUsername", user1.getSsoId());
		}
		if(user1.getUniqueId()!=null){
			query1.setParameter("pUniqueId", user1.getUniqueId());
		}
		List<User> user2 = query1.getResultList();
		return (User) DaoUtil.firstOrNull(user2);
	}
	
	@Override
	public User findIfExist(User user1)
	{
		boolean flag = false;
		String hql = "Select a from User a where 1=1 and ( ";
		if(user1.getEmail()!=null){
			hql+="a.email=:pemail ";
			flag = true;
		}
		if(user1.getSsoId()!=null){
			if(flag){
				hql+="or ";
			}
			hql+="a.ssoId=:pUsername ";
			flag = true;
		}
		hql+=") ";
		if(user1.getId()!=null){
			hql+="and a.id!=:pUserId ";
		}

		Query query1 = em.createQuery(hql);
		
		if(user1.getEmail()!=null){
			query1.setParameter("pemail", user1.getEmail());
		}
		
		if(user1.getSsoId()!=null){
			query1.setParameter("pUsername", user1.getSsoId());
		}
		
		if(user1.getId()!=null){
			query1.setParameter("pUserId", user1.getId());
		}
		List<User> user2 = query1.getResultList();
		return (User) DaoUtil.firstOrNull(user2);
	}
	
	@Override
	public User find(String mail)
	{
		Query query1 = em.createQuery("Select a from User a where a.email=:pemail");
		
		query1.setParameter("pemail", mail);
		List<User> user2 = query1.getResultList();
		return (User) DaoUtil.firstOrNull(user2);
	}
	
	@Override
	public User findBySSO(String ssoId)
	{
		Query query1 = em.createQuery("Select a from User a where a.ssoId=:pSsoId");
		
		query1.setParameter("pSsoId", ssoId);
		List<User> user2 = query1.getResultList();
		return (User) DaoUtil.firstOrNull(user2);
	}
	
	@Override
	public User findByUniqueId(String uniqueId)
	{
		Query query1 = em.createQuery("Select a from User a where a.uniqueId=:pSsoId");
		
		query1.setParameter("pSsoId", uniqueId);
		List<User> user2 = query1.getResultList();
		return (User) DaoUtil.firstOrNull(user2);
	}
	
	@Override
	public Boolean isSavedBefore(User user1)
	{
		Query query1 = em.createQuery("Select count(a) from User a where a.email=:pemail or a.ssoId=:pUsername");
		query1.setParameter("pemail", user1.getEmail());
		query1.setParameter("pUsername", user1.getSsoId());
		if( (Long) query1.getSingleResult()>0 )
		return true;
		else return false;
	}

}
