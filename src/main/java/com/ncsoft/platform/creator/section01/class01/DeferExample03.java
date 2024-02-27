package com.ncsoft.platform.creator.section01.class01;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DeferExample03 {

    public static void main(String[] args) {
        Logger.info("# Start");

        Mono
            .just("Hello")
            .log()
            .delayElement(Duration.ofSeconds(2))
            // defer는 구독 시점에만 데이터를 emit하기 때문에 메소드 호출 안됨
            .switchIfEmpty(Mono.defer(() -> sayDefault()))
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);

    }

    private static Mono<String> sayDefault(){
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }


}
