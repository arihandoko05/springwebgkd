/*
 * Created on 22 Des 2015 ( Time 13:48:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package org.gkd.springwebgkd.repo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.gkd.springwebgkd.bean.jpa.TagLpbEntity;
import org.gkd.springwebgkd.common.repo.AbstractRepository;
import org.springframework.stereotype.Component;

/**
 * JPA implementation for basic persistence operations ( entity "WhsSupplyScan"
 * )
 * 
 * @author Telosys Tools Generator
 *
 */
@Component
public class TagLpbRepository extends AbstractRepository<TagLpbEntity, String> {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor
	 */
	public TagLpbRepository() {
		super(TagLpbEntity.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public boolean delete(TagLpbEntity entity, String id) {
		if (entity != null) {
			return super.delete(entity, entity.getNoReg());
		}
		return false;
	}

	
	public long countAll() throws PersistenceException {
		try {
			Query query = entityManager.createNamedQuery("TagLpbEntity.countAll");
			return (long) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public Date openPeriodeLpbDt() throws PersistenceException {
		try {
			String sql = "SELECT * FROM (SELECT CLOSING_DATE FROM TCLOSING WHERE PERIODE_LPB = 'N' ORDER BY CLOSING_DATE DESC) WHERE ROWNUM = 1";
			Query query = entityManager.createNativeQuery(sql);
			return (Date) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public Date openPeriodeStoDt() throws PersistenceException {
		try {
			String sql = "SELECT * FROM (SELECT CLOSING_DATE FROM TCLOSING WHERE PERIODE_STO = 'N' ORDER BY CLOSING_DATE DESC) WHERE ROWNUM = 1";
			Query query = entityManager.createNativeQuery(sql);
			return (Date) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public Date openPeriodeBpb() throws PersistenceException {
		try {
			String sql = "SELECT * FROM (SELECT CLOSING_DATE FROM TCLOSING WHERE PERIODE_BPB = 'N' ORDER BY CLOSING_DATE DESC) WHERE ROWNUM = 1";
			Query query = entityManager.createNativeQuery(sql);
			return (Date) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public BigDecimal getQtyMutasiTag(String tahun, String bulan, String kdSite, String noBarcode) throws PersistenceException {
		try {
			String sql = "SELECT FWHS_MUTASI_TAG('"+tahun+"', '"+bulan+"', '"+kdSite+"', '"+noBarcode+"', 'QTY_AKHIR') FROM DUAL";
			Query query = entityManager.createNativeQuery(sql);
			return (BigDecimal) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	} 
	
	
	public BigDecimal getQtySupply(String noBarcode, String kdSite) throws PersistenceException {
		try {
			String sql = "SELECT FQTY_SCAN_SUPPLY('"+noBarcode+"', '"+kdSite+"') FROM DUAL";
			Query query = entityManager.createNativeQuery(sql);
			return (BigDecimal) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	} 
	
	public String getNameNpk(String npk) throws PersistenceException {
		try {
			String sql = "SELECT USRHRCORP.FNM_NPK('"+npk+"') FROM DUAL";
			Query query = entityManager.createNativeQuery(sql);
			return (String) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}

}
