/**
 *
 */
package com.sean.pks.shopping.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.sean.pks.model.Item;
import com.sean.pks.rule.model.PromotionalRule;
import com.sean.pks.rule.service.ApplyRule;

/**
 * @author sean
 *
 */
public class TourShoppingCart implements ShoppingCart{	

	ApplyRule applyRule = new ApplyRule();
	
	private List<Item> items = new ArrayList<Item>();
	
	private List<PromotionalRule> rules = new ArrayList<PromotionalRule>();
	
	public TourShoppingCart(){}
	
	public TourShoppingCart(List<PromotionalRule> rules){
		this.rules.addAll(rules);
	}
	
	/* (non-Javadoc)
	 * @see com.sean.pks.shopping.service.ShoppingCart#add(java.lang.Object)
	 */
	@Override
	public void add(Item t) {
		// TODO Auto-generated method stub
		
		this.items.add(t);
	}

	/* (non-Javadoc)
	 * @see com.sean.pks.shopping.service.ShoppingCart#total()
	 */
	@Override
	public BigDecimal total() {
		// TODO Auto-generated method stub
		return applyRule.getTotal(rules, items);
	}
	
	public List<PromotionalRule> getRules() {
		return rules;
	}

	public void setRules(List<PromotionalRule> rules) {
		this.rules = rules;
	}

}
