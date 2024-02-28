package lotto.model

data class WinningBundle(
    val winningLotto: Lotto,
    val bonusLottoNumber: LottoNumber,
) {
    init {
        require(bonusLottoNumber !in winningLotto.lottoNumbers.numbers) { BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE }
    }

    fun calculateResult(lottos: List<Lotto>): Result {
        val result = Result()
        updateResultWithLottos(result, lottos)
        return result
    }

    private fun updateResultWithLottos(
        result: Result,
        lottos: List<Lotto>,
    ) {
        lottos.forEach { lotto ->
            updateResultWithLotto(lotto, result)
        }
    }

    private fun updateResultWithLotto(
        lotto: Lotto,
        result: Result,
    ) {
        val countOfMatch = calculateCountOfMatch(lotto)
        val matchBonus = calculateMatchBonus(lotto)
        val rank = Rank.valueOf(countOfMatch, matchBonus)
        result.incrementRankCount(rank)
    }

    private fun calculateCountOfMatch(lotto: Lotto): Int {
        return lotto.lottoNumbers.numbers.intersect(winningLotto.lottoNumbers.numbers.toSet()).size
    }

    private fun calculateMatchBonus(lotto: Lotto): Boolean {
        return bonusLottoNumber in lotto.lottoNumbers.numbers
    }

    companion object {
        private const val BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE = "보너스 숫자는 당첨 번호와 중복되면 안됩니다."
    }
}
