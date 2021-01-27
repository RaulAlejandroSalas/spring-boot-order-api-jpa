package de.rauldev.masterspring.orderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.respository.IProductRepository;
import de.rauldev.masterspring.orderapi.services.ProductService;

import java.util.List;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/products")
	public ResponseEntity<List<ProductEntity>> findAllProducts(){
		return new ResponseEntity<List<ProductEntity>>(this.productService.findAllProducts(),HttpStatus.OK);
	}
	
	
	@GetMapping(value="/products/{id}")
	public ResponseEntity<ProductEntity> findProductById(@PathVariable("id") Long id) {
		return new ResponseEntity<ProductEntity>(this.productService.findProductById(id),HttpStatus.OK);
	}
	

	@PostMapping(value="/products")
	public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product){
		return new ResponseEntity<ProductEntity>(this.productService.saveProduct(product),HttpStatus.CREATED);
	}
	
	@PutMapping(value="/products")
	public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity product){
		return new ResponseEntity<ProductEntity>(this.productService.saveProduct(product),HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(value="/products/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id){
		this.productService.deleteProduct(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
