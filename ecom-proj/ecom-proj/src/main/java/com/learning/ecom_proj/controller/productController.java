package com.learning.ecom_proj.controller;

import com.learning.ecom_proj.model.product;
import com.learning.ecom_proj.service.productService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class productController {

    private productService prod;

    public productController(productService prod) {
        this.prod = prod;
    }

    @GetMapping("/products")
    public List<product> getAllItems(){
        return prod.allItems();
    }

    @GetMapping("/product/{id}")
    public product getItemsById(@PathVariable int id){
        return prod.getItemsById(id);
    }

    @PostMapping("/product")
    public product addProduct(@RequestPart product produ, @RequestPart MultipartFile imageFile) throws IOException {
        return prod.addProduct(produ,imageFile);
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        product produ = prod.getItemsById(productId);

        byte[] imageFile = produ.getImageDate();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(produ.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart product produ, @RequestPart MultipartFile imageFile){
        product p1 = null;
        try {
            p1 = prod.updateProduct(id,produ, imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(p1 != null){
             return new ResponseEntity<>("updated", HttpStatus.OK);
         } else {
             return new ResponseEntity<>("failed to update",HttpStatus.BAD_REQUEST);
         }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        prod.deleteProduct(id);
        return ResponseEntity.ok("deleted success");
    }
}
