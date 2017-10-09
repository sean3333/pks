/**
 *
 */
package com.sean.pks.rule.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.sean.pks.model.Item;
import com.sean.pks.rule.model.PromotionalRule;
import com.sean.pks.rule.model.RuleOperation;

/**
 * @author sean
 *
 */
public class ApplyRule {
	
	/**
	 * 1, sort PromotionalRules by priority 
	 * 2, find not discounted items 
	 * 3, apply each PromotionalRule recursively
	 * 4, for each RuleCondition of PromotionalRule, get items that meet the conditions
	 * 5, apply on current item (inCondition == true ) OR apply other items
	 * 6, deal with non discounted items
	 * 7, return total 
	 * 
	 * @param rules
	 * @param items
	 * @return
	 */
	public BigDecimal getTotal(List<PromotionalRule> rules, List<Item> items){
		
		// sort the rules by priority
		rules.sort((r1, r2)->(Integer.compare(r2.getPriority(), r1.getPriority())));
		
		// apply each PromotionalRule on not discounted items 
		rules.stream().filter(r->(r.ruleCheck()))
				.forEach(r->{applySingleRule(r,items.stream().filter(i->(!i.isDiscount()))
						.collect(Collectors.toList()));});
		
		// after apply all rules, 
		// set discounted price as original price to non discounted items
		items.stream().filter(i->(i.getDiscountedPrice()==null || !i.isDiscount()))
			.forEach(i->{i.setDiscountedPrice(i.getDiscountedPrice());});
		
		// return total discounted price
		return items.stream().map(Item::getDiscountedPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	// recursion method, to apply promontionalRule on each item
	private void applySingleRule(PromotionalRule rule,  List<Item> items){

		
		synchronized(items){			
		
			// get items that meet the conditions
			List<Item> conditionItems = items.stream().filter(i->(!i.isDiscount()))
					.filter(i->rule.getCondition().getItem().getId().equals(i.getId()))
					.collect(Collectors.toList());
			
			if(conditionItems.size() < rule.getCondition().getNumber()){
				// not enough items, end recursion 
				return;
			}
			
			// get items for this time
			conditionItems = conditionItems.subList(0, rule.getCondition().getNumber());
	
			// make the items already discounted
			conditionItems.forEach(i->{i.setDiscount(true);
				i.setDiscountedPrice(i.getPrice());});
			
			for(RuleOperation operation : rule.getOperations()){
				if(operation.isInCondition()){
					
					// ruleOperation will apply on the subset of items in ruleCondition
					// eg. 3 for 2 deal on opera house ticket
					applySingleOperation(operation, 
							conditionItems.subList(0, operation.getNumber()));
		
				} else {
					
					// ruruleOperationle will apply on other items
					// eg. a free Sky Tower tour for with every Opera House tour sold
					List<Item> operationItems = items.stream().filter(i->(!i.isDiscount()))
							.filter(item->operation.getItem().getId().equals(item.getId()))
							.collect(Collectors.toList());
		
					applySingleOperation(operation,
							operationItems.subList(0, 
									(operationItems.size() < operation.getNumber())
									?operationItems.size():operation.getNumber()));
					
				}
			}
			
			// self-invoke on not discounted items
			applySingleRule(rule,items.stream().filter(i->(!i.isDiscount()))
					.collect(Collectors.toList()));
		}
	}
		
	// apply the discounted price on items
	private void applySingleOperation(RuleOperation operation, List<Item> items){
		items.forEach(i->{
			i.setDiscountedPrice(operation.getDiscountedPrice());
			i.setDiscount(true);});
	}
	
	
	
}
