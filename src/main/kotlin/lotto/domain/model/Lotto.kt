package lotto.domain.model

class Lotto(val numbers: Set<LottoNumber>) {
    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LOTTO_NUMBER_SIZE) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE }
    }

    fun calculateMatchLottoNumberCount(winningLotto: Lotto): Int {
        return numbers.count { number -> winningLotto.numbers.contains(number) }
    }

    fun isMatchBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.contains(bonusNumber)
    }

    private companion object {
        const val LOTTO_NUMBER_SIZE = 6
        const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "로또 번호는 6개여야 하며, 중복될 수 없습니다."
    }
}
