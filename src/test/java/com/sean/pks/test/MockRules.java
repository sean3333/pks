/**
 *
 */
package com.sean.pks.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sean.pks.model.Item;
import com.sean.pks.model.ItemType;
import com.sean.pks.rule.model.InvalidRuleException;
import com.sean.pks.rule.model.PromotionalRule;
import com.sean.pks.rule.model.RuleCondition;
import com.sean.pks.rule.model.RuleOperation;

/**
 * create the 3 rules in the assignment
 * 
 * @author sean
 *
 */
public class MockRules {

	private static List<PromotionalRule> rules = new ArrayList<>();
	
	private static Item oh = new Item(ItemType.OH, BigDecimal.valueOf(300.00));
	private static Item bc = new Item(ItemType.BC, BigDecimal.valueOf(110.00));
	private static Item sk = new Item(ItemType.SK, BigDecimal.valueOf(30.00));
	
	static{
		// 3 for 2 deal on opera house ticket
		PromotionalRule rule = new PromotionalRule("3 for 2 deal on opera house ticket", 5);
		try {
			rule.setCondition( new RuleCondition(oh,3));
		} catch (InvalidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rule.getOperations().add(new RuleOperation(oh, true, 1, BigDecimal.ZERO));
		rules.add(rule);
		
		// a free Sky Tower tour for with every Opera House tour
		rule = new PromotionalRule("a free Sky Tower tour for with every Opera House tour", 5);
		try {
			rule.setCondition( new RuleCondition(oh,1));
		} catch (InvalidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rule.getOperations().add(new RuleOperation(sk, false, 1, BigDecimal.ZERO));
		rules.add(rule);
		
		// bulk discount Sydney Bridge Climb
		rule = new PromotionalRule("bulk discount", 5);
		try {
			rule.setCondition( new RuleCondition(bc,4));
		} catch (InvalidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rule.getOperations().add(new RuleOperation(bc, false, Integer.MAX_VALUE, BigDecimal.valueOf(20.00)));
		rules.add(rule);
		
	}
	

	public static List<PromotionalRule> getRules() {
		return rules;
	}

}
