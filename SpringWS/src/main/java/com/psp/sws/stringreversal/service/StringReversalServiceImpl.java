package com.psp.sws.stringreversal.service;

public class StringReversalServiceImpl implements StringReveralService {

	@Override
	public String reverse(String anyString) {
		
		if (anyString == null || anyString.trim().length() == 0) {
			return "";
		}
		return new StringBuilder(anyString).reverse().toString();
	}
}
