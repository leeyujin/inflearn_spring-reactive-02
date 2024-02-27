package com.ncsoft.platform.creator.section01.class04;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class CreateExample02 {

    public static void main(String[] args) {

        CryptoCurrencyPriceEmitter priceEmitter = new CryptoCurrencyPriceEmitter();

        Flux.
            create( (FluxSink<Integer> sink) -> {

                priceEmitter.setListener(new CryptoCurrencyPriceListener() {
                    @Override
                    public void onPrice(List<Integer> priceList) {
                        priceList.stream().forEach( price -> {
                            sink.next(price);
                        });
                    }
                    @Override
                    public void onComplete() {
                        sink.complete();
                    }
                });
            })
            .publishOn(Schedulers.parallel())
            .subscribe(
                    data -> Logger.onNext(data),
                    error -> {},
                    () -> Logger.info("# onComplete"));

        TimeUtils.sleep(3000L);

        priceEmitter.flowInto();

        TimeUtils.sleep(2000L);

        priceEmitter.flowInto();

        TimeUtils.sleep(2000L);

        priceEmitter.complete();

        TimeUtils.sleep(100L);


    }

}
