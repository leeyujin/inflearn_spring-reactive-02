package com.ncsoft.platform.creator.section01.class01;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Mono;

public class DeferExample04 {

    public static void main(String[] args) {
        Logger.info("# Start");

        Mono<Object> mono = Mono
                .empty()
                .switchIfEmpty(Mono.defer(() -> sayDefault()));

        TimeUtils.sleep(2500);
        // defer는 구독이 일어날때 emit하기때문에, 2.5초 대기 후 emit됨
        mono.subscribe(Logger::onNext);

    }

    private static Mono<String> sayDefault(){
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }


}
