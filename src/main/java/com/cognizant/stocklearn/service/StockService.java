package com.cognizant.stocklearn.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.stocklearn.model.Stock;
import com.cognizant.stocklearn.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;
	
	@Transactional
	public List<Stock> findByCodeAndDateBetween(String code,Date d1,Date d2)
	{
		return stockRepository.findByCodeAndDateBetween(code, d1, d2);
	}
	
	@Transactional
	public List<Stock> findByCloseGreaterThanEqual(BigDecimal bd)
	{
		return stockRepository.findByCloseGreaterThanEqual(bd);
	}
	
	@Transactional
	public List<Stock> findTop3ByOrderByVolumeDesc()
	{
		return stockRepository.findTop3ByOrderByVolumeDesc();
	}
	
	@Transactional
	public List<Stock> findTop3ByCodeOrderByCloseAsc(String code)
	{
		return stockRepository.findTop3ByCodeOrderByCloseAsc(code);
	}
}
