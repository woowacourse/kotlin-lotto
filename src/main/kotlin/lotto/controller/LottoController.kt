package lotto.controller

import lotto.domain.Lotto
import lotto.domain.LottoAmount
import lotto.domain.LottoFactory
import lotto.domain.LottoNumber
import lotto.domain.LottoResult
import lotto.domain.ManualLottoNumbers
import lotto.domain.Purchase
import lotto.domain.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val lottoFactory = LottoFactory()

    fun run() {
        val (price, manualAmount, totalAmount) = getLottoAmount()
        val allLottos = generateLottos(manualAmount, totalAmount)

        outputView.printLottoAmount(totalAmount, manualAmount)
        outputView.printLottos(allLottos)

        val winningLotto = getWinningLotto()
        calculateAndPrintResult(price, allLottos, winningLotto)
    }

    private fun getLottoAmount(): Triple<Int, LottoAmount, LottoAmount> {
        val price = inputView.inputPurchasePrice()
        val manualAmount = LottoAmount(inputView.inputAmountOfManualLotto())
        val totalAmount = Purchase(price).calculateAmountOfLottos()

        Validator.manualAmountValidator(manualAmount, totalAmount)

        return Triple(price, manualAmount, totalAmount)
    }

    private fun generateLottos(
        manualAmount: LottoAmount,
        totalAmount: LottoAmount,
    ): List<Lotto> {
        outputView.printManualLottoHeader()

        val manualNumbers = getManualLottoNumbers(manualAmount)
        val manualLottos = lottoFactory.generateManualLottos(manualNumbers)
        val autoLottos = lottoFactory.generateLottos(totalAmount.toInt() - manualAmount.toInt())

        return manualLottos + autoLottos
    }

    private fun getManualLottoNumbers(manualAmount: LottoAmount): ManualLottoNumbers {
        val numbersList =
            (0 until manualAmount.toInt()).map {
                inputView.inputManualLottoNumbers()
            }
        return ManualLottoNumbers(numbersList)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = inputView.inputWinningNumber().map { LottoNumber.of(it) }
        val bonusNumber = inputView.inputBonusNumber()
        return WinningLotto(Lotto(winningNumbers), bonusNumber)
    }

    private fun calculateAndPrintResult(
        price: Int,
        allLottos: List<Lotto>,
        winningLotto: WinningLotto,
    ) {
        val lottoResult = LottoResult(allLottos, winningLotto)
        outputView.printResult(lottoResult)
        outputView.printProfit(lottoResult.calculateProfitRate(price))
    }
}
