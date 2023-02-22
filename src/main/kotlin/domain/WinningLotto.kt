package domain

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(lotto.contains(bonusNumber).not()) { ERROR_DUPLICATE_BONUS_NUMBER.format(lotto.toList(), bonusNumber) }
    }

    constructor(numbers: IntArray, bonusNumber: Int) : this(Lotto(*numbers), LottoNumber.from(bonusNumber))

    fun match(lottos: LottoTickets): LottoResult =
        lottos.asSequence()
            .map { Rank.valueOf(it.countMatch(lotto), it.contains(bonusNumber)) }
            .groupingBy { it }
            .eachCount()
            .let { LottoResult(it) }

    override fun toString(): String = lotto.toString() + bonusNumber.toString()

    companion object {
        private const val ERROR_DUPLICATE_BONUS_NUMBER = "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : %s, %s"
    }
}
