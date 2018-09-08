import java.io.*;
import java.util.*;


public class Product implements java.io.Serializable{
	private int id;
	private String name;
	private String uom;
	private int brandid;
	private int vendorid;
	private int storeid;
	private int inventoryCount;
	private String imgpath;
	private int price;

	
	public Product(int id,String name,String uom,int brandid, int vendorid, int storeid, int inventoryCount,String imgpath,int price){
		this.id=id;
		this.name=name;
		this.uom=uom;
		this.brandid=brandid;
		this.vendorid=vendorid;
		this.storeid=storeid;
		this.inventoryCount = inventoryCount;
		this.imgpath = imgpath;
		this.price=price;		
	}
	
	
	public Product() {
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	
	public int getBrandId() {
		return brandid;
	}

	public void setBrandId(int brandid) {
		this.brandid = brandid;
	}
	
    public int getVendorid() {
		return vendorid;
	}

	public void setVendorid(int vendorid) {
		this.vendorid = vendorid;
	}
	
	public int getStoreid() {
		return storeid;
	}

	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	
	public int getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(int inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
	
    public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
