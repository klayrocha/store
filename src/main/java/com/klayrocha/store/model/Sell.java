package com.klayrocha.store.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
public class Sell implements Serializable, Comparable<Sell> {
	
	private static final long serialVersionUID = 8383651956190705299L;
	private Integer id;
	private BigDecimal value;
	private LocalDateTime dateSell;
	private List<SellProduct> products;
	
	@Override
	public int compareTo(Sell o) {
		return this.dateSell.compareTo(o.getDateSell());
	}
	
}
