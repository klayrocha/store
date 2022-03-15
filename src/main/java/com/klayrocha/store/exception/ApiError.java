package com.klayrocha.store.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class with error information
 * 
 * @author Francis Klay Rocha
 *
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApiError implements Serializable {

	private static final long serialVersionUID = -7931555626638491204L;
	private HttpStatus status;
	private String message;

	ApiError(HttpStatus status, String message, Throwable ex) {
		this.status = status;
		this.message = message;
	}
}