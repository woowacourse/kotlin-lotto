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
    private val lottoFactory = LottoFactory()

    fun run() {
        val price = inputView.inputPurchasePrice()
        val manualAmount = inputView.inputAmountOfManualLotto()
        val totalAmount = Purchase(price).calculateAmountOfLottos()

        Validator.manualAmountValidator(manualAmount, totalAmount)

        outputView.printManualLottoHeader()

        val manualNumbers = getManualLottoNumbers(manualAmount)
        val manualLottos = lottoFactory.generateManualLottos(manualNumbers)
        val autoLottos = lottoFactory.generateLottos(totalAmount - manualAmount)

        val allLottos = manualLottos + autoLottos

        outputView.printLottoAmount(totalAmount, manualAmount)
        outputView.printLottos(allLottos)

        val winningNumbers = inputView.inputWinningNumber().map { LottoNumber.of(it) }
        val bonusNumber = inputView.inputBonusNumber()

        val winningLotto = WinningLotto(Lotto(winningNumbers), bonusNumber)
        val lottoResult = LottoResult(allLottos, winningLotto)

        outputView.printResult(lottoResult)
        outputView.printProfit(lottoResult.calculateProfitRate(price))
    }

    private fun getManualLottoNumbers(manualAmount: Int): List<List<Int>> {
        return (0 until manualAmount).map {
            inputView.inputManualLottoNumbers() // 사용자 입력만 받음!
        }
    }
}
