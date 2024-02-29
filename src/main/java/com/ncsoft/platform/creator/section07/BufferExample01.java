package com.ncsoft.platform.creator.section07;

import com.ncsoft.platform.creator.utils.Logger;
import reactor.core.publisher.Flux;

public class BufferExample01 {
    public static void main(String[] args) {
        Flux
            .range(1,95)
            .buffer(10)
            .subscribe(buffer -> Logger.onNext(buffer));
    }
}
