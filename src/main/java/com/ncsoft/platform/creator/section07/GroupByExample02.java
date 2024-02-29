package com.ncsoft.platform.creator.section07;

import com.ncsoft.platform.creator.common.SampleData;
import com.ncsoft.platform.creator.utils.Logger;
import reactor.core.publisher.Flux;

public class GroupByExample02 {
    public static void main(String[] args) {
        Flux
            .fromIterable(SampleData.books)
            .groupBy( book -> book.getAuthorName(),
                      book-> book.getBookName() + "(" + book.getAuthorName() + ")"
                    )
            .flatMap(groupedFlux -> groupedFlux.collectList())
            .subscribe(booksByAutor -> Logger.onNext(booksByAutor));


    }
}
