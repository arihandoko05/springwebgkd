/*
 * Created on 23 Des 2015 ( Time 11:11:56 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.service.mapper;

import org.gkd.springwebgkd.bean.WhsSupplyScan;
import org.gkd.springwebgkd.bean.jpa.WhsSupplyScanEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class WhsSupplyScanServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public WhsSupplyScanServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'WhsSupplyScanEntity' to 'WhsSupplyScan'
	 * @param whsSupplyScanEntity
	 */
	public WhsSupplyScan mapWhsSupplyScanEntityToWhsSupplyScan(WhsSupplyScanEntity whsSupplyScanEntity) {
		if(whsSupplyScanEntity == null) {
			return null;
		}

		//--- Generic mapping 
		WhsSupplyScan whsSupplyScan = map(whsSupplyScanEntity, WhsSupplyScan.class);

		return whsSupplyScan;
	}
	
	/**
	 * Mapping from 'WhsSupplyScan' to 'WhsSupplyScanEntity'
	 * @param whsSupplyScan
	 * @param whsSupplyScanEntity
	 */
	public void mapWhsSupplyScanToWhsSupplyScanEntity(WhsSupplyScan whsSupplyScan, WhsSupplyScanEntity whsSupplyScanEntity) {
		if(whsSupplyScan == null) {
			return;
		}

		//--- Generic mapping 
		map(whsSupplyScan, whsSupplyScanEntity);

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}