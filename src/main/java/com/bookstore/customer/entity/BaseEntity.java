package com.bookstore.customer.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity {

	@Column(name = "dtm_utc_create", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "dtm_utc_update", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(length = 50)
	private String createdBy;

	@Column(length = 50)
	private String updatedBy;

	@Column(length = 10)
	private String recordStatusCode;

	@Column(length = 10)
	private String recordSourceCode;
	
	public BaseEntity() {
	}

	protected void create(String recordStatusCode, String createdBy, String recordSourceCode) {
		this.createdBy = createdBy;
		this.recordStatusCode = recordStatusCode;
		this.recordSourceCode = recordSourceCode;
	}

	protected void update(String recordStatusCode, String updatedBy) {
		this.updatedBy = updatedBy;
		this.recordStatusCode = recordStatusCode;
	}
	
}
