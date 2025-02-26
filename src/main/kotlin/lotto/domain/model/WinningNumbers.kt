package lotto.domain.model

class WinningNumbers(private val winningLotto: Lotto, private val bonusNumber: LottoNumber) {
    constructor(numbers: List<Int>, bonusNumber: Int) : this(Lotto(numbers), LottoNumber(bonusNumber))

    init {
        require(winningLotto.hasLottoNumber(bonusNumber)) { DUPLICATE_WINNING_NUMBER_MESSAGE.format(bonusNumber) }
    }

    fun calculateLottoRanks(lottos: Lottos): LottoRanks {
        val purchaseLottoRanks = getPurchaseLottoRanks(lottos)
        return LottoRanks(LottoRank.entries.associateWith { rank -> getLottoRankCount(rank, purchaseLottoRanks) })
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
                lotto.getMatchCount(winningLotto),
                lotto.hasLottoNumber(bonusNumber),
            )
        }
    }

    private companion object {
        const val DUPLICATE_WINNING_NUMBER_MESSAGE = "보너스 번호 %s은(는) 당첨 번호와 중복 될 수 없습니다."
    }
}
