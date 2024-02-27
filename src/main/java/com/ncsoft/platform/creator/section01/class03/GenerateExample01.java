package com.ncsoft.platform.creator.section01.class03;

import com.ncsoft.platform.creator.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class GenerateExample01 {

    public static void main(String[] args) {

        // sink : synchronousSink
        Flux
            .generate( ()-> 0, (state, sink) -> {
                sink.next(state);
                if( state == 10 )
                    sink.complete();
                return ++state;
            })
            .subscribe(Logger::onNext);


        Mono.empty().delay(Duration.ofMillis(1000));

    }


}
