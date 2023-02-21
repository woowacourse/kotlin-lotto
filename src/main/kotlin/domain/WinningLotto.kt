package domain

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(bonusNumber !in lotto) { ERROR_DUPLICATE_BONUS_NUMBER.format(lotto, bonusNumber) }
    }

    constructor(numbers: List<Int>, bonusNumber: Int) : this(Lotto.create(numbers), LottoNumber.valueOf(bonusNumber))

    fun getCountOfMatch(anyLotto: Lotto): Int = anyLotto.count { it in lotto }

    fun matchBonus(anyLotto: Lotto): Boolean = bonusNumber in anyLotto

    companion object {
        private const val ERROR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : %s, %s"
    }
}
