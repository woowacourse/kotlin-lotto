package controller

import domain.LottoCustomer
import domain.LottoSeller
import domain.ProfitCalculator
import domain.lottogenerator.WinningLottoGenerator
import domain.model.PurchaseMoney
import domain.model.WinningLotto
import domain.model.lotto.Lotto
import domain.model.lotto.PurchasedLottos
import util.NumericValidator
import view.InputView
import view.ResultView

class LottoController(
    private val lottoSeller: LottoSeller = LottoSeller(),
    private val winningNumbersGenerator: WinningLottoGenerator = WinningLottoGenerator(),
    private val profitCalculator: ProfitCalculator = ProfitCalculator(),
) {

    private val lottoCustomer: LottoCustomer by lazy {
        val purchaseMoney = PurchaseMoney(NumericValidator.validate(InputView.requestPurchaseMoney()))
        val manualLottosCount = NumericValidator.validate(InputView.requestManualLottoCount())

        LottoCustomer(purchaseMoney, manualLottosCount)
    }

    private val winningLotto: WinningLotto by lazy {
        val catchLotto = InputView.requestCatchNumbers().map { catchNumber ->
            NumericValidator.validate(catchNumber)
        }
        val bonusNumber = NumericValidator.validate(InputView.requestBonusNumber())

        winningNumbersGenerator.generateWinningLotto(catchLotto, bonusNumber)
    }

    private fun purchaseManualLottos(): List<Lotto> =
        List(lottoCustomer.manualLottosCountToPurchase) { numberOfPurchase ->
            val manualNumbers = InputView.requestManualLottoNumbers(numberOfPurchase).map { manualLottoNumber ->
                NumericValidator.validate(manualLottoNumber)
            }

            lottoSeller.sellManualLotto(manualNumbers)
        }

    private fun purchaseAutomaticLottos(): List<Lotto> =
        lottoSeller.sellAutoMaticLottos(lottoCustomer.getAutomaticLottosCountToPurchase())

    fun run() {
        runCatching {
            val purchasedLottos = PurchasedLottos(purchaseManualLottos() + purchaseAutomaticLottos())
            ResultView.printPurchasedLottos(lottoCustomer, purchasedLottos)
            val lottosResult = purchasedLottos.getTotalLottoResults(winningLotto)
            val profit = profitCalculator.getProfit(lottoCustomer.purchaseMoney, lottosResult)
            ResultView.printLottoResults(lottosResult, profit)
        }.onFailure { exception ->
            ResultView.printExceptionMessage(exception)
        }
    }
}
