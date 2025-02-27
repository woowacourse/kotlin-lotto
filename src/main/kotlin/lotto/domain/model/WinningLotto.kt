package lotto.domain.model

import lotto.domain.service.LottoResult

class WinningLotto(
    private val winningNumbers: Set<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    constructor(
        winningNumbers: List<Int>,
        bonusNumber: LottoNumber,
    ) : this(winningNumbers.map { LottoNumber(it) }.toSet(), bonusNumber)

    init {
        require(bonusNumber !in winningNumbers) { ERROR_BONUS_DUPLICATE }
    }

    fun getResult(
        manualLottoTickets: List<LottoTicket>,
        autoLottoTickets: List<LottoTicket>,
    ): LottoResult {
        val results = manualLottoTickets.map { getRank(it) }.toMutableList()
        results.addAll(autoLottoTickets.map { getRank(it) })
        return LottoResult(results)
    }

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
