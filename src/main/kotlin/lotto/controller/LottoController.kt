package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.Purchase
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val price: String = getPrice()
        val amount: Int = getAmount(price)
        printLottoAmount(amount)

        val lottos: List<Lotto> = getLottos(amount)
        printLottos(lottos)

        val winningNumbers: List<LottoNumber> = getWinningNumbers()
        val bonusNumber: String = getBonusNumber()
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)

        printAllResult(lottos, winningLotto)
    }

    fun getPrice(): String {
        val price = inputView.inputPurchasePrice()
        return price
    }

    fun getAmount(price: String): Int {
        return Purchase(price).calculateAmountOfLottos()
    }

    fun printLottoAmount(amount: Int) {
        outputView.printLottoAmount(amount)
    }

    fun getLottos(amount: Int): List<Lotto> {
        val lottos: List<Lotto> = LottoFactory().generateLottos(amount)
        return lottos
    }

    fun printLottos(lottos: List<Lotto>) {
        outputView.printLottos(lottos)
    }

    fun getWinningNumbers(): List<LottoNumber> {
        val winningNumbers: List<LottoNumber> = inputView.inputWinningNumber().map { LottoNumber(it) }
        Lotto(winningNumbers)

        return winningNumbers
    }

    fun getBonusNumber(): String {
        val bonusNumber: String = inputView.inputBonusNumber()
        return bonusNumber
    }

    fun printLottoResult(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
    ) {
        outputView.printResult(LottoResult(lottos, winningLotto))
    }

    fun printProfitRate(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
    ) {
        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate()
        outputView.printProfit(profitRate)
    }

    fun printAllResult(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
    ) {
        printLottoResult(lottos, winningLotto)
        printProfitRate(lottos, winningLotto)
    }
}
