package de.rauldev.masterspring.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.respository.IProductRepository;
import de.rauldev.masterspring.orderapi.validators.ProductValidator;

@Service
public class ProductService {

	@Autowired
	private IProductRepository productRepository;
	
	
	public List<ProductEntity> findAllProducts(){
		return this.productRepository.findAll();
	}
	
	
	public ProductEntity findProductById(Long id) {
		return this.productRepository.findById(id)
								   	 .orElseThrow(()->new RuntimeException("Not found Product"));
	
	}
	@Transactional
	public ProductEntity saveProduct(ProductEntity product) {
		ProductValidator.validate(product);
		if(product.getId()==null) {
			ProductEntity productEntity = this.productRepository.save(product);
			return productEntity;
		}
		ProductEntity productEntityDB = this.productRepository.findById(product.getId())
                											  .orElseThrow(()->new RuntimeException("Not found Product"));
		
		productEntityDB.setName(product.getName());
		productEntityDB.setPrice(product.getPrice());
		return this.productRepository.save(productEntityDB);
	}
	
	@Transactional
	public void deleteProduct(Long id){
		ProductEntity product = this.productRepository.findById(id)	
													  .orElseThrow(()->new RuntimeException("Not found Product"));	
		this.productRepository.delete(product);
	}
}
