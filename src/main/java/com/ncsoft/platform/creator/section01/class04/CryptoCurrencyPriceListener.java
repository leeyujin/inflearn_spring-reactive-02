package com.ncsoft.platform.creator.section01.class04;

import java.util.List;

public interface CryptoCurrencyPriceListener {
    void onPrice(List<Integer> priceList);
    void onComplete();
}