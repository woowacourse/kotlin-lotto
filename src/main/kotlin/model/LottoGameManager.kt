package model

class LottoGameManager(
    private val lottoGenerator: LottoGenerator,
) {
    fun generateLotteries(purchaseExpense: Int) = lottoGenerator.generate(Money(purchaseExpense))

    fun generateWinningLotto(winningNumbers: List<Int>) = Lotto(*(winningNumbers.toIntArray()))

    fun generateBonusLottoNumber(
        bonusNumber: Int,
        winningLotto: Lotto,
    ) = LottoNumber(bonusNumber, winningLotto)
}
