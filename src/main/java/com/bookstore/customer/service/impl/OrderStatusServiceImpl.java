package com.bookstore.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.OrderStatus;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.repository.OrderStatusRepository;
import com.bookstore.customer.request.OrderStatusRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.OrderStatusResponse;
import com.bookstore.customer.service.OrderStatusService;
import com.bookstore.customer.utils.MessageSupplier;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderStatusServiceImpl implements OrderStatusService{

	@Autowired
	MessageSupplier messageSupplier;
	
	@Autowired
	FetchTokenData fetchTokenData;
	
	@Autowired
	OrderStatusRepository orderStatusRepository;
	
	@Override
	public CommonResponse<String> saveOrderStatus(OrderStatusRequest orderStatusRequest) {
		log.info("Save Order Status Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		String source = RecordSourceCode.ECP_WB.getShortName();
		Optional<OrderStatus> optOrderStatus = orderStatusRepository.findByIdAndRecordStatusCode(orderStatusRequest.getId(),recordStatus);
		if(optOrderStatus.isPresent()) {
			OrderStatus updateOrderStatus = optOrderStatus.get();
			updateOrderStatus.saveOrderStatus(updateOrderStatus,orderStatusRequest,tokenData,recordStatus,source);
			orderStatusRepository.save(updateOrderStatus);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_UPDATE_SUCCESS));
		}
		
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.saveOrderStatus(orderStatus, orderStatusRequest, tokenData, recordStatus, source);
		orderStatusRepository.save(orderStatus);
		return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_SAVE_SUCCESS));
	}

	@Override
	public CommonResponse<List<OrderStatusResponse>> getAllOrderStatus() {
		log.info("Get Order Status Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<OrderStatusResponse> orderStatusResponses = orderStatusRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new OrderStatusResponse(l)).collect(Collectors.toList());
		return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_FETCH_SUCCESS),
				orderStatusResponses);
	}

	@Override
	public CommonResponse<String> dalateOrderStatusById(Long id) {
		log.info("Delete Order Status Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.DISABLED.getShortCode();
		Optional<OrderStatus> optOrderStatus = orderStatusRepository.findById(id);
		if(optOrderStatus.isPresent()) {
			OrderStatus dltOrderStatus = optOrderStatus.get();
			dltOrderStatus.updateOrderStatus(dltOrderStatus,tokenData,recordStatus);
			orderStatusRepository.save(dltOrderStatus);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderStatusConstant.ORDER_STATUS_DELETE_SUCCESS));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderStatusConstant.INVALIE_ORDER_STATUS_ID,id));
	}

}
