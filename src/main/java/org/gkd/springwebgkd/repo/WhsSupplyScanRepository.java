/*
 * Created on 22 Des 2015 ( Time 13:48:49 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package org.gkd.springwebgkd.repo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.gkd.springwebgkd.bean.jpa.WhsSupplyScanEntity;
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
public class WhsSupplyScanRepository extends AbstractRepository<WhsSupplyScanEntity, BigDecimal> {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor
	 */
	public WhsSupplyScanRepository() {
		super(WhsSupplyScanEntity.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public boolean delete(WhsSupplyScanEntity entity, BigDecimal id) {
		if (entity != null) {
			return super.delete(entity, entity.getKdTrx());
		}
		return false;
	}

	
	public long countAll() throws PersistenceException {
		try {
			Query query = entityManager.createNamedQuery("WhsSupplyScanEntity.countAll");
			return (long) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public BigDecimal findMaxKdTrx() throws PersistenceException {
		try {
			String sql = "SELECT NVL(MAX(KD_TRX),0) + 1 FROM WHS_SUPPLY_SCAN";
			Query query = entityManager.createNativeQuery(sql);
			return (BigDecimal) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public String openPeriodeLpb() throws PersistenceException {
		try {
			String sql = "SELECT TO_CHAR(TO_DATE(LPAD(BULAN,2,'0')||LPAD(TAHUN,4,'0'), 'MMYYYY'), 'MONTH YYYY') PERIODE "
                    + "FROM TCLOSING WHERE PERIODE_LPB = 'N'";
			Query query = entityManager.createNativeQuery(sql);
			return (String) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	public String openPeriodeBpb() throws PersistenceException {
		try {
			String sql = "SELECT FOPEN_PERIODE_BPB1('N') FROM DUAL";
			Query query = entityManager.createNativeQuery(sql);
			return (String) query.getSingleResult();
		} finally {
			entityManager.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<WhsSupplyScanEntity> findListSupplyHarian(String kdGudang) throws PersistenceException {
		try {
			String sql = "SELECT * FROM (SELECT p.* FROM WHS_SUPPLY_SCAN p "
					+ "WHERE TO_CHAR(TANGGAL_TRX,'YYYYMMDD') = TO_CHAR(SYSDATE,'YYYYMMDD') "
					+ "AND p.ST_INOUT = 'OUT' AND p.KD_GUDANG = '"+kdGudang+"' ORDER BY p.TANGGAL_TRX DESC) WHERE ROWNUM <= 10";
			Query query = entityManager.createNativeQuery(sql, WhsSupplyScanEntity.class);
			System.out.println(sql);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	public BigDecimal findQtySupply(String noBarcode, String bulan, String tahun) {
		try {
			String sql = "SELECT QTY_SUPPLY FROM(SELECT QTY_SUPPLY FROM WHS_SUPPLY_SCAN "
					+ "WHERE BULAN = '"+bulan+"' AND TAHUN = '"+tahun+"' AND NO_BARCODE = '"+noBarcode+"' "
					+ "AND ST_INOUT = 'OUT' ORDER BY KD_TRX DESC) WHERE ROWNUM = 1";
			Query query = entityManager.createNativeQuery(sql);
			return (BigDecimal) query.getSingleResult();
		} catch(NoResultException e){
			return BigDecimal.ZERO;
		} finally {
			entityManager.close();
		}
	}
	
	public BigDecimal getKdTrxBefore(String noBarcode, String kdGudang){
		try {
			String sql = "SELECT KD_TRX FROM (SELECT KD_TRX, NO_BARCODE, QTY_SUPPLY, ST_INOUT FROM WHS_SUPPLY_SCAN WHERE NO_BARCODE = '"+noBarcode+"' "
					+ "AND KD_GUDANG = '"+kdGudang+"' AND ST_INOUT = 'OUT' AND NO_BPB IS NULL ORDER BY TANGGAL_TRX desc) WHERE ROWNUM = 1";
			Query query = entityManager.createNativeQuery(sql);
			return (BigDecimal) query.getSingleResult();
		} finally {
			entityManager.close();
		}
		
	}

}
