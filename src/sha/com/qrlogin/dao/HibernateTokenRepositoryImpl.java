package sha.com.qrlogin.dao;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import sha.com.qrlogin.model.PersistentLogin;
import sha.com.qrlogin.utils.DaoUtil;

import java.util.Date;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractGenericDao<PersistentLogin> implements PersistentTokenRepository{
	
	static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);
	
	@Override
    public void createNewToken(PersistentRememberMeToken token) {
        logger.info("Creating Token for user : {}", token.getUsername());
    	Query query1 = em.createQuery("Select a from PersistentLogin a where a.username=:pSeries");
    	query1.setParameter("pSeries", token.getUsername());
        PersistentLogin persistentLogin = (PersistentLogin) DaoUtil.firstOrNull(query1.getResultList());
        if(persistentLogin!=null){
        	persistentLogin.setSeries(token.getSeries());
            persistentLogin.setToken(token.getTokenValue());
            persistentLogin.setLast_used(token.getDate());
            update(persistentLogin);
            return;
        }
        persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        save(persistentLogin);
    }
	
	@Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        logger.info("Fetch Token if any for seriesId : {}", seriesId);
        try {
        	Query query1 = em.createQuery("Select a from PersistentLogin a where a.series=:pSeries");
        	query1.setParameter("pSeries", seriesId);
            PersistentLogin persistentLogin = (PersistentLogin) DaoUtil.firstOrNull(query1.getResultList());
 
            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            logger.info("Token not found...");
            return null;
        }
    }
	
    @Override
    public void removeUserTokens(String username) {
        logger.info("Removing Token if any for user : {}", username);
    	Query query1 = em.createQuery("Select a from PersistentLogin a where a.username=:pSeries");
    	query1.setParameter("pSeries", username);
        PersistentLogin persistentLogin = (PersistentLogin) DaoUtil.firstOrNull(query1.getResultList());
        if (persistentLogin != null) {
            logger.info("rememberMe was selected");
            delete(persistentLogin);
        }
 
    }
    
    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        logger.info("Updating Token for seriesId : {}", seriesId);
    	Query query1 = em.createQuery("Select a from PersistentLogin a where a.series=:pSeries");
    	query1.setParameter("pSeries", seriesId);
        PersistentLogin persistentLogin = (PersistentLogin) DaoUtil.firstOrNull(query1.getResultList());
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }

}
