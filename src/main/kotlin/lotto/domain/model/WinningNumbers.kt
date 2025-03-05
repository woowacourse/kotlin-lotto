package lotto.domain.model

class WinningNumbers(private val winningLotto: Lotto, private val bonusNumber: LottoNumber) {
    init {
        require(winningLotto.hasLottoNumber(bonusNumber).not()) {
            DUPLICATE_WINNING_NUMBER_MESSAGE.format(bonusNumber.number, winningLotto.numbers)
        }
    }

    fun calculateLottoRanks(lottos: Lottos): LottoYieldCalculator {
        val purchaseLottoRanks = getPurchaseLottoRanks(lottos)
        return LottoYieldCalculator(
            LottoRank.entries.associateWith { rank ->
                getLottoRankCount(
                    rank,
                    purchaseLottoRanks,
                )
            },
        )
    }

    private fun getLottoRankCount(
        rank: LottoRank,
        purchaseLottoRanks: List<LottoRank>,
    ): Int {
        return purchaseLottoRanks.count { purchaseLottoRank -> purchaseLottoRank == rank }
    }

    private fun getPurchaseLottoRanks(purchaseLotto: Lottos): List<LottoRank> {
        val lottoRank = LottoRank
        return purchaseLotto.lottos.map { lotto ->
            lottoRank.calculate(
                lotto.getSameNumberCount(winningLotto),
                lotto.hasLottoNumber(bonusNumber),
            )
        }
    }

    companion object {
        private const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호 %s은(는) 당첨 번호 %s와 중복 될 수 없습니다."
    }
}
