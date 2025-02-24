package lotto.model

import lotto.contants.LottoRuleConstants.LOTTO_PICK_COUNT

class WinningLotto(
    private val winningNumbers: Set<LottoNumber>,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(winningNumbers.size == LOTTO_PICK_COUNT.value) { ERROR_NUMBERS_COUNT }
        require(!winningNumbers.contains(bonusNumber)) { ERROR_LOTTO_NUMBERS_NOT_CONTAIN_BONUS_NUMBER }
    }

    fun getResult(lottoTickets: List<LottoTicket>): LottoResult {
        val result = lottoTickets.map { getRank(it) }
        return LottoResult(result)
    }

    fun getRank(lottoTicket: LottoTicket): Rank {
        val countOfMatch: Int = getCountOfMatch(lottoTicket)
        val matchBonus: Boolean = getMatchBonus(lottoTicket)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int = winningNumbers.intersect(lottoTicket.getNumbers()).size

    private fun getMatchBonus(lottoTicket: LottoTicket): Boolean = lottoTicket.getNumbers().contains(bonusNumber)

    companion object {
        private const val ERROR_NUMBERS_COUNT = "로또 번호의 개수는 6개입니다."
        private const val ERROR_LOTTO_NUMBERS_NOT_CONTAIN_BONUS_NUMBER = "로또 당첨 번호는 보너스 번호를 포함하지 말아야합니다."

        fun create(vararg lottoNumbers: LottoNumber, bonusNumber: LottoNumber): WinningLotto {
            return WinningLotto(lottoNumbers.toSet(), bonusNumber)
        }
    }
}
