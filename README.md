# pks

## rule engine demo

this is a rule engine demo for calculating promotions in shopping cart.

1. one rule includes one condition with multiple operations: 
1 rule = if (1 condition), then (n operations).

2. support priority for different rules

3. do not support apply multiple rules to one item yet, but can be easily support with few modification.

***

## Examples:

here are items to sale:
| Id | Name | Price |
|:----|:-----------------------|:------|
| OH | Opera house tour | $300.00 |
| BC | Sydney Bridge Climb | $110.00 |
| SK | Sydney Sky Tower | $30.00 |


* single rule 1:
 - rule:
	if (buy 3 Opera House tour tickets);
	then (you will pay the price of 2 only getting another one completely free of charge).
 - Items added to the cart:
OH, OH, OH, BC - Total expected: $710.00

* single rule 2:
 * rule:
	if (buy 1 Opera House tour ticket);
	then (get a free Sky Tower tour ticket).
 * Items added to the cart:
OH, SK - Total expected: $300.00

* single rule 3:
 * rule:
	if (buys 4 Sydney Bridge Climb tickets);
	then (the price will drop to $20 from the 5th ticket).
 * Items added to the cart:
BC, BC, BC, BC, BC, OH - Total expected: $760

* multiple operations rule:
 * rule:
	if (buy 3 Opera House tour tickets);
	then (pay the price of 2 only getting another one completely free of charge,
          And get 3 free Sky Tower tour ticket).
 * Items added to the cart:
OH, OH, OH, SK, SK, SK - Total expected:  $600

* different priority rules:
 * rules:
  * highest priority rule:
	if (buy 3 Opera House tour tickets);
	then (pay the price of 2 only getting another one completely free of charge,
          And get 3 free Sky Tower tour ticket).
  * middle priority rule:
	if (buy 1 Opera House tour ticket);
	then (get a free Sky Tower tour ticket).
  * (same) middle priority rule:
	if (buys 4 Sydney Bridge Climb tickets);
	then (the price will drop to $20 from the 5th ticket).
 * Items added to the cart:
OH, OH, OH, OH, SK, SK, SK, CB, CB, CB, CB, CB, CB - Total expected: $1420
