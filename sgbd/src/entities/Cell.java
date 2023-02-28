package entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import enums.OperationTypeEnums;
import sgbd.query.Operator;
import util.FindRoots;

public abstract class Cell {
	
	private String style;
	private String name;
	private Object cell;
	protected List<Cell> parents;
	private Cell child;
	private int x;
	private int y;
	private int length;
	private int width;
	
	public Cell(String name, String style, Object cell, int x,int y,int length,int width) {
		
		this.parents = new ArrayList<>();
		this.style = style;
		this.name = name;
		this.cell = cell;
		this.child= null;
		this.x = x;
		this.y = y;
		this.length = length;
		this.width = width;
		
	}
	
	public Cell getChild() {
		return child;
	}

	public void setChild(Cell child) {
		this.child = child;
	}

	public List<Cell> getParents() {
		return parents;
	}

	public String getStyle() {
		return style;
	}

	public String getName() {
		return name;
	}

	public Object getCell() {
		return cell;
	}
	
	public int getX() {
		return x;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setLength(int newLength) {
		this.length = newLength;
	}
	
	public void setWidth(int newWidth) {
		this.width = newWidth;
	}
	
	public void addParent(Cell parent) {
		parents.add(parent);
	}
	
	public String getSourceTableName(String columnName) {
		
		for(Cell cell : FindRoots.getRoots(this)) {
			
			if(cell.getColumnsName().contains(columnName)) 
				return cell.getName();
		
		}
		
		return null;
		
	}

	public abstract Operator getData();
	public abstract List<String> getColumnsName();
	public abstract List<List<String>> getContent();
	
	public Boolean checkRules(OperationTypeEnums type) {
		
		if(type == OperationTypeEnums.UNARIA) {
			if(this.getParents().size() != 1) {
				JOptionPane.showMessageDialog(null, "Operacao unaria", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}else if(type == OperationTypeEnums.BINARIA){
			if(this.getParents().size() > 2) {
				JOptionPane.showMessageDialog(null, "Operacao binaria", "Erro", JOptionPane.ERROR_MESSAGE);
				return false;
			}else if(this.getParents().size() < 2) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
