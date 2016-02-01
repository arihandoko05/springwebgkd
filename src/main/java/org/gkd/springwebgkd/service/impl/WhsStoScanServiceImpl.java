/*
 * Created on 19 Jan 2016 ( Time 16:37:21 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.gkd.springwebgkd.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.gkd.springwebgkd.bean.WhsStoScan;
import org.gkd.springwebgkd.bean.jpa.WhsStoScanEntity;
import org.gkd.springwebgkd.repo.WhsStoScanRepository;
import org.gkd.springwebgkd.service.WhsStoScanService;
import org.gkd.springwebgkd.service.mapper.WhsStoScanServiceMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of WhsStoScanService
 */
@Component
@Transactional
public class WhsStoScanServiceImpl implements WhsStoScanService {

	@Resource
	private WhsStoScanRepository whsStoScanRepository;

	@Resource
	private WhsStoScanServiceMapper whsStoScanServiceMapper;
			
	@Override
	public WhsStoScan findById(BigDecimal kdTrx) {
		WhsStoScanEntity entity = whsStoScanRepository.load(kdTrx);
		return whsStoScanServiceMapper.mapWhsStoScanEntityToWhsStoScan(entity);
	}

	@Override
	public List<WhsStoScan> findAll() {
		List<WhsStoScanEntity> entities = whsStoScanRepository.loadAll();
		List<WhsStoScan> beans = new ArrayList<WhsStoScan>();
		for(WhsStoScanEntity entity : entities) {
			beans.add(whsStoScanServiceMapper.mapWhsStoScanEntityToWhsStoScan(entity));
		}
		return beans;
	}

	@Override
	public WhsStoScan save(WhsStoScan whsStoScan) {
		return update(whsStoScan) ;
	}

	@Override
	public WhsStoScan create(WhsStoScan whsStoScan) {
		if(whsStoScanRepository.load(whsStoScan.getKdTrx()) != null) {
			throw new IllegalStateException("already.exists");
		}
		WhsStoScanEntity whsStoScanEntity = new WhsStoScanEntity();
		whsStoScanServiceMapper.mapWhsStoScanToWhsStoScanEntity(whsStoScan, whsStoScanEntity);
		WhsStoScanEntity whsStoScanEntitySaved = whsStoScanRepository.save(whsStoScanEntity);
		return whsStoScanServiceMapper.mapWhsStoScanEntityToWhsStoScan(whsStoScanEntitySaved);
	}

	@Override
	public WhsStoScan update(WhsStoScan whsStoScan) {
		WhsStoScanEntity whsStoScanEntity = whsStoScanRepository.load(whsStoScan.getKdTrx());
		whsStoScanServiceMapper.mapWhsStoScanToWhsStoScanEntity(whsStoScan, whsStoScanEntity);
		WhsStoScanEntity whsStoScanEntitySaved = whsStoScanRepository.save(whsStoScanEntity);
		return whsStoScanServiceMapper.mapWhsStoScanEntityToWhsStoScan(whsStoScanEntitySaved);
	}

	@Override
	public void delete(WhsStoScan whsStoScan, BigDecimal kdTrx) {
		WhsStoScanEntity whsStoScanEntity = whsStoScanRepository.load(whsStoScan.getKdTrx());
		whsStoScanServiceMapper.mapWhsStoScanToWhsStoScanEntity(whsStoScan, whsStoScanEntity);
		whsStoScanRepository.delete(whsStoScanEntity, kdTrx);
	}

	public WhsStoScanRepository getWhsStoScanRepository() {
		return whsStoScanRepository;
	}

	public void setWhsStoScanRepository(WhsStoScanRepository whsStoScanRepository) {
		this.whsStoScanRepository = whsStoScanRepository;
	}

	public WhsStoScanServiceMapper getWhsStoScanServiceMapper() {
		return whsStoScanServiceMapper;
	}

	public void setWhsStoScanServiceMapper(WhsStoScanServiceMapper whsStoScanServiceMapper) {
		this.whsStoScanServiceMapper = whsStoScanServiceMapper;
	}

	@Override
	public List<WhsStoScan> search(Map<String, Object> params) {
		List<WhsStoScanEntity> entities = whsStoScanRepository.search(params, "tglTrx desc", 10);
		List<WhsStoScan> beans = new ArrayList<WhsStoScan>();
		for(WhsStoScanEntity entity : entities) {
			beans.add(whsStoScanServiceMapper.mapWhsStoScanEntityToWhsStoScan(entity));
		}
		return beans;
	}

	@Override
	public BigDecimal getMaxId() {
		return whsStoScanRepository.findMaxKdTrx();
	}

	@Override
	public String openPeriodeSto() {
		return whsStoScanRepository.openPeriodeSto();
	}


}