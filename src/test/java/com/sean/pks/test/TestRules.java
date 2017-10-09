/**
 *
 */
package com.sean.pks.test;

import java.math.BigDecimal;
import org.junit.Test;

import com.sean.pks.model.Item;
import com.sean.pks.model.ItemType;
import com.sean.pks.rule.model.InvalidRuleException;
import com.sean.pks.rule.model.PromotionalRule;
import com.sean.pks.rule.model.RuleCondition;
import com.sean.pks.rule.model.RuleOperation;

/**
 * @author sean
 *
 */
public class TestRules {
	
	private Item oh = new Item(ItemType.OH, BigDecimal.valueOf(300.00));
	private Item bc = new Item(ItemType.BC, BigDecimal.valueOf(110.00));
	private Item sk = new Item(ItemType.SK, BigDecimal.valueOf(30.00));
	
	@Test
	public void makeRuleCondition(){
		RuleCondition condition = new RuleCondition(oh,3);
		assert condition.getItem().equals(oh);
		assert condition.getNumber() == 3 ;
	}
	
	@Test
	public void makeRuleOperation(){
		RuleOperation operation = new RuleOperation(oh, true, 1, BigDecimal.ZERO);	
		assert operation.getItem().equals(oh);
		assert operation.getDiscountedPrice().intValue() == 0;
		assert operation.getNumber() == 1;
		assert operation.isInCondition() == true;		
	}
	
	@Test
	public void makeRule_correct_sameItem_inCondition() throws InvalidRuleException{
		PromotionalRule rule = new PromotionalRule("3 for 2 deal on opera house ticket", 5);

		rule.setCondition( new RuleCondition(oh,3));

		rule.getOperations().add(new RuleOperation(oh, true, 1, BigDecimal.ZERO));
		assert rule.ruleCheck();
		
	}
	
	@Test
	public void makeRule_correct_sameItem_notInCondition() throws InvalidRuleException{
		PromotionalRule rule = new PromotionalRule("bulk discount", 5);

		rule.setCondition( new RuleCondition(bc,4));

		rule.getOperations().add(new RuleOperation(bc, false, Integer.MAX_VALUE, BigDecimal.ZERO));
		assert rule.ruleCheck();
		
	}
	
	@Test
	public void makeRule_correct_differentItem(){
		PromotionalRule rule = new PromotionalRule("a free Sky Tower tour for with every Opera House tour", 5);
		try {
			rule.setCondition( new RuleCondition(oh,1));
		} catch (InvalidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rule.getOperations().add(new RuleOperation(sk, false, 1, BigDecimal.ZERO));
		assert rule.ruleCheck();
		
	}
	
	@Test
	public void makeRule_wrong_sameItem_inCondition(){
		PromotionalRule rule = new PromotionalRule("3 for 5 deal on opera house ticket", 5);

		try {
			rule.setCondition( new RuleCondition(oh,3));
		} catch (InvalidRuleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		rule.getOperations().add(new RuleOperation(oh, true, 5, BigDecimal.ZERO));
		assert !rule.ruleCheck();
		
	}
	

	@Test(expected = InvalidRuleException.class)
	public void makeRule_wrong_null_inCondition() throws InvalidRuleException{
		PromotionalRule rule = new PromotionalRule("3 for 5 deal on opera house ticket", 5);

			rule.setCondition( new RuleCondition(null,3));


		rule.getOperations().add(new RuleOperation(oh, true, 5, BigDecimal.ZERO));
		assert !rule.ruleCheck();
		
	}
}
