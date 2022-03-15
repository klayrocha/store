package com.klayrocha.store.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SellProduct implements Serializable {

	private static final long serialVersionUID = -5932579808917204846L;
	private Integer id;
	private Integer quantity;
	private Sell sell;
	private Product product;

}
