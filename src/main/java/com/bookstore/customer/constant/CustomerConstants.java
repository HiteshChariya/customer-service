package com.bookstore.customer.constant;

import com.bookstore.customer.request.CartDetailRequest;

public final class CustomerConstants {

	private CustomerConstants() {
	}

	public final class CountryConstant{
		private CountryConstant() {
		}
		
		public static final String INVALIE_COUNTRY_ID = "invalid.country.id";
		public static final String COUNTRY_SAVE_SUCCESS = "country.save.success";
		public static final String COUNTRY_DELETE_SUCCESS = "country.delete.success";
		public static final String COUNTRY_UPDATE_SUCCESS = "country.update.success";
		public static final String COUNTRY_FETCH_SUCCESS = "country.fetch.success";
	}
	
	public final class AddressConstant{
		private AddressConstant() {
		}
		
		public static final String INVALIE_ADDRESS_ID = "invalid.address.id";
		public static final String ADDRESS_SAVE_SUCCESS = "address.save.success";
		public static final String ADDRESS_DELETE_SUCCESS = "address.delete.success";
		public static final String ADDRESS_UPDATE_SUCCESS = "address.update.success";
		public static final String ADDRESS_FETCH_SUCCESS = "address.fetch.success";
	}
	
	public final class ShippingMethodConstant{
		private ShippingMethodConstant() {
		}
		
		public static final String INVALIE_SHIPPING_METHOD_ID = "invalid.shipping.method.id";
		public static final String SHIPPING_METHOD_SAVE_SUCCESS = "shipping.method.save.success";
		public static final String SHIPPING_METHOD_DELETE_SUCCESS = "shipping.method.delete.success";
		public static final String SHIPPING_METHOD_UPDATE_SUCCESS = "shipping.method.update.success";
		public static final String SHIPPING_METHOD_FETCH_SUCCESS = "shipping.method.fetch.success";
	}
	
	public final class OrderStatusConstant{
		private OrderStatusConstant() {
		}
		
		public static final String INVALIE_ORDER_STATUS_ID = "invalid.order.status.id";
		public static final String ORDER_STATUS_SAVE_SUCCESS = "order.status.save.success";
		public static final String ORDER_STATUS_DELETE_SUCCESS = "order.status.delete.success";
		public static final String ORDER_STATUS_UPDATE_SUCCESS = "order.status.update.success";
		public static final String ORDER_STATUS_FETCH_SUCCESS = "order.status.fetch.success";
	}
	
	public final class OrderDetailConstant{
		private OrderDetailConstant() {
		}
		
		public static final String INVALIE_ORDER_DETAIL_ID = "invalid.order.detail.id";
		public static final String ORDER_DETAIL_SAVE_SUCCESS = "order.detail.save.success";
		public static final String ORDER_DETAIL_DELETE_SUCCESS = "order.detail.delete.success";
		public static final String ORDER_DETAIL_UPDATE_SUCCESS = "order.detail.update.success";
		public static final String ORDER_DETAIL_FETCH_SUCCESS = "order.detail.fetch.success";
	}
	
	public final class OrderHistoryConstant{
		private OrderHistoryConstant() {
		}
		
		public static final String INVALIE_ORDER_HISTORY_ID = "invalid.order.history.id";
		public static final String ORDER_HISTORY_SAVE_SUCCESS = "order.history.save.success";
		public static final String ORDER_HISTORY_DELETE_SUCCESS = "order.history.delete.success";
		public static final String ORDER_HISTORY_UPDATE_SUCCESS = "order.history.update.success";
		public static final String ORDER_HISTORY_FETCH_SUCCESS = "order.history.fetch.success";
	}

	public final class CartDetailConstant {
		private CartDetailConstant(){
		}
		public static final String INVALID_CART_DETAIL_ID = "invalid.cart.detail.id";
		public static final String CART_DETAIL_SAVE_SUCCESS = "cart.detail.save.success";
		public static final String CART_DETAIL_DELETE_SUCCESS = "cart.detail.delete.success";
		public static final String CART_DETAIL_UPDATE_SUCCESS = "cart.detail.update.success";
		public static final String CART_DETAIL_FETCH_SUCCESS = "cart.detail.fetch.success";

	}

	public final class ReviewRateConstant {
		private ReviewRateConstant(){
		}
		public static final String INVALID_REVIEW_RATE_ID = "invalid.review.rate.id";
		public static final String REVIEW_RATE_SAVE_SUCCESS = "review.rate.save.success";
		public static final String REVIEW_RATE_DELETE_SUCCESS = "review.rate.delete.success";
		public static final String REVIEW_RATE_UPDATE_SUCCESS = "review.rate.update.success";
		public static final String REVIEW_RATE_FETCH_SUCCESS = "review.rate.fetch.success";

	}
	
}
