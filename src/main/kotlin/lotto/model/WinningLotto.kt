package lotto.model

class WinningLotto(
    private val winningLottoTicket: LottoTicket,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningLottoTicket.containsNumber(bonusNumber)) { ERROR_LOTTO_NUMBERS_NOT_CONTAIN_BONUS_NUMBER }
    }

    fun getRanks(lottoTickets: List<LottoTicket>): List<Rank> = lottoTickets.map { getRank(it) }

    private fun getRank(lottoTicket: LottoTicket): Rank {
        val countOfMatch: Int = getCountOfMatch(lottoTicket)
        val matchBonus: Boolean = getMatchBonus(lottoTicket)
        return Rank.valueOf(countOfMatch, matchBonus)
    }

    private fun getCountOfMatch(lottoTicket: LottoTicket): Int = winningLottoTicket.matchNumbersSize(lottoTicket)

    private fun getMatchBonus(lottoTicket: LottoTicket): Boolean = lottoTicket.containsNumber(bonusNumber)

    companion object {
        private const val ERROR_LOTTO_NUMBERS_NOT_CONTAIN_BONUS_NUMBER = "로또 당첨 번호는 보너스 번호를 포함하지 말아야합니다."

        fun create(
            vararg lottoNumbers: Int,
            bonusNumber: Int,
        ): WinningLotto {
            val winningNumbers = LottoTicket(LottoIssueType.WINNING, lottoNumbers.map { LottoNumber(it) })
            return WinningLotto(winningNumbers, LottoNumber(bonusNumber))
        }
    }
}
