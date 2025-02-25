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
        val price: Int = getPrice()
        val manualAmount: Int = getManualLottoAmount()
        val amount: Int = getAmount(price)
        Validator.manualAmountValidator(manualAmount, amount)
        printLottoAmount(amount)

        val lottos: List<Lotto> = getLottos(amount)
        printLottos(lottos)

        val winningNumbers: List<LottoNumber> = getWinningNumbers()
        val bonusNumber: LottoNumber = getBonusNumber()
        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)

        printAllResult(lottos, winningLotto, price)
    }

    fun getPrice(): Int {
        val price = inputView.inputPurchasePrice()
        return price
    }

    fun getManualLottoAmount(): Int {
        val manualAmount = inputView.inputAmountOfManualLotto()
        return manualAmount
    }

    fun getAmount(price: Int): Int {
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
        val winningNumbers: List<LottoNumber> = inputView.inputWinningNumber().map { LottoNumber.of(it) }
        Lotto(winningNumbers)

        return winningNumbers
    }

    fun getBonusNumber(): LottoNumber {
        val bonusNumber: LottoNumber = inputView.inputBonusNumber()
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
        price: Int,
    ) {
        val profitRate: Double = LottoResult(lottos, winningLotto).calculateProfitRate(price)
        outputView.printProfit(profitRate)
    }

    fun printAllResult(
        lottos: List<Lotto>,
        winningLotto: WinningLotto,
        price: Int,
    ) {
        printLottoResult(lottos, winningLotto)
        printProfitRate(lottos, winningLotto, price)
    }
}
