package com.bookstore.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.ShippingMethod;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.repository.ShippingMethodRepository;
import com.bookstore.customer.request.ShippingMethodRequest;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.response.ShippingMethodResponse;
import com.bookstore.customer.service.ShippingMethodService;
import com.bookstore.customer.utils.MessageSupplier;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingMethodServiceImpl implements ShippingMethodService {

	@Autowired
	MessageSupplier messageSupplier;
	
	@Autowired
	FetchTokenData fetchTokenData;

	@Autowired
	ShippingMethodRepository shippingMethodRepository;

	@Override
	public CommonResponse<String> saveShippingMethod(ShippingMethodRequest shippingMethodRequest) {
		log.info("Save Country Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		String source = RecordSourceCode.ECP_WB.getShortName();
		Optional<ShippingMethod> optShippingMethod = shippingMethodRepository
				.findByIdAndRecordStatusCode(shippingMethodRequest.getId(), recordStatus);
		if(optShippingMethod.isPresent()) {
			ShippingMethod updateShippingMethod = optShippingMethod.get();
			updateShippingMethod.saveShippingMethod(updateShippingMethod,tokenData,recordStatus,source,shippingMethodRequest);
			shippingMethodRepository.save(updateShippingMethod);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_UPDATE_SUCCESS));
		}
		
		ShippingMethod shippingMethod = new ShippingMethod();
		shippingMethod.saveShippingMethod(shippingMethod, tokenData, recordStatus, source, shippingMethodRequest);
		shippingMethodRepository.save(shippingMethod);
		return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_SAVE_SUCCESS));
	}

	@Override
	public CommonResponse<List<ShippingMethodResponse>> getAllShippingMethod() {
		log.info("Save Country Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<ShippingMethodResponse> shippingMethods = shippingMethodRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new ShippingMethodResponse(l)).collect(Collectors.toList());
		return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_FETCH_SUCCESS),
				shippingMethods);
	}

	@Override
	public CommonResponse<String> dalateShippingMethodById(Long id) {
		log.info("Save Country Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.DISABLED.getShortCode();
		Optional<ShippingMethod> optShippingMethod = shippingMethodRepository.findById(id);
		if(optShippingMethod.isPresent()) {
			ShippingMethod dltShippingMethod = optShippingMethod.get();
			dltShippingMethod.updateShippingMethod(dltShippingMethod,tokenData,recordStatus);
			shippingMethodRepository.save(dltShippingMethod);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.SHIPPING_METHOD_DELETE_SUCCESS));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.ShippingMethodConstant.INVALIE_SHIPPING_METHOD_ID,id));
	}

}
