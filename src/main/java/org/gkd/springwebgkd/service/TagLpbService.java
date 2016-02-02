/*
 * Created on 23 Des 2015 ( Time 11:12:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.gkd.springwebgkd.bean.TagLpb;


/**
 * Business Service Interface for entity WhsSupplyScan.
 */
public interface TagLpbService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param kdTrx
	 * @return entity
	 */
	TagLpb findById( String noReg  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<TagLpb> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	TagLpb save(TagLpb entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	TagLpb update(TagLpb entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	TagLpb create(TagLpb entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param kdTrx
	 */
	void delete(TagLpb entity, String noreg );

	Date openPeriodeDt();
	
	Date openPeriodeStoDt();
	
	Date openPeriodeBpb();
	
	BigDecimal getQtyMutasiTag(String tahun, String bulan, String kdSite, String noBarcode);
	
	BigDecimal getQtySupply(String noBarcode, String kdSite);
	
	String getNameNpk(String npk);
}
