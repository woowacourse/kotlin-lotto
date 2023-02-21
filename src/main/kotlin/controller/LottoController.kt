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

    private val winningNumbers: WinningNumbers by lazy {
        WinningNumbers(
            initializeCatchNumber(),
            initializeBonusNumber()
        )
    }

    fun run() {
        val purchaseMoney = initializePurchaseMoney()
        val purchasedLottos = purchaseLottos(purchaseMoney)
        purchasedLottos.printPurchasedLottos()
        checkPurchasedLottosResult(purchaseMoney, purchasedLottos)
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

    private fun purchaseLottos(purchaseMoney: PurchaseMoney): PurchasedLottos {
        val numberOfTotalLottos = purchaseMoney.money / LOTTO_PRICE
        val numberOfManualLottos = getNumberOfManualLottos(numberOfTotalLottos)
        val manualLottos = purchaseManualLottos(numberOfManualLottos)

        val autoLottoGenerator =
            LottoGenerator(numberOfLottos = purchaseMoney.money / LOTTO_PRICE - numberOfManualLottos)
        val autoLottos = autoLottoGenerator.generateLottos()

        return PurchasedLottos(manualLottos + autoLottos)
    }

    private fun purchaseManualLottos(numberOfManualLottos: Int): List<Lotto> {
        return runCatching {
            ResultView.printManualLottoRequest()
            val manualLottoGenerator = LottoGenerator(numberOfManualLottos) {
                readManualNumbers()
            }
            manualLottoGenerator.generateLottos()
        }.getOrElse { error ->
            println(error.message.toString())
            purchaseManualLottos(numberOfManualLottos)
        }
    }

    private fun readManualNumbers(): List<Int> {
        return runCatching {
            val input = InputView.requestManualLottoNumbers()
            input.map { numericValidator.validate(it) }
        }.getOrElse { error ->
            println(error.message.toString())
            readManualNumbers()
        }
    }

    private fun getNumberOfManualLottos(maximumQuantity: Int): Int {
        return runCatching {
            val numberOfManualLottos =
                numericValidator.validate(InputView.requestNumberOfManualLottos())

            require(numberOfManualLottos <= maximumQuantity) {
                NUMBER_OF_MANUAL_LOTTOS_ERROR
            }

            numberOfManualLottos
        }.getOrElse { error ->
            println(error.message.toString())
            getNumberOfManualLottos(maximumQuantity)
        }
    }

    private fun PurchasedLottos.printPurchasedLottos() {
        ResultView.printPurchasedNumberOfLottos(this.lottos.size)
        ResultView.printPurchasedLottos(this)
    }

    private fun checkPurchasedLottosResult(purchaseMoney: PurchaseMoney, purchasedLottos: PurchasedLottos) {
        val lottoResults = purchasedLottos.getTotalLottoResults(winningNumbers)
        val profit = profitCalculator.getProfit(purchaseMoney, lottoResults)

        ResultView.printLottoResults(lottoResults, profit)
    }

    companion object {
        private const val LOTTO_PRICE = 1000
        private const val NUMBER_OF_MANUAL_LOTTOS_ERROR = "[ERROR] 구입한 로또 개수를 초과했습니다."
    }
}
