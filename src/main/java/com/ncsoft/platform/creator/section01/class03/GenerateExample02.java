package com.ncsoft.platform.creator.section01.class03;

import com.ncsoft.platform.creator.utils.Logger;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

public class GenerateExample02 {

    public static void main(String[] args) {

        Flux
            .generate(() -> Tuples.of(2,1), (state, sink) -> {
                sink.next(String.format("%d * %d = %d", state.getT1(), state.getT2(), state.getT1() * state.getT2()));
                if( state.getT2() == 9 )
                    sink.complete();
                return Tuples.of( state.getT1(), state.getT2() + 1 );
            }, state -> Logger.info("# 구구단 {}단 종료!", state.getT1()))
                .subscribe(Logger::onNext);


    }


}
