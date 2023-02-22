package controller

import domain.ProfitCalculator
import domain.model.lotto.PurchasedLottos
import domain.LottoGenerator
import domain.model.PurchaseMoney
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import domain.validator.NumericValidator
import view.InputView
import view.ResultView

class LottoController(
    private val numericValidator: NumericValidator = NumericValidator(),
    private val profitCalculator: ProfitCalculator = ProfitCalculator()
) {

    fun run() {
        val purchaseMoney = repeatWithRunCatching { initializePurchaseMoney() }
        val purchasedLottos = purchaseLottos(purchaseMoney)
        purchasedLottos.printPurchasedLottos()
        checkPurchasedLottosResult(purchaseMoney, purchasedLottos)
    }

    private fun initializePurchaseMoney(): PurchaseMoney {
        val input = InputView.requestPurchaseMoney()
        val money = numericValidator.validate(input)
        return PurchaseMoney(money)
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

    private fun purchaseLottos(purchaseMoney: PurchaseMoney): PurchasedLottos {
        val numberOfTotalLottos = purchaseMoney.money / LOTTO_PRICE

        val numberOfManualLottos = repeatWithRunCatching { getNumberOfManualLottos(numberOfTotalLottos) }
        val manualLottos = repeatWithRunCatching { purchaseManualLottos(numberOfManualLottos) }

        val autoLottoGenerator = LottoGenerator(numberOfLottos = numberOfTotalLottos - numberOfManualLottos)
        val autoLottos = autoLottoGenerator.generateLottos()

        return PurchasedLottos(manualLottos + autoLottos)
    }

    private fun getNumberOfManualLottos(maximumQuantity: Int): Int {
        val numberOfManualLottos =
            numericValidator.validate(InputView.requestNumberOfManualLottos())

        require(numberOfManualLottos <= maximumQuantity) {
            NUMBER_OF_MANUAL_LOTTOS_ERROR
        }

        return numberOfManualLottos
    }

    private fun purchaseManualLottos(numberOfManualLottos: Int): List<Lotto> {
        ResultView.printManualLottoRequest()
        val manualLottoGenerator = LottoGenerator(numberOfManualLottos) {
            repeatWithRunCatching { readManualNumbers() }
        }
        return manualLottoGenerator.generateLottos()
    }

    private fun readManualNumbers(): List<Int> {
        val input = InputView.requestManualLottoNumbers()
        return input.map { numericValidator.validate(it) }
    }

    private fun PurchasedLottos.printPurchasedLottos() {
        ResultView.printPurchasedNumberOfLottos(this.lottos.size)
        ResultView.printPurchasedLottos(this)
    }

    private fun checkPurchasedLottosResult(purchaseMoney: PurchaseMoney, purchasedLottos: PurchasedLottos) {
        val winningNumbers = WinningNumbers(initializeCatchNumber(), initializeBonusNumber())
        val lottoResults = purchasedLottos.getTotalLottoResults(winningNumbers)
        val profit = profitCalculator.getProfit(purchaseMoney, lottoResults)

        ResultView.printLottoResults(lottoResults, profit)
    }

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val NUMBER_OF_MANUAL_LOTTOS_ERROR = "[ERROR] 구입한 로또 개수를 초과했습니다."
    }
}
