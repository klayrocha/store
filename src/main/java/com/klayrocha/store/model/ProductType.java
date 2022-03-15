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
public class ProductType implements Serializable, Comparable<ProductType> {

	private static final long serialVersionUID = -514532133135335680L;
	private Integer id;
	private String description;

	@Override
	public int compareTo(ProductType o) {
		return this.description.compareTo(o.getDescription());
	}

}
