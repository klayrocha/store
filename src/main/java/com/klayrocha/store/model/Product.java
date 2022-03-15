package com.klayrocha.store.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class Product implements Serializable, Comparable<Product> {

	private static final long serialVersionUID = -6236405833037391156L;
	private Integer id;
	@NotBlank(message = "Name is mandatory")
	@NotNull(message = "Name is mandatory")
	private String name;
	@NotNull(message = "Quantity is mandatory")
	private Integer quantity;
	private ProductType productType;

	@Override
	public int compareTo(Product o) {
		return this.name.compareTo(o.getName());
	}

}
