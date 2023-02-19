package domain

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(lotto.contains(bonusNumber).not()) { ERROR_DUPLICATE_BONUS_NUMBER.format(lotto.toList(), bonusNumber) }
    }

    constructor(numbers: IntArray, bonusNumber: Int) : this(Lotto(*numbers), LottoNumber.from(bonusNumber))

    fun match(lottos: List<Lotto>): LottoResult =
        LottoResult(
            Rank.values().associateWith { rank ->
                lottos.count { rank == Rank.valueOf(getCountOfMatch(it), matchBonus(it)) }
            }.filterValues { it > 0 },
        )

    private fun getCountOfMatch(anyLotto: Lotto): Int = anyLotto.countMatch(lotto)

    private fun matchBonus(anyLotto: Lotto): Boolean = anyLotto.contains(bonusNumber)

    override fun toString(): String = lotto.toString() + bonusNumber.toString()
    companion object {
        private const val ERROR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : %s, %s"
    }
}
