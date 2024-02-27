package lotto.model

class NumberOfManual(val counts: Int, totalCounts: Int) {
    init {
        require(counts >= MIN_NUMBER_OF_MANUAL) { "수동으로 발행할 로또 개수를 올바르게 입력해주세요." }
        require(counts <= totalCounts) { "발행할 수 있는 로또 개수를 초과했습니다." }
    }

    companion object {
        const val MIN_NUMBER_OF_MANUAL = 0
    }
}
