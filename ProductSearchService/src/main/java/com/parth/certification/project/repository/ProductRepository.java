package com.parth.certification.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parth.certification.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public Product findByName(String name);
}
