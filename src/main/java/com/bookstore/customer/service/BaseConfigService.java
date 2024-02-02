package com.bookstore.customer.service;

import com.bookstore.customer.entity.OrderTrakerStatus;
import com.bookstore.customer.enums.OrderTrakerCode;
import com.bookstore.customer.repository.OrderStatusTrakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class BaseConfigService {

	@Autowired
	OrderStatusTrakerRepository orderStatusTrakerRepository;

	private Map<String, OrderTrakerStatus> orderTrakerStatusData;

	@PostConstruct
	public void loadBaseConfig() {
		loadOrderTrakerStatusData();
	}

	private void loadOrderTrakerStatusData() {
		List<OrderTrakerStatus> confOrderStatuses = orderStatusTrakerRepository.findAll();
		orderTrakerStatusData = confOrderStatuses.parallelStream().collect(Collectors
				.toMap(confOrderStatus -> confOrderStatus.getOrderTrakerStatue(), confOrderStatus -> confOrderStatus));
	}

	public Long getConfRecordStatusByShortCode(OrderTrakerCode orderTrakerCode) {
		return orderTrakerStatusData.get(orderTrakerCode.getTraker()).getId();
	}

}
