/**
 *
 */
package com.sean.pks.shopping.service;

import java.math.BigDecimal;

import com.sean.pks.model.Item;

/**
 * @author sean
 *
 */
public interface ShoppingCart{
	public void add(Item t);
	public BigDecimal total();

}
