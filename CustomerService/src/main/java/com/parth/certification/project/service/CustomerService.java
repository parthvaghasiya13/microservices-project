package com.parth.certification.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.parth.certification.project.dto.CustomerDTO;
import com.parth.certification.project.model.Customer;
import com.parth.certification.project.repository.CustomerRepository;

@Transactional
@Service
@Slf4j
public class CustomerService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public List<CustomerDTO> all() {
        log.info("Entered getAll CustomerDTO");
		return customerRepository.findAll().stream().map(c -> modelMapper.map(c, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	public CustomerDTO save(CustomerDTO customerDTO) {
		Customer customer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
		CustomerDTO result = modelMapper.map(customer, CustomerDTO.class);
		kafkaTemplate.send("CustomerCreated", "CustomerCreated", new Gson().toJson(result));
		log.info("Entered save CustomerDTO");
		return result;
	}

	public CustomerDTO get(long customerId) {
		log.info("Entered get CustomerDTO");
		Optional<Customer> customerResult = customerRepository.findById(customerId);
		if(!customerResult.isPresent()){
			return null;
		}
		return modelMapper.map(customerResult.get(), CustomerDTO.class);
	}

	public void delete(long customerId) {
		log.info("Entered delete CustomerDTO");
		customerRepository.deleteById(customerId);
	}
}
