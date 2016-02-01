/*
 * Created on 26 Jan 2016 ( Time 08:40:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.service.mapper;

import org.gkd.springwebgkd.bean.WhsSjScan;
import org.gkd.springwebgkd.bean.jpa.WhsSjScanEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class WhsSjScanServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public WhsSjScanServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'WhsSjScanEntity' to 'WhsSjScan'
	 * @param whsSjScanEntity
	 */
	public WhsSjScan mapWhsSjScanEntityToWhsSjScan(WhsSjScanEntity whsSjScanEntity) {
		if(whsSjScanEntity == null) {
			return null;
		}

		//--- Generic mapping 
		WhsSjScan whsSjScan = map(whsSjScanEntity, WhsSjScan.class);

		return whsSjScan;
	}
	
	/**
	 * Mapping from 'WhsSjScan' to 'WhsSjScanEntity'
	 * @param whsSjScan
	 * @param whsSjScanEntity
	 */
	public void mapWhsSjScanToWhsSjScanEntity(WhsSjScan whsSjScan, WhsSjScanEntity whsSjScanEntity) {
		if(whsSjScan == null) {
			return;
		}

		//--- Generic mapping 
		map(whsSjScan, whsSjScanEntity);

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