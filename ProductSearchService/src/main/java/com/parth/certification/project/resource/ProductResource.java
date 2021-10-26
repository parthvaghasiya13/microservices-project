package com.parth.certification.project.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.parth.certification.project.dto.ProductDTO;
import com.parth.certification.project.model.Order;
import com.parth.certification.project.model.Product;
import com.parth.certification.project.service.ProductService;

@RequestMapping("/items")
@RestControllerAdvice
@RestController
public class ProductResource {

	@Autowired
	private ProductService itemService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public List<ProductDTO> all() {
		return itemService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{itemName}")
	public ProductDTO get(@PathVariable String itemName) {
		return itemService.get(itemName);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{itemName}")
	public ProductDTO put(@PathVariable String itemName,@RequestBody ProductDTO itemDTO) {
		itemDTO.setName(itemName);
		return itemService.save(itemDTO);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		itemService.delete(id);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO add(@RequestBody ProductDTO itemDTO) {
		return itemService.save(itemDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		// Add conditional logic to show differnt status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private static Map<String, List<Order>> productDB = new HashMap<String, List<Order>>();
	
	static {
		productDB = new HashMap<String, List<Order>>();
		
		List<Order> lst = new ArrayList<Order>();
		Order prod = new Order("Table", 12);
		lst.add(prod);
		prod = new Order("Chair",10);
		lst.add(prod);
		
		productDB.put("01", lst);
	}
	
	@RequestMapping(value = "/getProductDetailsForCustomer/{productNumber}", method= RequestMethod.GET)
	public List<Order> getProducts(@PathVariable String productNumber){	
		List<Order> ProductList = productDB.get(productNumber);
		if(ProductList == null) {
			ProductList = new ArrayList<Order>();
			Order prod = new Order("Not Found", 0);
			ProductList.add(prod);
		}
		return ProductList;
	}

	

}
