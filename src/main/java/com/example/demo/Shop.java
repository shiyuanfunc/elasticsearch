package com.example.demo;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Document(indexName = "shop")
public class Shop implements Serializable {
	
	private String id ;
	private String shopName;
	private Double shopPrice;
	private String shopDesc;
	private Double shopDiscount;
	private String shopImgPath;
	private byte[] shopData;
	private Date createTime ;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;

	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(Double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public Double getShopDiscount() {
		return shopDiscount;
	}
	public void setShopDiscount(Double shopDiscount) {
		this.shopDiscount = shopDiscount;
	}
	public String getShopImgPath() {
		return shopImgPath;
	}
	public void setShopImgPath(String shopImgPath) {
		this.shopImgPath = shopImgPath;
	}
	public byte[] getShopData() {
		return shopData;
	}
	public void setShopData(byte[] bs) {
		this.shopData = bs;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    @Override
    public String toString() {
        return "Shop{" +
                "id='" + id + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopPrice=" + shopPrice +
                ", shopDesc='" + shopDesc + '\'' +
                ", shopDiscount=" + shopDiscount +
                ", shopImgPath='" + shopImgPath + '\'' +
                ", shopData=" + Arrays.toString(shopData) +
                ", createTime=" + createTime +
                '}';
    }
}
