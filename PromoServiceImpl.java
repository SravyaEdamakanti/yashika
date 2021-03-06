package com.capgemini.service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.capgemini.exception.ProductNotFoundException;
import com.capgemini.exception.PromoCodeNotFoundException;
import com.capgemini.model.Product;
import com.capgemini.model.PromoCode;
import com.capgemini.repository.ProductRepository;
import com.capgemini.repository.PromoRepository;

@Component(value="promoServices")
public class PromoServiceImpl implements PromoDisplayService{
	@Autowired
PromoRepository promo;
	@Autowired
	ProductRepository product;
	@Override
	public List<PromoCode> promoDisplay() throws PromoCodeNotFoundException{
		//List list=new ArrayList();
		// LocalDate date=LocalDate.now();
		List<PromoCode> list=promo.newPromos();
	//	System.out.println(list);
		if(list.size()!=0)
		return list;
		 else {
			throw new PromoCodeNotFoundException("No PromoCodes are available");
		 }
	}

	@Override
	public List<Product> newProductsDisplay(String categoryName) throws ProductNotFoundException{
			LocalDate date=LocalDate.now();
			Date date1=Date.valueOf(date);
			LocalDate date2=date.minusDays(3);
			Date date3=Date.valueOf(date2);
          List<Product> productList=product.newProducts(date3,date1,categoryName);
          if(productList.size()!=0)
			return productList;
          else {
        	  System.out.println("not found");
			throw new ProductNotFoundException("No new Arrivals");
		}
	}

}
