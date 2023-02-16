package lotto.entity

class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(numbers.size == LOTTO_COUNT) { String.format(ERROR_MESSAGE_WIN_NUMBER_IS_SIX, numbers.size) }
        require(numbers.intersect(numbers).size == LOTTO_COUNT) { ERROR_MESSAGE_DUPLICATED_NUMBER }
    }

    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    fun matchLottoNumberCount(lotto: Lotto): Int = numbers.intersect(lotto.numbers).size

    fun isMatchBonus(bonus: LottoNumber): Boolean = numbers.contains(bonus)

    companion object {
        private const val ERROR_MESSAGE_WIN_NUMBER_IS_SIX = "로또 번호는 6개여야 합니다. 입력된 로또 번호 개수는 %d 입니다."
        private const val ERROR_MESSAGE_DUPLICATED_NUMBER = "로또 번호는 서로 중복될 수 없습니다"
        const val MINIMUM_LOTTO_NUMBER = 1
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val LOTTO_MINIMUM_RANGE = 0
        const val LOTTO_COUNT = 6
    }
}
