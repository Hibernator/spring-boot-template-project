package com.example.demo.core.adapter;

import com.example.demo.application.response.PriceCheckResult;
import com.example.demo.core.domain.Product;
import com.example.demo.infrastructure.exception.ProductNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ProductAdapter {
  List<Product> loadAllProducts(Pageable paging);

  void insertNewProduct(Product product);

  Product loadProductDetails(Long id) throws ProductNotFoundException;

  CompletableFuture<PriceCheckResult> checkAsyncPrice(Product product) throws InterruptedException;

  PriceCheckResult checkPrice(Product product) throws InterruptedException;
}
