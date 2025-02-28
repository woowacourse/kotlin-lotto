package lotto.domain.model

class WinningLotto(
    private val winningNumbers: Set<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(bonusNumber !in winningNumbers) { ERROR_BONUS_DUPLICATE }
    }

    constructor(
        winningNumbers: List<Int>,
        bonusNumber: LottoNumber,
    ) : this(winningNumbers.map { LottoNumber(it) }.toSet(), bonusNumber)

    fun getRank(lottoTicket: LottoTicket): Rank {
        val countOfMatch = getCountOfMatch(lottoTicket)
        val hasBonusNumber = hasBonusNumber(lottoTicket)
        return Rank.valueOf(countOfMatch, hasBonusNumber)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int = lottoTicket.countMatchingNumbers(winningNumbers)

    private fun hasBonusNumber(lottoTicket: LottoTicket): Boolean = lottoTicket.hasNumber(bonusNumber)

    companion object {
        private const val ERROR_BONUS_DUPLICATE = "보너스 번호는 당첨 번호와 중복되면 안 됩니다."
    }
}
