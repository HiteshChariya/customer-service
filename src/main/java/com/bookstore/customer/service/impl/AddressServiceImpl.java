package com.bookstore.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.Address;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.repository.AddressRepository;
import com.bookstore.customer.request.AddressRequest;
import com.bookstore.customer.response.AddressResponse;
import com.bookstore.customer.response.CommonResponse;
import com.bookstore.customer.service.AddressService;
import com.bookstore.customer.utils.MessageSupplier;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

	@Autowired
	MessageSupplier messageSupplier;

	@Autowired
	FetchTokenData fetchTokenData;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public CommonResponse<String>  saveShiipingAddress(AddressRequest addressRequest) {
		log.info("Save Country Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		String source = RecordSourceCode.ECP_WB.getShortName();

		Optional<Address> optAddress = addressRepository.findByIdAndRecordStatusCode(addressRequest.getAddressId(),
				recordStatus);
		if (optAddress.isPresent()) {
			Address updateAddress = optAddress.get();
			updateAddress.saveAddress(updateAddress, addressRequest, tokenData, recordStatus, source);
			addressRepository.save(updateAddress);
			return CommonResponse
					.success(messageSupplier.get(CustomerConstants.AddressConstant.ADDRESS_UPDATE_SUCCESS));
		}

		Address address = new Address();
		address.saveAddress(address, addressRequest, tokenData, recordStatus, source);
		addressRepository.save(address);

		return CommonResponse.success(messageSupplier.get(CustomerConstants.AddressConstant.ADDRESS_SAVE_SUCCESS));
	}

	@Override
	public CommonResponse<List<AddressResponse>> getAllAddress() {
		log.info("Save Address Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<AddressResponse> addresses = addressRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new AddressResponse(l)).collect(Collectors.toList());

		addresses.forEach(address -> {
			address.setCustomer(tokenData.getFirstName());
		});
		return CommonResponse.success(messageSupplier.get(CustomerConstants.AddressConstant.ADDRESS_FETCH_SUCCESS),
				addresses);
	}

	@Override
	public CommonResponse<String> dalateAddressById(Long id) {
		log.info("Delete Address Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String status = RecordStatusCode.DISABLED.getShortCode();
		Optional<Address> optAddress = addressRepository.findById(id);
		if (optAddress.isEmpty())
			return CommonResponse
					.failure(messageSupplier.get(CustomerConstants.AddressConstant.INVALIE_ADDRESS_ID, id));

		Address address = optAddress.get();
		address.deleteAddress(address, tokenData, status);
		addressRepository.save(address);
		return CommonResponse.failure(messageSupplier.get(CustomerConstants.AddressConstant.ADDRESS_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<List<AddressResponse>> getAddressesByCustomer(Long id) {
		log.info("Get Address Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String status = RecordStatusCode.ENABLED.getShortCode();
		List<AddressResponse> addressResponse = new ArrayList<>();
		List<Address> addresses = addressRepository.findByCustomerIdAndRecordStatusCode(id, status);
		if (addresses.isEmpty())
			return CommonResponse
					.failure(messageSupplier.get(CustomerConstants.AddressConstant.INVALIE_ADDRESS_ID, id));

		addressResponse = addresses.stream().map(l -> new AddressResponse(l)).collect(Collectors.toList());
		addressResponse.forEach(address -> {
			address.setCustomer(tokenData.getFirstName());
		});
		return CommonResponse.success(messageSupplier.get(CustomerConstants.AddressConstant.ADDRESS_FETCH_SUCCESS),
				addressResponse);
	}

	private Address getAddressById(Long addressId) {
		Optional<Address> optAddress = addressRepository.findById(addressId);
		if(optAddress.isPresent())
			return optAddress.get();
		
		return null;
	}
}
