package com.ncsoft.platform.creator.section06;

import com.ncsoft.platform.creator.common.Book;
import com.ncsoft.platform.creator.common.SampleData;
import com.ncsoft.platform.creator.utils.Logger;
import reactor.core.publisher.Flux;

public class OnErrorReturnExample01 {
    public static void main(String[] args) {
        getBooks()
                .map(book -> book.getPenName().toUpperCase())
                .map(book -> book+"A")
                .onErrorReturn("No pen name")
                .subscribe(Logger::info, Logger::onError);


    }

    public static Flux<Book> getBooks(){
        return Flux.fromIterable(SampleData.books);
    }
}
