package lotto.model

data class WinningBundle(
    val winningLotto: Lotto,
    val bonusLottoNumber: LottoNumber,
) {
    init {
        require(bonusLottoNumber !in winningLotto.lottoNumbers) { BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE }
    }

    fun calculateResult(lottos: List<Lotto>): LottoResult {
        return LottoResult(
            lottos.map { lotto ->
                val countOfMatch = lotto.calculateCountOfMatch(winningLotto)
                val matchBonus = lotto.calculateMatchBonus(bonusLottoNumber)
                Rank.valueOf(countOfMatch, matchBonus)
            }.groupingBy { it }.eachCount(),
        )
    }

    companion object {
        private const val BONUS_NUMBER_DUPLICATED_ERROR_MESSAGE = "보너스 숫자는 당첨 번호와 중복되면 안됩니다."
    }
}
