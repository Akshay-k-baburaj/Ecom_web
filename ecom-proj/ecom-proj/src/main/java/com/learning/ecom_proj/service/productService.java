package com.learning.ecom_proj.service;

import com.learning.ecom_proj.model.product;
import com.learning.ecom_proj.repository.ProductRespository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
public class productService {

    private ProductRespository repo;

    public productService(ProductRespository repo) {
        this.repo = repo;
    }

    public List<product> allItems() {
        return repo.findAll();
    }

    public product getItemsById(int id){
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }

    public product addProduct(product prod, MultipartFile multi) throws IOException {
        prod.setImageName(multi.getOriginalFilename());
        prod.setImageType(multi.getContentType());
        prod.setImageDate(multi.getBytes());
        return repo.save(prod);
    }

    public product updateProduct(int id, product produ, MultipartFile imageFile) throws IOException {
        produ.setImageName(imageFile.getOriginalFilename());
        produ.setImageType(imageFile.getContentType());
        produ.setImageDate(imageFile.getBytes());
        return repo.save(produ);
    }

    public void deleteProduct(int id){
        product p = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
        repo.delete(p);
    }
}
