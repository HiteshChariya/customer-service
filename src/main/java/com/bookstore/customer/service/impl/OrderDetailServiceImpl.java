package com.bookstore.customer.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bookstore.customer.entity.Address;
import com.bookstore.customer.entity.CartDetail;
import com.bookstore.customer.entity.OrderTrakerStatus;
import com.bookstore.customer.enums.OrderStatusCode;
import com.bookstore.customer.enums.OrderTrakerCode;
import com.bookstore.customer.feign.BookClientService;
import com.bookstore.customer.repository.*;
import com.bookstore.customer.request.AddressRequest;
import com.bookstore.customer.response.*;
import com.bookstore.customer.searchparam.OrderSearchParams;
import com.bookstore.customer.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bookstore.customer.auth.FetchTokenData;
import com.bookstore.customer.auth.TokenData;
import com.bookstore.customer.constant.CustomerConstants;
import com.bookstore.customer.entity.OrderDetail;
import com.bookstore.customer.enums.RecordSourceCode;
import com.bookstore.customer.enums.RecordStatusCode;
import com.bookstore.customer.request.OrderDetailRequest;
import com.bookstore.customer.service.OrderDetailService;
import com.bookstore.customer.utils.MessageSupplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService{

	@Autowired
	MessageSupplier messageSupplier;
	
	@Autowired
	FetchTokenData fetchTokenData;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;

	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Autowired
	BookClientService bookClientService;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	CartDetailServiceRepository cartDetailServiceRepository;

	@Autowired
	OrderTrackerStatusRepository orderTrackerStatusRepository;

	private static final String ORDER_DETAIL_ID = "id";

	@Override
	public CommonResponse<OrderDetailResponse> saveOrderDetail(OrderDetailRequest orderDetailRequest) {
		log.info("Save Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		String source = RecordSourceCode.ECP_WB.getShortName();
		String orderStatus = OrderStatusCode.PENDING.getShortCode();
		Long addressId = null;
		Optional<OrderDetail> optOrderDetail = orderDetailRepository.findByIdAndRecordStatusCode(orderDetailRequest.getId(),recordStatus);
		if(optOrderDetail.isPresent()) {
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,orderDetailRequest.getId()));
		}

		if(null == orderDetailRequest.getAddressId()){
			AddressRequest addressRequest = new AddressRequest(orderDetailRequest);
			Address address = new Address();
			address.saveAddress(address, addressRequest, tokenData, recordStatus, source);
			addressRepository.save(address);
			addressId = address.getId();
		}
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.saveOrderDetail(orderDetail,orderDetailRequest,tokenData,recordStatus,source,addressId,0L);
		orderDetailRepository.save(orderDetail);

		String[] ids = orderDetail.getBookId().split(",");
		OrderDetailResponse orderDetailResponse = getOrderDetailResponse(orderDetail,ids,tokenData);
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_SAVE_SUCCESS),orderDetailResponse);
	}

	@Override
	public CommonResponse<List<OrderDetailResponse>> getAllOrderDetail() {
		log.info("Get Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<OrderDetail> orderDetails = orderDetailRepository.findByCustomerIdAndRecordStatusCode(tokenData.getId(),recordStatus);
		List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();
		for(OrderDetail orderDetail : orderDetails){
			String[] ids = orderDetail.getBookId().split(",");
			OrderDetailResponse orderDetailResponse = getOrderDetailResponse(orderDetail,ids,tokenData);
			orderDetailResponses.add(orderDetailResponse);
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_FETCH_SUCCESS),
				orderDetailResponses);
	}

	@Override
	public CommonResponse<String> deleteOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.DISABLED.getShortCode();
		Optional<OrderDetail> optOrderDetail = orderDetailRepository.findById(id);
		if(optOrderDetail.isPresent()) {
			OrderDetail dltOrderDetail = optOrderDetail.get();
			dltOrderDetail.updateOrderDetail(dltOrderDetail,tokenData,recordStatus);
			orderDetailRepository.save(dltOrderDetail);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
	}

	@Override
	public CommonResponse<List<OrderStatusResponse>> getAllOrderStatus() {
		log.info("Get Order Status Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<OrderStatusResponse> orderStatus = orderStatusRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new OrderStatusResponse(l)).collect(Collectors.toList());
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_FETCH_SUCCESS),
				orderStatus);
	}

	@Override
	public CommonResponse<List<OrderTrackerStatusResponse>> getAllOrderTrackerStatus() {
		log.info("Get Order Status Service..");
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		List<OrderTrackerStatusResponse> orderStatus = orderTrackerStatusRepository.findByRecordStatusCode(recordStatus).stream()
				.map(l -> new OrderTrackerStatusResponse(l)).collect(Collectors.toList());
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_FETCH_SUCCESS),
				orderStatus);
	}

	@Override
	public PagedResponse<OrderDetailResponse> getOrderDetails(OrderSearchParams orderSearchParams) {
		log.info("Author service check ... ");
		String sortParam = ORDER_DETAIL_ID;
		TokenData tokenData = fetchTokenData.getTokenData();
		PageRequestData pageRequestData = orderSearchParams != null
				? Util.getPageRequestData(orderSearchParams.getPageSize(), orderSearchParams.getPageNumber(),
				orderSearchParams.getSortOrder(), sortParam)
				: Util.getPageRequestData(null, null, null, ORDER_DETAIL_ID);
		String status = orderSearchParams.getStatus();
		Long statusId = status == null ? null : orderTrackerStatusID(status);
		int startRecord = (pageRequestData.getPageNumber() * pageRequestData.getPageSize()) + 1;
		Sort sort = Util.getSortingOrder(pageRequestData.getSortOrder(), getOrderDetailSortParam(sortParam));
		Pageable pageable = PageRequest.of(pageRequestData.getPageNumber(), pageRequestData.getPageSize(), sort);
		Page<OrderDetailResponse> orderDetailPage = orderDetailRepository
				.findAllOrders(statusId,RecordStatusCode.ENABLED.getShortCode(), pageable)
				.map(response -> new OrderDetailResponse(response));
		orderDetailPage.forEach(response -> {
			String[] productIds = response.getBookId().split(",");
			Long discount = 0L, totalPrice = 0L, totalAmount = 0L, totalItems = 0L;
			List<BookResponse> bookResponses = new ArrayList<>();
			for (String id : productIds){
				BookResponse book = getBookClientDetail(Long.parseLong(id),tokenData);
				CartDetail cartDetail = new CartDetail();
				Optional<CartDetail> optCartDetail = cartDetailServiceRepository.findByBookIdAndRecordStatusCode(Long.parseLong(id),RecordStatusCode.ENABLED.getShortCode());
				if(optCartDetail.isPresent())
					cartDetail = optCartDetail.get();

				totalItems = totalItems + 1;
				discount = (book.getPrice() * book.getDiscount() / 100) + discount;
				totalPrice = (book.getPrice() * cartDetail.getCartItem()) + totalPrice ;
				book.setCartItem(cartDetail.getCartItem());
				bookResponses.add(book);
			}
			totalAmount = totalPrice - discount;
			response.setItemPrice(totalPrice);
			response.setDiscountPrice(discount);
			response.setTotalItem(totalItems);
			response.setTotalPrice(totalAmount);
			response.setOrderItems(bookResponses);
		});
		return orderDetailPage.isEmpty() ? PagedResponse.empty()
				: PagedResponse.of(orderDetailPage.getContent(), startRecord, pageRequestData.getPageSize(),
				(int) orderDetailPage.getTotalElements());
	}

	@Override
	public CommonResponse<OrderDetailResponse> getOrderById(Long id) {
		log.info("Get Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Optional<OrderDetail> optOrderDetail = orderDetailRepository.findByIdAndRecordStatusCode(id,recordStatus);
		if(optOrderDetail.isPresent()){
			OrderDetail orderDetail = optOrderDetail.get();
			String[] ids = orderDetail.getBookId().split(",");
			OrderDetailResponse orderDetailResponse = getOrderDetailResponse(orderDetail,ids,tokenData);
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_SAVE_SUCCESS),orderDetailResponse);
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
	}

	@Override
	public CommonResponse<OrderDetailResponse> confirmOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.CONFIRMED.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}

		String[] ids = orderDetail.getBookId().split(",");
		for(String bookId : ids){
			CartDetail cartDetail = new CartDetail();
			Optional<CartDetail> optCartDetail = cartDetailServiceRepository.findByBookIdAndRecordStatusCode(Long.parseLong(bookId),RecordStatusCode.ENABLED.getShortCode());
			if(optCartDetail.isPresent()){
				cartDetail = optCartDetail.get();
				cartDetail.updateCart(cartDetail,recordStatus,tokenData,orderStatus);
				cartDetailServiceRepository.save(cartDetail);
			}
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<OrderDetailResponse> shippedOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.SHIPPED.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<OrderDetailResponse> deliverOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.DELIVERED.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<OrderDetailResponse> placedOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.PLACED.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<OrderDetailResponse> cancelOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.CANCELLED.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<OrderDetailResponse> outForDeliverOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.OUT_FOR_DELIVERY.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	@Override
	public CommonResponse<OrderDetailResponse> returnOrderDetail(Long id) {
		log.info("Delete Order Detail Service..");
		TokenData tokenData = fetchTokenData.getTokenData();
		String recordStatus = RecordStatusCode.ENABLED.getShortCode();
		Long orderStatus = orderTrackerStatusID(OrderTrakerCode.OUT_FOR_DELIVERY.getTraker());
		OrderDetail orderDetail = updateOrderStatus(id,tokenData,recordStatus,orderStatus);
		if(null == orderDetail){
			return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.INVALIE_ORDER_DETAIL_ID,id));
		}
		return CommonResponse.success(messageSupplier.get(CustomerConstants.OrderDetailConstant.ORDER_DETAIL_DELETE_SUCCESS));
	}

	private OrderDetailResponse getOrderDetailResponse(OrderDetail orderDetail, String[] productIds, TokenData tokenData) {
		Long discount = 0L, totalPrice = 0L, totalAmount = 0L, totalItems = 0L;
		List<BookResponse> bookResponses = new ArrayList<>();
		log.info("id List {} ",productIds);
		for (String id : productIds){
			BookResponse book = getBookClientDetail(Long.parseLong(id),tokenData);
			if(null == book)
				return null;

			CartDetail cartDetail = new CartDetail();
			Optional<CartDetail> optCartDetail = cartDetailServiceRepository.findByBookIdAndRecordStatusCode(Long.parseLong(id),RecordStatusCode.ENABLED.getShortCode());
			if(optCartDetail.isPresent())
				cartDetail = optCartDetail.get();

			totalItems = totalItems + 1;
			discount = (book.getPrice() * book.getDiscount() / 100) + discount;
			totalPrice = (book.getPrice() * cartDetail.getCartItem()) + totalPrice ;
			book.setCartItem(cartDetail.getCartItem());
			bookResponses.add(book);
		}
		totalAmount = totalPrice - discount;
		return new OrderDetailResponse(orderDetail,totalPrice,discount,totalItems,totalAmount,bookResponses);
	}

	private BookResponse getBookClientDetail(Long bookId, TokenData tokenData) {
		String response = bookClientService.findBookById(bookId);
		log.info("response -> {}", response);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			BookServiceResponse bookServiceResponse = mapper.readValue(response, BookServiceResponse.class);
			List<BookResponse> bookResponses = bookServiceResponse.getResponse().stream().collect(Collectors.toList());
			if(!bookResponses.isEmpty())
				return bookResponses.get(0);

		} catch (JsonProcessingException e) {
			log.error("ERROR WHILE CONVERT VALUES - > {}", e.getMessage(), e);
		}
		return null;
	}

	private OrderDetail updateOrderStatus(Long id, TokenData tokenData,String recordStatus,Long orderStatusId){
		Optional<OrderDetail> optOrderDetail = orderDetailRepository.findById(id);
		if(optOrderDetail.isPresent()){
			OrderDetail orderDetail = optOrderDetail.get();
			orderDetail.updateOrderStatus(orderDetail,orderStatusId,tokenData,recordStatus);
			orderDetailRepository.save(orderDetail);
			return orderDetail;
		}
		return null;
	}

	private Long orderTrackerStatusID(String orderTrackerStatus){
		Optional<OrderTrakerStatus> optTrackerStatus = orderTrackerStatusRepository.findByOrderTrakerStatue(orderTrackerStatus);
        return optTrackerStatus.map(OrderTrakerStatus::getId).orElse(null);
    }

	private String getOrderDetailSortParam(String sortParam) {
		return sortParam;
	}
}
