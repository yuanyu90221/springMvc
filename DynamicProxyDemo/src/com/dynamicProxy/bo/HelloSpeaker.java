package com.dynamicProxy.bo;

import com.dynamicProxy.bo.inf.IHello;

public class HelloSpeaker implements IHello {

	@Override
	public void hello(String name) {
		System.out.println("Hello " + name);
	}

}
