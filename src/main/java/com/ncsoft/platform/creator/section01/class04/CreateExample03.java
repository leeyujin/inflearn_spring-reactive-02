package com.ncsoft.platform.creator.section01.class04;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class CreateExample03 {

    public static int start = 1;
    public static int end = 4;

    public static void main(String[] args) {

        Flux
            .create((FluxSink<Integer> emitter) -> {

                emitter.onRequest( n -> {
                    Logger.info("# requested" + n);
                    TimeUtils.sleep(500L);
                    for( int i = start ; i <= end ; i ++){
                        emitter.next(i);
                    }
                    start += 4;
                    end += 4;

                });

                emitter.onDispose(() -> {
                    Logger.info("# clean up");
                });
            } , FluxSink.OverflowStrategy.DROP)
            .subscribeOn( Schedulers.boundedElastic() )
            // 2개의 요청까지 수용 -> 그 이후 OverflowStrategy.DROP 에 의해 버려짐
            .publishOn( Schedulers.parallel(), 2 )
            .subscribe( data -> {
                Logger.onNext(data);
            });

        TimeUtils.sleep(3000L);


    }

}
