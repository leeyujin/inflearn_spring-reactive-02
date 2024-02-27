package com.ncsoft.platform.creator.section01.class04;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.Arrays;
import java.util.List;

public class CreateExample01 {
    public static int SIZE = 0 ;
    public static int COUNT = -1 ;

    private static List<Integer> dataSource = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

    public static void main(String[] args) {

        Flux.create( (FluxSink<Integer> emitter) -> {

            emitter.onRequest( n -> {
                TimeUtils.sleep(1000L);

                for(int i = 0 ; i < n ; i ++){
                    if (COUNT >= 9) {
                        emitter.complete();
                    }else{
                        COUNT++;
                        emitter.next(dataSource.get(COUNT));
                    }
                }
            });

            // 후처리
            emitter.onDispose( () -> Logger.info("# clean up") );
        }).subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(2);
            }

            @Override
            protected void hookOnNext(Integer value) {
                SIZE++;
                Logger.onNext(value);
                if (SIZE == 2){
                    request(2);
                    SIZE = 0;
                }
            }

            @Override
            protected void hookOnComplete(){
                Logger.onComplete("# onComplete");
            }
        });



    }
}
