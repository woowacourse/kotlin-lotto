package lotto.domain

class UserLotto {
    private val count: Int
    private val lottoNumbers: List<Lotto>

    constructor(count: Int) {
        this.count = count
        this.lottoNumbers = listOf()
    }
    constructor(count: Int, lottoNumbers: List<Lotto>) {
        this.count = count
        this.lottoNumbers = lottoNumbers
    }

    fun calculateTotalRank(winningNumbers: WinningNumbers): List<Rank> {
        val ranks = mutableListOf<Rank>()
        lottoNumbers.forEach { lotto ->
            Rank.valueOf(
                lotto.countMatchingNumbers(winningNumbers.winningLotto),
                lotto.checkMatchingBonusNumber(winningNumbers.bonusNumber)
            )?.let {
                ranks.add(it)
            }
        }
        return ranks
    }

    fun checkInputManualLottoCount(manualLottoCount: Int?) {
        require(manualLottoCount != null) { MANUAL_LOTTO_INPUT_ERROR }
        require(manualLottoCount in (0..count)) { MANUAL_LOTTO_COUNT_ERROR }
    }

    companion object {
        private const val MANUAL_LOTTO_INPUT_ERROR = "수동 로또 구입 개수는 숫자여야 합니다."
        private const val MANUAL_LOTTO_COUNT_ERROR = "수동 로또의 개수는 0이상 구매 로또 개수 이하여야 합니다."
    }
}
