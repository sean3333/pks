/**
 *
 */
package com.sean.pks.model;

/**
 * item type, current only 3 types
 * 
 * @author sean
 *
 */
public enum ItemType {
	OH("Opera_house_tour", 1),
	BC("Sydney_Bridge_Climb", 2),
	SK("Sydney_Sky_Tower" ,3)

	;

    private String name ;
    private int index ;
     
    private ItemType( String name , int index ){
        this.name = name ;
        this.index = index ;
    }
     
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
     
}
