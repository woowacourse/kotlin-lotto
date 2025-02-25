package lotto.model

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE) { LOTTO_BOUND_MESSAGE }
    }

    companion object {
        const val MAXIMUM_LOTTO_RANGE: Int = 45
        const val MINIMUM_LOTTO_RANGE: Int = 1
        const val LOTTO_BOUND_MESSAGE = "로또 번호는 1에서 45 범위 내에서 있어야 합니다."
    }
}
