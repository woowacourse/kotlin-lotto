package lotto.model

data class LottoWinningBundle(
    val winningLotto: Lotto,
    val bonusLottoNumber: LottoNumber,
) {
    init {
        require(bonusLottoNumber !in winningLotto.lottoNumbers) { BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE }
    }

    fun calculateResult(lottos: List<Lotto>): LottoResult {
        val result: MutableMap<LottoRank, Int> = LottoRank.entries.reversed().associateWith { 0 }.toMutableMap()
        lottos.forEach { lotto ->
            val countOfMatch = calculateCountOfMatch(lotto)
            val matchBonus = calculateMatchBonus(lotto)
            val lottoRank = LottoRank.valueOf(countOfMatch, matchBonus)
            result[lottoRank] = result.getOrDefault(lottoRank, 0) + 1
        }
        return LottoResult(result)
    }

    private fun calculateCountOfMatch(lotto: Lotto): Int {
        return lotto.lottoNumbers.intersect(winningLotto.lottoNumbers.toSet()).size
    }

    private fun calculateMatchBonus(lotto: Lotto): Boolean {
        return bonusLottoNumber in lotto.lottoNumbers
    }

    companion object {
        private const val BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE = "보너스 숫자는 당첨 번호와 중복되면 안됩니다."
    }
}
