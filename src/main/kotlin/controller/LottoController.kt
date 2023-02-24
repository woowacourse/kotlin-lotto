package controller

import domain.ProfitCalculator
import domain.model.lotto.PurchasedLottos
import domain.LottoGenerator
import domain.model.PurchaseMoney
import domain.model.WinningNumbers
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import domain.model.lotto.LottoShop
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
        ResultView.printPurchasedLottos(purchasedLottos)
        checkPurchasedLottosResult(purchaseMoney, purchasedLottos)
    }

    private fun initializePurchaseMoney(): PurchaseMoney {
        val money = InputView.requestPurchaseMoney()

        return PurchaseMoney(money)
    }

    private fun purchaseLottos(purchaseMoney: PurchaseMoney): PurchasedLottos {
        val numberOfTotalLottos = purchaseMoney.money / LOTTO_PRICE

        val lottoShop = repeatWithRunCatching { LottoShop(purchaseMoney, InputView.requestNumberOfManualLottos()) }

        val numberOfManualLottos = lottoShop.numberOfManualLottos // TODO
        val manualLottos = repeatWithRunCatching { purchaseManualLottos(lottoShop) }

        val autoLottoGenerator = LottoGenerator(numberOfLottos = numberOfTotalLottos - numberOfManualLottos)
        val autoLottos = autoLottoGenerator.generateLottos()

        ResultView.printPurchasedNumberOfLottos(numberOfManualLottos, numberOfTotalLottos - numberOfManualLottos)

        return PurchasedLottos(manualLottos + autoLottos)
    }

    private fun purchaseManualLottos(lottoShop: LottoShop): List<Lotto> {
        ResultView.printManualLottoRequest()
        return List(lottoShop.numberOfManualLottos) {
            lottoShop.purchaseManualLotto(readManualNumbers())
        }
    }

    private fun readManualNumbers(): List<Int> {
        val input = InputView.requestManualLottoNumbers()
        return input.map { numericValidator.validate(it) }
    }

    private fun checkPurchasedLottosResult(purchaseMoney: PurchaseMoney, purchasedLottos: PurchasedLottos) {
        val winningNumbers = repeatWithRunCatching {
            WinningNumbers(
                initializeCatchNumber(),
                initializeBonusNumber()
            )
        }
        val lottoResults = purchasedLottos.getTotalLottoResults(winningNumbers)
        val profit = profitCalculator.getProfit(purchaseMoney, lottoResults)

        ResultView.printLottoResults(lottoResults, profit)
    }

    private fun initializeCatchNumber(): Set<LottoNumber> {
        val input = InputView.requestCatchNumbers()

        return input.map { number ->
            val verifiedNumber = numericValidator.validate(number)
            LottoNumber.from(verifiedNumber)
        }.toSet()
    }

    private fun initializeBonusNumber(): LottoNumber {
        val bonusNumber = InputView.requestBonusNumber()
        return LottoNumber.from(bonusNumber)
    }

    private fun <T> repeatWithRunCatching(action: () -> T): T {
        return runCatching(action).getOrElse { error ->
            println(error.message.toString())
            repeatWithRunCatching(action)
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
