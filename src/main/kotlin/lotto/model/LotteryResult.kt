package lotto.model

import lotto.util.WinningRank

data class LotteryResult(val winning: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!winning.compareBonusNumbers(bonusNumber)) { "보너스 숫자는 당첨 번호와 중복될 수 없습니다." }
    }

    fun getWinningResult(lottoTickets: List<Lotto>): List<WinningRank> {
        return lottoTickets.map {
            WinningRank.convert(
                it.matchWinningNumbers(winning),
                it.compareBonusNumbers(bonusNumber),
            )
        }
    }
}
