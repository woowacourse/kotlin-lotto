package domain

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(lotto.numbers.contains(bonusNumber).not()) { ERROR_DUPLICATE_BONUS_NUMBER.format(lotto, bonusNumber) }
    }

    constructor(numbers: IntArray, bonusNumber: Int) : this(Lotto(*numbers), LottoNumber(bonusNumber))

    fun match(lottos: List<Lotto>): LottoResult =
        LottoResult(
            Rank.values().associateWith { rank ->
                lottos.count { rank == Rank.valueOf(getCountOfMatch(it), matchBonus(it)) }
            },
        )

    private fun getCountOfMatch(anyLotto: Lotto): Int = anyLotto.numbers.count { lotto.numbers.contains(it) }

    private fun matchBonus(anyLotto: Lotto): Boolean = anyLotto.numbers.contains(bonusNumber)

    override fun toString(): String = lotto.toString() + bonusNumber.toString()
    companion object {
        private const val ERROR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : %s, %s"
    }
}
