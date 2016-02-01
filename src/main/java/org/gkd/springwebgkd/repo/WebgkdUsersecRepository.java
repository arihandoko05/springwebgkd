package org.gkd.springwebgkd.repo;

import javax.persistence.*;
import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.gkd.springwebgkd.bean.jpa.WebgkdUsersecEntity;
import org.gkd.springwebgkd.common.repo.AbstractRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Repository
@Transactional(readOnly = true)
public class WebgkdUsersecRepository extends AbstractRepository<WebgkdUsersecEntity, String> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public WebgkdUsersecEntity saveData(WebgkdUsersecEntity account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		try {
			super.save(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public WebgkdUsersecEntity findByEmail(String email) {
		try {
			return entityManager.createNamedQuery(WebgkdUsersecEntity.FIND_BY_EMAIL, WebgkdUsersecEntity.class)
					.setParameter("email", email).getSingleResult();
		} catch (PersistenceException e) {
			return null;
		}
	}
	
    @Override
    protected EntityManager getEntityManager() {
        return this.entityManager;
    }

    public WebgkdUsersecRepository() {
        super(WebgkdUsersecEntity.class);
    }
}
