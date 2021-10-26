package com.parth.certification.project.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parth.certification.project.dto.ProductDTO;
import com.parth.certification.project.model.Product;
import com.parth.certification.project.repository.ProductRepository;

@Transactional
@Service
public class ProductService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository itemRepository;

	public List<ProductDTO> all() {
		return itemRepository.findAll().stream().map(item -> modelMapper.map(item, ProductDTO.class))
				.collect(Collectors.toList());
	}

	public ProductDTO save(ProductDTO itemDTO) {
		Product customer = itemRepository.save(modelMapper.map(itemDTO, Product.class));
		return modelMapper.map(customer, ProductDTO.class);
	}

	public ProductDTO get(String itemName) {
		return modelMapper.map(itemRepository.findByName(itemName), ProductDTO.class);
	}

	public void delete(long itemId) {
		itemRepository.deleteById(itemId);
	}
}
