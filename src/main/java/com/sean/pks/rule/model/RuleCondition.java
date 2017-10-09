/**
 *
 */
package com.sean.pks.rule.model;

import com.sean.pks.model.Item;

/**
 * 
 * @author sean
 *
 */
public class RuleCondition {
	private Item item;
	private int number;
	
	public RuleCondition(){}
	
	public RuleCondition(Item item, int number){
		this.setItem(item);
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
		
}
