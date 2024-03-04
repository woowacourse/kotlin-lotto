package lotto.model

@JvmInline
value class Price(val num: Int) {
    init {
        require(num > 0) { "[ERROR] 금액이 양수가 아니거나 아무것도 입력되지 않았습니다" }
    }
}
