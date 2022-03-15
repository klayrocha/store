package com.klayrocha.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.klayrocha.store.model.Product;
import com.klayrocha.store.service.ProductService;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

	private final ProductService service;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@GetMapping(produces = { "application/json" }, consumes = { "application/json" })
	public Single<List<Product>> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit) {

		return Observable.fromIterable(service.getAllProducts(page, limit)).toList();
	}
	
	@GetMapping("/{id}")
    public Single<Product> getById(@PathVariable("id") Integer id) {
		Product product = new Product();
        return Observable.fromCallable(() -> service.getById(id,product))
        		.doOnError(throwable -> logger.error("Error on controller  getById " + throwable.getMessage()))
                .single(product);
    }
	
	@PostMapping(produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<?> create(@Valid @RequestBody Product product) {
		service.create(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(produces = {"application/json"}, consumes = {"application/json"})
    public Single<?> update(@RequestBody Product product) {
        return Observable.fromCallable(() -> service.update(product))
                .doOnError(throwable -> logger.error("Error on controller  update " + throwable.getMessage()))
                .single(product);
    }
	
	@DeleteMapping("/{id}")
    public Completable delete(@PathVariable("id") Integer id) {
        return Completable.fromAction(() -> {
        	service.delete(id);
        });
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
