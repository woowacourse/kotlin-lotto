package controller

import domain.ProfitCalculator
import domain.model.lotto.PurchasedLottos
import domain.LottoGenerator
import domain.model.PurchaseMoney
import domain.model.WinningNumbers
import domain.model.lotto.LottoNumber
import domain.validator.NumericValidator
import view.InputView
import view.ResultView

class LottoController(
    private val numericValidator: NumericValidator = NumericValidator(),
    private val profitCalculator: ProfitCalculator = ProfitCalculator()
) {

    private val purchaseMoney: PurchaseMoney by lazy { initializePurchaseMoney() }

    private val winningNumbers: WinningNumbers by lazy {
        WinningNumbers(
            initializeCatchNumber(),
            initializeBonusNumber()
        )
    }

    fun run() {
        val purchasedLottos = purchaseLottos()
        purchasedLottos.printPurchasedLottos()
        checkPurchasedLottosResult(purchasedLottos)
    }

    private fun initializePurchaseMoney(): PurchaseMoney {
        return runCatching {
            val input = InputView.requestPurchaseMoney()
            val money = numericValidator.validate(input)
            PurchaseMoney(money)
        }.getOrElse { error ->
            println(error.message.toString())
            initializePurchaseMoney()
        }
    }

    private fun initializeCatchNumber(): Set<LottoNumber> {
        val input = InputView.requestCatchNumbers()

        return input.map { number ->
            val verifiedNumber = numericValidator.validate(number)
            LottoNumber.from(verifiedNumber)
        }.toSet()
    }

    private fun initializeBonusNumber(): LottoNumber {
        val input = InputView.requestBonusNumber()
        return LottoNumber.from(numericValidator.validate(input))
    }

    private fun purchaseLottos(): PurchasedLottos {
        val lottoGenerator = LottoGenerator(numberOfLottos = purchaseMoney.money / LOTTO_PRICE)

        return PurchasedLottos(lottoGenerator.generateLottos())
    }

    private fun PurchasedLottos.printPurchasedLottos() {
        ResultView.printPurchasedNumberOfLottos(this.lottos.size)
        ResultView.printPurchasedLottos(this)
    }

    private fun checkPurchasedLottosResult(purchasedLottos: PurchasedLottos) {
        val lottoResults = purchasedLottos.getTotalLottoResults(winningNumbers)
        val profit = profitCalculator.getProfit(purchaseMoney, lottoResults)

        ResultView.printLottoResults(lottoResults, profit)
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
