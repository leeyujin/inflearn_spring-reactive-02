package com.ncsoft.platform.creator.section01.class04;


import com.ncsoft.platform.creator.common.SampleData;

public class CryptoCurrencyPriceEmitter {
    private CryptoCurrencyPriceListener listener;

    public void setListener(CryptoCurrencyPriceListener listener) {
        this.listener = listener;
    }

    public void flowInto() {
        listener.onPrice(SampleData.btcPrices);
    }
    public void complete() {
        listener.onComplete();
    }
}