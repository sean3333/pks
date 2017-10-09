/**
 *
 */
package com.sean.pks.rule.model;

import java.util.ArrayList;
import java.util.List;

/**
 * the complete promotion rule, continues: 1 condition, n operations
 * 
 * @author sean
 *
 */
public class PromotionalRule {
	
	// for persistence
	// not used yet
	private long id;
	
	private String ruleName;
	
	/* 
	 * the higher priority will be applied first
	 * 0 lowest, the larger number means higher priority
	 * no restriction on largest number yet
	 */
	private int priority;
	
	/*
	 * == true: only apply on not discounted items
	 * not used yet, 
	 * should add a boolean field in Item for using this
	 */
	private boolean exclusive;
	
	private RuleCondition condition;
	
	// support multiple RuleOperations for one RuleCondition
	private List<RuleOperation> operations = new ArrayList<RuleOperation>();
	
	public PromotionalRule(){}
	
	public PromotionalRule(String ruleName, int priority){
		this.ruleName = ruleName;
		this.priority = priority;
	}
	
	/**
	 * check rule is valid
	 * 
	 * @return
	 */
	public boolean ruleCheck() {
		if(this.condition == null || operations.isEmpty()){
			return false;
		} else {
			for(RuleOperation o: operations){		
				if(o.isInCondition() && o.getNumber() > this.condition.getNumber())
					// when operation will change items in condition, 
					// the number should not be larger than the one in condition
					return false;
			}
		}
		return true;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public RuleCondition getCondition() {
		return condition;
	}
	
	public void setCondition(RuleCondition condition) throws InvalidRuleException {
		if(condition.getItem()==null || condition.getNumber()==0){
			throw new InvalidRuleException();
		}
		this.condition = condition;
	}
	
	public List<RuleOperation> getOperations() {
		return operations;
	}
	public void setOperations(List<RuleOperation> operations) {
		this.operations = operations;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}
	
	

}
