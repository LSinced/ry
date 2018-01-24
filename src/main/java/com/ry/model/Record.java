/**
 * 
 */
package com.ry.model;

/**
 * @author wanglei
 * 下午2:24:17
 */
public class Record {
	private String agent;
	private String tunnel;
	private String product;
	private float price;
	private int num;
	
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getTunnel() {
		return tunnel;
	}
	public void setTunnel(String tunnel) {
		this.tunnel = tunnel;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agent == null) ? 0 : agent.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((tunnel == null) ? 0 : tunnel.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (agent == null) {
			if (other.agent != null)
				return false;
		} else if (!agent.equals(other.agent))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (tunnel == null) {
			if (other.tunnel != null)
				return false;
		} else if (!tunnel.equals(other.tunnel))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Record [agent=" + agent + ", tunnel=" + tunnel + ", product="
				+ product + ", price=" + price + "]";
	}
	
	
}
