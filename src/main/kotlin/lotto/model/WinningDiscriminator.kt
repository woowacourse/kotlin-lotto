package lotto.model

class WinningDiscriminator private constructor(
    private val winningLotto: Lotto,
    private val bonusNumber: LottoNumber,
) {
    constructor(winningNumbers: List<Int>, bonusNumber: Int) : this(
        Lotto.from(winningNumbers),
        LottoNumber.from(bonusNumber),
    )

    init {
        validateWinningNumberAndBonusNumberDuplicate()
    }

    fun getResult(lottoWallet: LottoWallet): Map<Rank, Int> {
        val countResult = Rank.entries.associateWith { 0 }.toMutableMap()

        lottoWallet.lottos.forEach { lotto ->
            val rank = discriminate(lotto)
            countResult[rank] = countResult.getValue(rank) + 1
        }

        return countResult
    }

    private fun discriminate(lotto: Lotto): Rank =
        Rank.from(
            countOfMatch = lotto.countMatchNumbers(winningLotto),
            matchBonus = matchBonus(lotto),
        )

    private fun matchBonus(lotto: Lotto): Boolean = lotto.contains(bonusNumber)

    private fun validateWinningNumberAndBonusNumberDuplicate() {
        require(!matchBonus(winningLotto)) {
            "[ERROR] 우승 번호와 보너스 번호는 중복될 수 없습니다."
        }
    }
}
