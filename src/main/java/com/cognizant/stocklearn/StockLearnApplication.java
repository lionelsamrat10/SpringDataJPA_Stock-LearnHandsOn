package com.cognizant.stocklearn;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.stocklearn.model.Stock;
import com.cognizant.stocklearn.service.StockService;


@SpringBootApplication
public class StockLearnApplication {
	
	private static StockService stockService;
	private static final Logger LOGGER = LoggerFactory.getLogger(StockLearnApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Inside main");
		ApplicationContext ctx = SpringApplication.run(StockLearnApplication.class, args);
		stockService = ctx.getBean(StockService.class);
		testGetAllFbStocksInSep();
		testGetAllGoogleStocks();
		testGetTop3Stocks();
		testGetBottom3Stocks();
	}
	
	private static void testGetAllFbStocksInSep()
	{
		LOGGER.info("Starting test");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d1 = sdf.parse("2019-09-01");
			Date d2 = sdf.parse("2019-09-30");
			for(Stock stock : stockService.findByCodeAndDateBetween("FB", d1, d2))
			{
				LOGGER.debug(stock.toString());
			}
		}catch(ParseException pe)
		{
			LOGGER.error(pe.getMessage());
		}
		LOGGER.info("END");
	}
	
	private static void testGetAllGoogleStocks()
	{
		LOGGER.info("Start");
		BigDecimal bd = BigDecimal.valueOf(1250.0);
		for(Stock stock : stockService.findByCloseGreaterThanEqual(bd))
		{
			LOGGER.debug(stock.toString());
		}
		LOGGER.info("End");
	}
	
	private static void testGetTop3Stocks()
	{
		LOGGER.info("Start");
		for(Stock stock : stockService.findTop3ByOrderByVolumeDesc())
		{
			LOGGER.debug(stock.toString());
		}
		LOGGER.info("End");
	}
	
	private static void testGetBottom3Stocks()
	{
		LOGGER.info("Start");
		for(Stock stock : stockService.findTop3ByCodeOrderByCloseAsc("NFLX"))
		{
			LOGGER.debug(stock.toString());
		}
		LOGGER.info("End");
	}
}
