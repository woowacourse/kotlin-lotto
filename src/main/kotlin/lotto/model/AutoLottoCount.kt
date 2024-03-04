package lotto.model

@JvmInline
value class AutoLottoCount(val num: Int) {
    init {
        require(num > 0) { "[ERROR] 자동로또개수는 0이상의 정수이어야 함" }
    }
}
