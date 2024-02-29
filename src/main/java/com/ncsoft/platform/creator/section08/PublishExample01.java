package com.ncsoft.platform.creator.section08;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class PublishExample01 {
    public static void main(String[] args) {

        ConnectableFlux<Integer> flux =
                Flux
                    .range(1, 5)
                    .delayElements(Duration.ofMillis(300L))
                    .publish();

        // 의미없는 지연시간 - connect 호출 전이라서
        TimeUtils.sleep(500L);
        flux.subscribe(data -> Logger.onNext("subscriber1: ", data));

        // 의미없는 지연시간 - connect 호출 전이라서
        TimeUtils.sleep(200L);
        flux.subscribe(data -> Logger.onNext("subscriber2: ", data));

        // emit 되는 시점
        flux.connect();


        TimeUtils.sleep(1000L);
        flux.subscribe(data -> Logger.onNext("subscriber3: ", data));


        TimeUtils.sleep(2000L);
    }
}
