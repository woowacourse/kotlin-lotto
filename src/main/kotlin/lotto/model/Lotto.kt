package lotto.model

class Lotto(
    private val numbers: Set<LottoNumber>,
) {
    init {
        require(numbers.size == LOTTO_NUMBERS_SIZE) { LOTTO_COUNT_MESSAGE }
    }

    constructor(numbers: List<LottoNumber>) : this(numbers.toSet()) {
        require(numbers.size == this.numbers.size) { LOTTO_COUNT_MESSAGE }
    }

    fun contains(it: LottoNumber): Boolean {
        return numbers.contains(it)
    }

    fun match(lotto: Lotto): Int {
        return numbers.count { lotto.contains(it) }
    }

    companion object {
        const val LOTTO_NUMBERS_SIZE: Int = 6
        const val LOTTO_COUNT_MESSAGE = "로또 번호는 6개여야 합니다."
        const val LOTTO_BOUND_MESSAGE = "로또 번호는 1에서 45 범위 내에서 있어야 합니다."
        const val LOTTO_DISTINCT_MESSAGE = "로또 번호가 중복될 수 없습니다."
    }
}
