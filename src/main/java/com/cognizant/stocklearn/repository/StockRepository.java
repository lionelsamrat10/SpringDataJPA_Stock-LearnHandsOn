package com.cognizant.stocklearn.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.stocklearn.model.Stock;

public interface StockRepository extends JpaRepository<Stock,Integer> {
	
	public List<Stock> findByCodeAndDateBetween(String code,Date d1,Date d2);

	public List<Stock> findByCloseGreaterThanEqual(BigDecimal bd);
	
	public List<Stock> findTop3ByOrderByVolumeDesc();
	
	public List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
