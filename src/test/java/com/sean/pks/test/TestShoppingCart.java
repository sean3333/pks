/**
 *
 */
package com.sean.pks.test;

import java.math.BigDecimal;
import org.junit.Test;

import com.sean.pks.model.Item;
import com.sean.pks.model.ItemType;
import com.sean.pks.shopping.service.TourShoppingCart;

/**
 * @author sean
 *
 */
public class TestShoppingCart {
	
	/*
	private Item oh = new Item(ItemType.OH, BigDecimal.valueOf(300.00));
	private Item bc = new Item(ItemType.BC, BigDecimal.valueOf(110.00));
	private Item sk = new Item(ItemType.SK, BigDecimal.valueOf(30.00));
	*/
	private TourShoppingCart cart;
	
	@Test
	public void createCart(){
		cart = new TourShoppingCart(MockRules.getRules());
		assert !cart.getRules().isEmpty();
	}
	
	@Test
	public void getTotal_3oh(){
		// OH, OH, OH, BC - Total expected: $710.00
		cart = new TourShoppingCart(MockRules.getRules());
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		assert cart.total().equals(BigDecimal.valueOf(710.00));
		System.out.println(cart.total());
	}
	
	@Test
	public void getTotal_oh_sk(){
		// OH, SK - Total expected: $300.00
		cart = new TourShoppingCart(MockRules.getRules());
		
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		
		cart.add(new Item(ItemType.SK, BigDecimal.valueOf(30.00)) );
		
		assert cart.total().equals(BigDecimal.valueOf(300.00));
		System.out.println(cart.total());
	}
	
	@Test
	public void getTotal_4bc(){
		// BC, BC, BC, BC, BC, OH - Total expected: $760
		// this total should be $760, not $750
		cart = new TourShoppingCart(MockRules.getRules());
		
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		
		assert cart.total().equals(BigDecimal.valueOf(760.00));
		System.out.println(cart.total());
	}
	
	@Test
	public void getTotal_complexrule(){
		/* 4*OH, 3*SK, 6*CB - Total expected: $1420
		 * = (3*OH,CB) + (OH+SK) +(5*CB) + (2*SK)
		 * = 600 + 300 + 460 + 60
		 * = $1420
		 */
		cart = new TourShoppingCart(MockFlexableRules.getRules());
		
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		
		cart.add(new Item(ItemType.SK, BigDecimal.valueOf(30.00)) );
		cart.add(new Item(ItemType.SK, BigDecimal.valueOf(30.00)) );
		cart.add(new Item(ItemType.SK, BigDecimal.valueOf(30.00)) );
		
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		
		cart.add(new Item(ItemType.OH, BigDecimal.valueOf(300.00)) );
		
		cart.add(new Item(ItemType.BC, BigDecimal.valueOf(110.00)) );
		
		assert cart.total().equals(BigDecimal.valueOf(1420.00));
		System.out.println(cart.total());
	}

}
