/**
 * 
 */
package com.ry.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ry.model.Record;

/**
 * @author wanglei
 * 下午6:46:27
 */
public class Test {

	/**
	 * add by wanglei at 2017-9-13 下午6:46:27
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Record r1=new Record();
		Record r2=new Record();
		r1.setAgent("三体通信");
		r1.setPrice(17.5f);
		r1.setProduct("500M");
		r1.setTunnel("安徽电信");
		r2.setAgent("三体通信");
		r2.setPrice(17.5f);
		r2.setProduct("500M");
		r2.setTunnel("安徽电信");
		//System.out.println(r1.equals(r2));
		String r1String=JSON.toJSONString(r1);
		System.out.println(r1String);
		Record r3=JSON.parseObject(r1String, Record.class);
		System.out.println(r3.getAgent());
		Map<String, Object> map=JSON.parseObject(r1String);
		System.out.println(map.get("tunnel"));
		List<Record> list=new ArrayList<Record>();
		list.add(r1);
		list.add(r2);
		String listString=JSON.toJSONString(list);
		System.out.println(listString);
		List<Record> list2=JSON.parseArray(listString, Record.class);
		for (Record record : list2) {
			System.out.println(record.getPrice());
		}
	}

}
