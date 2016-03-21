/*
 * Created on 23 Des 2015 ( Time 11:12:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.gkd.springwebgkd.bean.WhsSupplyScanx;

/**
 * Business Service Interface for entity WhsSupplyScanx.
 */
public interface WhsSupplyScanxService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param kdTrx
	 * @return entity
	 */
	WhsSupplyScanx findById( BigDecimal kdTrx ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<WhsSupplyScanx> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	WhsSupplyScanx save(WhsSupplyScanx entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	WhsSupplyScanx update(WhsSupplyScanx entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	WhsSupplyScanx create(WhsSupplyScanx entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param kdTrx
	 */
	void delete(WhsSupplyScanx entity, BigDecimal kdTrx );


	BigDecimal getMaxId();
		
	String openPeriodeBpb();
	
	List<WhsSupplyScanx> findListSupplyHarian(String kdGudang);
	
	List<WhsSupplyScanx> search(Map<String, Object> params, int maxResult);
	
}