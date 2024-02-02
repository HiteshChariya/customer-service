package com.bookstore.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bookstore.customer.enums.OrderTrakerCode;
import com.bookstore.customer.service.BaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.OrderHistory;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.repository.OrderHistoryRepository;
import com.bookstore.customer.request.OrderHistoryRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.OrderHistoryResponse;
import com.bookstore.customer.service.OrderHistoryService;
import com.bookstore.customer.utils.MessageSupplier;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderHistoryServiceImpl implements OrderHistoryService{

	@Autowired
	MessageSupplier messageSupplier;
	
	@Autowired
	FetchTokenData fetchTokenData;
	
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	@Autowired
	BaseConfigService baseConfigService;
	
	@Override
	public CommonResponse<String> saveOrderHistory(OrderHistoryRequest orderHistoryRequest) {
		log.info("Save Order History Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long statusId = baseConfigService.getConfRecordStatusByShortCode(orderHistoryRequest.getStatus());
		String source = RecordSourceCode.ECP_WB.getShortName();
		Optional<OrderHistory> optOrderHistory = orderHistoryRepository
				.findByIdAndRecordStatusCode(orderHistoryRequest.getId(), recordStatus);
		if(optOrderHistory.isPresent()) {
			OrderHistory updateOrderHistory = optOrderHistory.get();
			updateOrderHistory.saveOrderHistory(updateOrderHistory,tokenData,recordStatus,source,orderHistoryRequest,statusId);
			orderHistoryRepository.save(updateOrderHistory);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.ORDER_HISTORY_UPDATE_SUCCESS));
		}
		
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.saveOrderHistory(orderHistory,tokenData,recordStatus,source,orderHistoryRequest,statusId);
		orderHistoryRepository.save(orderHistory);
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.ORDER_HISTORY_SAVE_SUCCESS));
	}

	@Override
	public CommonResponse<List<OrderHistoryResponse>> getAllOrderHistory() {
		log.info("Get Order History Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<OrderHistoryResponse> orderHistorys = orderHistoryRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new OrderHistoryResponse(l)).collect(Collectors.toList());
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.ORDER_HISTORY_FETCH_SUCCESS),
				orderHistorys);
	}

	@Override
	public CommonResponse<String> deleteOrderHistory(Long id) {
		log.info("Delete Order History Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.DISABLED.getShortCode();
		Optional<OrderHistory> optOrderHistory = orderHistoryRepository
				.findById(id);
		if(optOrderHistory.isPresent()) {
			OrderHistory dltOrderHistory = optOrderHistory.get();
			dltOrderHistory.deleteOrderHistory(dltOrderHistory,tokenData,recordStatus);
			orderHistoryRepository.save(dltOrderHistory);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.ORDER_HISTORY_DELETE_SUCCESS));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.INVALIE_ORDER_HISTORY_ID,id));
	}

	@Override
	public CommonResponse<OrderHistoryResponse> getOrderHistoryById(Long id) {
		log.info("Get Order History By Id Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Optional<OrderHistory> optOrderHistory = orderHistoryRepository.findByIdAndRecordStatusCode(id,recordStatus);
		if(optOrderHistory.isPresent()){
			OrderHistoryResponse orderHistoryResponse = new OrderHistoryResponse(optOrderHistory.get());
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.ORDER_HISTORY_FETCH_SUCCESS),orderHistoryResponse);
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderHistoryConstant.INVALIE_ORDER_HISTORY_ID,id));
	}


}
