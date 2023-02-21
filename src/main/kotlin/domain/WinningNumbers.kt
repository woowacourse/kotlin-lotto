package domain

class WinningNumbers(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.lottoNumbers.contains(bonusNumber)) { "[Error] 보너스 번호가 당첨 번호와 중복되면 안됩니다." }
    }

    fun compareLottoBundle(lottoBundle: LottoBundle): WinStatistics {
        return WinStatistics(lottoBundle.lottos.map { compareLotto(it) })
    }

    private fun compareLotto(otherLotto: Lotto): ComparingResult {
        val matchedCount: Int = otherLotto.lottoNumbers.count { lotto.lottoNumbers.contains(it) }
        val isBonusMatched: Boolean = otherLotto.lottoNumbers.contains(bonusNumber)

        return ComparingResult(matchedCount, isBonusMatched)
    }
}
