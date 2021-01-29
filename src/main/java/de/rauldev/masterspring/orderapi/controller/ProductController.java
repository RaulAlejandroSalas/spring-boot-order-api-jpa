package de.rauldev.masterspring.orderapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.rauldev.masterspring.orderapi.converters.ProductConverter;
import de.rauldev.masterspring.orderapi.dtos.ProductDTO;
import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.services.ProductService;
import de.rauldev.masterspring.orderapi.utils.WrapperResponse;

import java.util.List;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	private ProductConverter converter = new ProductConverter();
	
	//products/?page=1&size=10
	@GetMapping(value="/products")
	public ResponseEntity<WrapperResponse<List<ProductDTO>>> findAllProducts(
			@RequestParam(value="page",required = false,defaultValue = "0") int page,
			@RequestParam(value="size",required = false,defaultValue = "10") int size
			){
		
		Pageable pageable = PageRequest.of(page, size);
		List<ProductEntity> productsDB = this.productService.findAllProducts(pageable);
		
		List<ProductDTO> productDTOs=converter.fromEntity(productsDB);
				  
		return new WrapperResponse<>(true,"SUCCESS",productDTOs)
			      .createResponse(HttpStatus.OK);
	}
	
	
	@GetMapping(value="/products/{id}")
	public ResponseEntity<WrapperResponse<ProductDTO>> findProductById(@PathVariable("id") Long id) {
		ProductEntity productEntity = this.productService.findProductById(id);
		ProductDTO productDTO = converter.fromEntity(productEntity);
		return new WrapperResponse<>(true,"SUCCESS",productDTO)
										      .createResponse(HttpStatus.OK);
	}
	

	@PostMapping(value="/products")
	public ResponseEntity<WrapperResponse<ProductDTO>> createProduct(@RequestBody ProductDTO product){
		ProductEntity productEntity = this.productService.saveProduct(converter.fromDTO(product));
		ProductDTO productDTO = converter.fromEntity(productEntity);
		return new WrapperResponse<>(true,"SUCCESS",productDTO)
			      .createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/products")
	public ResponseEntity<WrapperResponse<ProductDTO>> updateProduct(@RequestBody ProductDTO product){
		ProductEntity productEntity = this.productService.saveProduct(converter.fromDTO(product));
		ProductDTO productDTO = converter.fromEntity(productEntity);
		return new WrapperResponse<>(true,"SUCCESS",productDTO)
			      .createResponse(HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(value="/products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
		this.productService.deleteProduct(id);
		return new WrapperResponse<ProductDTO>(true,"SUCCESS",null)
			      .createResponse(HttpStatus.OK);
	}
	
}
