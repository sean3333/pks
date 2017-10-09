/**
 *
 */
package com.sean.pks.model;

import java.math.BigDecimal;



/**
 * the item for sale
 * 
 * @author sean
 *
 */
public class Item {

	private ItemType id;

	private BigDecimal price;
	
	private BigDecimal discountedPrice;
	
	private boolean discount;
	
	public Item(){}
	
	public Item(ItemType id, BigDecimal price){
		this.id = id;
		this.price = price;
		this.discountedPrice = price;
		this.discount = false;
	}
	
	/*
	@Override
    public int hashCode()   
    {  
        return id.getIndex() ;  
    }  
	
	@Override
    public boolean equals(Object obj) 
    {  
          if(!(obj instanceof Item))  
              return false;  
          Item t=(Item)obj;   
          if(t.id == null){
        	  return false;
          }
          return this.id.equals(t.id);  
    }
    */
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ItemType getId() {
		return id;
	}
	public void setId(ItemType id) {
		this.id = id;
	}

	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}
		
}
