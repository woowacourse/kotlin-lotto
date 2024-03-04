package lotto.model

@JvmInline
value class RankCount(val num: Int) {
    init {
        require(num >= 0) { "[ERROR] 0이상의 정수이어야 함" }
    }
}
