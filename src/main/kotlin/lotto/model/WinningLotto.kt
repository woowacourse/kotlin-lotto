package lotto.model

class WinningLotto(private val winningNumbers: Lotto, private val bonusNumber: LottoNumber) {
    init {
        require(!winningNumbers.getMatchBonus(bonusNumber)) {
            ERROR_BONUS_DUPLICATE
        }
    }

    fun getWinningResult(lottos: List<Lotto>): Map<WinningRank, Int> {
        val winningResult =
            lottos.groupBy { lotto ->
                val matchCount = lotto.getMatchCount(winningNumbers)
                val matchBonus = lotto.getMatchBonus(bonusNumber)
                WinningRank.findByMatchCount(matchCount, matchBonus)
            }.mapValues { (_, value) -> value.size }
        return winningResult
    }

    companion object {
        const val ERROR_BONUS_DUPLICATE = "보너스 번호는 당첨 번호와 중복될 수 없습니다."
    }
}
