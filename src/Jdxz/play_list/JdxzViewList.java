package Jdxz.play_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class JdxzViewList {
	private HashMap<String, String> map = new HashMap<String, String>();//name ,absolutely address  
	private ArrayList<String> list = new ArrayList<String>();//name

	public HashMap<String, String> getMap() {
		return map;
	}

	public void setMap(String name, String url) {
		map.put(name, url);
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(String name) {
		list.add(name);
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}

}