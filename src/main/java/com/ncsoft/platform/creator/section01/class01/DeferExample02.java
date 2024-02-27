package com.ncsoft.platform.creator.section01.class01;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class DeferExample02 {

    public static void main(String[] args) {
        Logger.info("# Start");

        Mono
            .just("Hello")
            .log()
            .delayElement(Duration.ofSeconds(2))
            // 출력 안되길 예상하지만 Say Hi가 출력됨 -> 불필요한 메소드 호출이 일어남
            .switchIfEmpty(sayDefault())
            .subscribe(Logger::onNext);

        TimeUtils.sleep(2500);

    }

    private static Mono<String> sayDefault(){
        Logger.info("# Say Hi");
        return Mono.just("Hi");
    }


}
