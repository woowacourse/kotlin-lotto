package controller

import domain.ProfitCalculator
import domain.model.lotto.PurchasedLottos
import domain.LottoGenerator
import domain.model.PurchaseMoney
import domain.model.WinningNumbers
import domain.validator.NumericValidator
import view.InputView
import view.ResultView

class LottoController(
    private val numericValidator: NumericValidator = NumericValidator(),
    private val lottoGenerator: LottoGenerator = LottoGenerator(),
    private val profitCalculator: ProfitCalculator = ProfitCalculator()
) {

    private val purchaseMoney: PurchaseMoney by lazy {
        val money = numericValidator.validate(InputView.requestPurchaseMoney())

        PurchaseMoney(money)
    }

    private val winningNumbers: WinningNumbers by lazy {
        val catchNumbers = InputView.requestCatchNumbers().map { number ->
            numericValidator.validate(number)
        }.toSet()
        val bonusNumber = numericValidator.validate(InputView.requestBonusNumber())

        WinningNumbers(catchNumbers, bonusNumber)
    }

    fun run() {
        val purchasedLottos = purchaseLottos()

        checkPurchasedLottosResult(purchasedLottos)
    }

    private fun purchaseLottos(): PurchasedLottos {
        val purchasedLottos = PurchasedLottos(lottoGenerator.generateLottos(purchaseMoney))

        ResultView.printPurchasedNumberOfLottos(purchasedLottos.lottos.size)
        ResultView.printPurchasedLottos(purchasedLottos)

        return purchasedLottos
    }

    private fun checkPurchasedLottosResult(purchasedLottos: PurchasedLottos) {
        val lottoResults = purchasedLottos.getTotalLottoResults(winningNumbers)
        val profit = profitCalculator.getProfit(purchaseMoney, lottoResults)

        ResultView.printLottoResults(lottoResults, profit)
    }
}
