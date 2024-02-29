package com.ncsoft.platform.creator.section03;

import com.ncsoft.platform.creator.utils.Logger;
import com.ncsoft.platform.creator.utils.TimeUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class ThenExample02 {
    public static void main(String[] args) {

        restartApplication()
                .then()
                .subscribe(
                        Logger::onNext,
                        Logger::onError,
                        ()-> Logger.onComplete("Send an email to Administrator")
                );

        TimeUtils.sleep(3000L);

    }


    private static Mono<Void> restartApplication(){
        return Mono
                .just("Application server was restarted successfully")
                .delayElement(Duration.ofSeconds(2))
                .doOnNext(Logger::doOnNext)
                .flatMap(notUse -> Mono.empty());


    }

}
