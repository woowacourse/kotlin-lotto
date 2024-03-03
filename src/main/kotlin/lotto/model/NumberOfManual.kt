package lotto.model

class NumberOfManual(val counts: Int) {
    init {
        require(counts >= MIN_NUMBER_OF_MANUAL) { "수동으로 발행할 로또 개수를 올바르게 입력해주세요." }
    }

    companion object {
        const val MIN_NUMBER_OF_MANUAL = 0
    }
}
