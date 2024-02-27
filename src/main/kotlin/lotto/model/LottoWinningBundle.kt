package lotto.model

data class LottoWinningBundle(
    val winningLotto: Lotto,
    val bonusLottoNumber: LottoNumber,
) {
    init {
        require(bonusLottoNumber !in winningLotto.lottoNumbers.numbers) { BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE }
    }

    fun calculateResult(purchasedLottos: List<Lotto>): Result {
        val result: MutableMap<Rank, Int> = Rank.entries.reversed().associateWith { 0 }.toMutableMap()
        purchasedLottos.forEach { lotto ->
            val countOfMatch = calculateCountOfMatch(lotto)
            val matchBonus = calculateMatchBonus(lotto)
            val rank = Rank.valueOf(countOfMatch, matchBonus)
            result[rank] = result.getOrDefault(rank, 0) + 1
        }
        return Result(result)
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
