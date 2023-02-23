package lotto.domain

class UserLotto(private val lottoNumbers: List<Lotto>) {
    fun calculateTotalRank(winningNumbers: WinningNumbers): List<Rank> {
        val ranks = mutableListOf<Rank>()
        lottoNumbers.forEach { lotto ->
            ranks.add(
                Rank.valueOf(
                    lotto.countMatchingNumbers(winningNumbers.winningLotto),
                    lotto.checkMatchingBonusNumber(winningNumbers.bonusNumber)
                )
            )
        }
        return ranks
    }

    companion object {
        private const val MANUAL_LOTTO_INPUT_ERROR = "수동 로또 구입 개수는 숫자여야 합니다."
        private const val MANUAL_LOTTO_COUNT_ERROR = "수동 로또의 개수는 0이상 구매 로또 개수 이하여야 합니다."

        fun validateInputManualLottoCount(lottoCount: Int, manualLottoCount: String) {
            require(manualLottoCount.toIntOrNull() != null) { MANUAL_LOTTO_INPUT_ERROR }
            require(manualLottoCount.toInt() in (0..lottoCount)) { MANUAL_LOTTO_COUNT_ERROR }
        }
    }
}
