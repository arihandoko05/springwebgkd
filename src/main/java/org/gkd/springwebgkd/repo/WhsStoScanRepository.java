/*
 * Created on 22 Des 2015 ( Time 13:48:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package org.gkd.springwebgkd.repo;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.gkd.springwebgkd.bean.jpa.WhsStoScanEntity;
import org.gkd.springwebgkd.common.repo.AbstractRepository;
import org.springframework.stereotype.Component;

/**
 * JPA implementation for basic persistence operations ( entity "WhsStoScanEntity"
 * )
 * 
 * @author Telosys Tools Generator
 *
 */
@Component
public class WhsStoScanRepository extends AbstractRepository<WhsStoScanEntity, BigDecimal> {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor
	 */
	public WhsStoScanRepository() {
		super(WhsStoScanEntity.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public boolean delete(WhsStoScanEntity entity, BigDecimal id) {
		if (entity != null) {
			return super.delete(entity, entity.getKdTrx());
		}
		return false;
	}

	
	public long countAll() throws PersistenceException {
		try {
			Query query = entityManager.createNamedQuery("WhsStoScanEntity.countAll");
			return (long) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public BigDecimal findMaxKdTrx() throws PersistenceException {
		try {
			String sql = "SELECT NVL(MAX(KD_TRX),0) + 1 FROM WHS_STO_SCAN";
			Query query = entityManager.createNativeQuery(sql);
			return (BigDecimal) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public String openPeriodeSto() throws PersistenceException {
		try {
			String sql = "SELECT TO_CHAR(TO_DATE(LPAD(BULAN,2,'0')||LPAD(TAHUN,4,'0'), 'MMYYYY'), 'MONTH YYYY') PERIODE "
                    + "FROM TCLOSING WHERE PERIODE_STO = 'N'";
			Query query = entityManager.createNativeQuery(sql);
			return (String) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

}
