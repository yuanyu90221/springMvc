package com.dynamicProxy;

import com.dynamicProxy.bo.HelloSpeaker;
import com.dynamicProxy.bo.inf.IHello;
import com.dynamicProxy.invoke.LogHandler;

public class ProxyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LogHandler logHandler = new LogHandler();
		 
        IHello helloProxy = (IHello)logHandler.bind(new HelloSpeaker());
         
        helloProxy.hello("Name");
	}

}
