package com.pblgllgs.productmicroservice.repository;

import com.pblgllgs.productmicroservice.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductEntity,String > {
}
