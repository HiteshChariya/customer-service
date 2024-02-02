package com.bookstore.customer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum RecordSourceCode {

	 ECP_CL("ECP-CL"),ECP_AN("ECP-AN"),ECP_SW("ECP-SW"),ECP_EA("ECP-EA"),ECP_EV("ECP-EV"),
	 ECP_CA("ECP-CA"),ECP_CV("ECP-CV"),ECP_SR("ECP-SR"),ECP_DE("ECP-DE"),ECP_LA("ECP-LA"),
	 ECP_WB("ECP-WB"),ECP_AP("ECP-AP"),ECP_SM("ECP-SM"),ECP_SB("ECP-SB"),ECP_CS("ECP-CS");
	
	private String shortName;
	

	
}
