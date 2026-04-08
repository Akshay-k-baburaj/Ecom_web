package com.learning.ecom_proj.repository;

import com.learning.ecom_proj.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends JpaRepository<product,Integer> {
}
