package lotto.model

@JvmInline
value class Count(val num: Int) {
    init {
        require(num >= 0) { "[ERROR] 0이상이어야 함" }
    }
}
