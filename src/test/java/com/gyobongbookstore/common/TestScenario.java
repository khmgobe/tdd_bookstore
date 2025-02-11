package com.gyobongbookstore.common;

import com.gyobongbookstore.common.api.RegisterBookApi;

public class TestScenario {

    /**
     * 도서 등록 API
     * @return 도서 등록 API 데이터
     */
    public static RegisterBookApi registerBook() {
        return new RegisterBookApi();
    }
}
