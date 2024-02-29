package com.ncsoft.platform.creator.section03;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FlatMapExample01 {
    public static void main(String[] args) {

        Flux
            .just("Good","Bad")
            .flatMap(feeling ->
                Flux
                    .just("Morning", "Afternoon", "Evening")
                    // emit 데이터 순서 보장 X (Good, Bad 순서 보장 X)
                    .publishOn(Schedulers.parallel())
                    .map( time -> feeling + " " + time))
                .subscribe(Logger::onNext);

        TimeUtils.sleep(200L);


      }
}
