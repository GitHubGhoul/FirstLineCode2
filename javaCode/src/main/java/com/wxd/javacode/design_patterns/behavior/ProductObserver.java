package com.wxd.javacode.design_patterns.behavior;

public interface ProductObserver {

	void onPublished(Product product);

	void onPriceChanged(Product product);
}
