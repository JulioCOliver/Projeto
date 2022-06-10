package br.com.foursys.fourcamp.fourstore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Sale;
import br.com.foursys.fourcamp.fourstore.service.ProductService;

@RestController
public class ProductController {
	ProductService service = new ProductService();

	@GetMapping("/teste")

	public String teste() {
		return "teste";
	}
}