package com.java.core.companies.LinkedIn;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl implements NestedInteger {
	private Integer val;
	private List<NestedInteger> nestedList;


	public NestedIntegerImpl(Integer val) {
		this.val = val;
	}

	public NestedIntegerImpl(List<NestedInteger> nestedList) {
		this.nestedList = nestedList;
	}

	@Override
	public boolean isInteger() {
		return val != null;
	}

	@Override
	public Integer getInteger() {
		return val;
	}

	@Override
	public List<NestedInteger> getList() {
		return nestedList;
	}

	public static void main(String[] args) {
		NestedInteger nested1 = new NestedIntegerImpl(1);
		List<NestedInteger> nestedList4 = new ArrayList<>();
		NestedInteger nested4 = new NestedIntegerImpl(4);
		nestedList4.add(nested4);
		NestedInteger nested4List = new NestedIntegerImpl(nestedList4);
		NestedInteger nested6 = new NestedIntegerImpl(6);
		List<NestedInteger> nestedList6 = new ArrayList<>();
		nestedList6.add(nested6);
		NestedInteger nested6List = new NestedIntegerImpl(nestedList6);
		nestedList4.add(nested6List);
		List<NestedInteger> nestedList = new ArrayList<>();
		nestedList.add(nested1);
		nestedList.add(nested4List);
		System.out.println(nested6List.depthSum(nestedList));
	}

	// {1,{4,{6}}} -> input list:NestInteger:1, NestedInteger:{NestedInteger:4,...}
	//{3} depth = 1
	// {{3}} depth = 2
	// {1, 1}
	// {3,{4},5}
	public int depthSum (List<NestedInteger> input)
	{
		//Implementation here
		if (input == null || input.size() == 0 ) return 0;
		return calSum(input, 1);
	}

	//int sum = 0;
	//int depth = 1
// val = 3, d = 1
// val = {4}, d = 2
// val = 5,
	private int calSum(List<NestedInteger> input, int depth) {
		int sum = 0;
		for (NestedInteger nested : input) {
			if (nested.isInteger()) {
				sum += nested.getInteger() * depth;
			} else {
				sum += calSum(nested.getList(), depth + 1);
//				System.out.println("Callback returned sum >>> " + sum1);
//				sum += sum1;
			}
		}
		return sum;
	}

}
