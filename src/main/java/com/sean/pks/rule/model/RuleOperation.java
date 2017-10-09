/**
 *
 */
package com.sean.pks.rule.model;

import java.math.BigDecimal;

import com.sean.pks.model.Item;

/**
 * @author sean
 *
 */
public class RuleOperation {
	
	private Item item;
	
	//whether the discounted items should be within the items of RuleCondition or not
	// if == true, only discount the items in RuleCondition
	private boolean inCondition;
	
	// how many items will be discounted
	private int number;
	
	// the discounted price
	private BigDecimal discountedPrice;
	
	public RuleOperation(){}
	
	public RuleOperation(Item item, boolean inCondition, int number, BigDecimal discountedPrice){
		this.setItem(item);
		this.inCondition = inCondition;
		this.number = number;
		this.discountedPrice = discountedPrice;		
	}	
	
	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public boolean isInCondition() {
		return inCondition;
	}
	public void setInCondition(boolean inCondition) {
		this.inCondition = inCondition;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
