package de.rauldev.masterspring.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.rauldev.masterspring.orderapi.entities.ProductEntity;
import de.rauldev.masterspring.orderapi.exceptions.GeneralServiceException;
import de.rauldev.masterspring.orderapi.exceptions.NotDataFoundException;
import de.rauldev.masterspring.orderapi.exceptions.ValidateServiceException;
import de.rauldev.masterspring.orderapi.repository.IProductRepository;
import de.rauldev.masterspring.orderapi.validators.ProductValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	private static final String NOT_PRODUCT_FOUND = "Not found Product";

	@Autowired
	private IProductRepository productRepository;
	
	
	public List<ProductEntity> findAllProducts(Pageable pageable){
		try {
			log.debug("findAllProducts => ");
			
			return this.productRepository.findAll(pageable).toList();
		} catch (ValidateServiceException | NotDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	
	}
	
	
	public ProductEntity findProductById(Long id) {
		try {
			log.debug("findProductById => " + id);
			return this.productRepository.findById(id).orElseThrow(()->new NotDataFoundException(NOT_PRODUCT_FOUND));
		} catch (ValidateServiceException | NotDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	
	}
	@Transactional
	public ProductEntity saveProduct(ProductEntity product) {
		ProductValidator.validate(product);
		if(product.getId()==null) {
			try {
				log.debug("saveProduct => " + product.toString());
				return  this.productRepository.save(product);
			} catch (ValidateServiceException | NotDataFoundException e) {
				log.info(e.getMessage(), e);
				throw e;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new GeneralServiceException(e.getMessage(), e);
			}
		}
		try {
			log.debug("findProductById => " + product.getId());

			ProductEntity productEntityDB = this.productRepository
												.findById(product.getId())
												.orElseThrow(()->new NotDataFoundException(NOT_PRODUCT_FOUND));
			productEntityDB.setName(product.getName());
			productEntityDB.setPrice(product.getPrice());
			return this.productRepository.save(productEntityDB);
		} catch (ValidateServiceException | NotDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
		}
	
	@Transactional
	public void deleteProduct(Long id){
		
		try {
			log.debug("deleteProduct => " + id);
			
			ProductEntity product = this.productRepository.findById(id)	
														  .orElseThrow(()->new NotDataFoundException(NOT_PRODUCT_FOUND));
			this.productRepository.delete(product);
		} catch (ValidateServiceException | NotDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
