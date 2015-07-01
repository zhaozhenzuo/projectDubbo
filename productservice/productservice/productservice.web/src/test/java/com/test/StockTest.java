package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.test.productservice.dao.SkuStockMapper;
import com.test.productservice.domain.SkuStock;
import com.test.productservice.service.inf.SkuService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { TestCommonConfig.class, PropertyPlaceholderAutoConfiguration.class })
// @ContextConfiguration(classes = { TestCommonConfig.class,
// PropertyPlaceholderAutoConfiguration.class })
@ActiveProfiles("dev")
public class StockTest implements ApplicationContextAware {

	static {
		System.setProperty("spring.profiles.active", "dev");
		System.setProperty("configPath", "/home/zhao/work/config");
	}

	@Autowired
	private SkuService skuService;

	@Autowired
	private SkuStockMapper skuStockMapper;

	private static ApplicationContext applicationContext;

	private static final CountDownLatch countDownLatch = new CountDownLatch(10);

	private static final Logger logger = LoggerFactory.getLogger(StockTest.class);

	@Test
	public void testStock() throws Exception {
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		List<Future<Boolean>> futureList = new ArrayList<>();
		
		for (int i = 0; i < 10; i++) {
			MockBuyTask mockBuyTask = new MockBuyTask();
			futureList.add(executorService.submit(mockBuyTask));
		}

		int sucCount=0;
		for(Future<Boolean> future:futureList){
			if(future.get()){
				sucCount++;
			}
			logger.info(future.get().toString());
		}
		
		logger.info("共"+sucCount+"人成功购物一件商品");
		Assert.assertTrue(sucCount==5);
		SkuStock skuStock = skuStockMapper.selectPoByPrimaryKey(7L);
		Assert.assertTrue(skuStock != null && skuStock.getSkuId()==8L && skuStock.getCount() == 0);

	}

	static class MockBuyTask implements Callable<Boolean> {

		@Override
		public Boolean call() throws Exception {
			countDownLatch.countDown();
			logger.info("+wait for other man to run,thread:" + Thread.currentThread().getId());
			countDownLatch.await();

			SkuStock skuStock = new SkuStock();
			skuStock.setSkuId(8L);
			skuStock.setCount(1);
			SkuStockMapper skuStockMapper = applicationContext.getBean(SkuStockMapper.class);
			int rowCount = skuStockMapper.updateStockBySkuIdAndCountOfBuy(skuStock);
			logger.info("+rowcount:" + rowCount);
			return rowCount > 0 ? true : false;
		}

	}

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
}
