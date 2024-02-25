package model

interface LottoGameManager {
    fun generateLotteries(purchaseExpense: Int): List<Lotto>

    fun generateWinningLotto(winningNumbers: List<Int>): Lotto

    fun generateBonusLottoNumber(
        bonusNumber: Int,
        winningLotto: Lotto,
    ): LottoNumber
}

class RandomLottoGameManager(
    private val lottoGenerator: LottoGenerator,
) : LottoGameManager {
    override fun generateLotteries(purchaseExpense: Int) = lottoGenerator.generate(Money(purchaseExpense))

    override fun generateWinningLotto(winningNumbers: List<Int>) = Lotto(*(winningNumbers.toIntArray()))

    override fun generateBonusLottoNumber(
        bonusNumber: Int,
        winningLotto: Lotto,
    ) = LottoNumber(bonusNumber, winningLotto)
}
