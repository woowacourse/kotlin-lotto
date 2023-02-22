package controller

import domain.LottoCustomer
import domain.LottoSeller
import domain.lottogenerator.WinningLottoGenerator
import domain.model.PurchaseMoney
import domain.model.WinningLotto
import domain.model.lotto.Lotto
import domain.model.lotto.PurchasedLottos
import view.InputView
import view.ResultView

class LottoGameController(
    private val winningLottoGenerator: WinningLottoGenerator = WinningLottoGenerator(),
    private val lottoSeller: LottoSeller = LottoSeller(),
) {

    fun run() {
        val lottoCustomer = initCustomer()
        val purchasedLottos = purchaseLottos(lottoCustomer)

        ResultView.printPurchasedLottos(lottoCustomer, purchasedLottos)
        checkLottoResults(lottoCustomer, purchasedLottos)
    }

    private fun initCustomer(): LottoCustomer = retryLottoStep {
        LottoCustomer(
            PurchaseMoney(InputView.requestPurchaseMoney()),
            InputView.requestManualLottoCount()
        )
    }

    private fun purchaseLottos(lottoCustomer: LottoCustomer): PurchasedLottos {
        val manualLottos = List(lottoCustomer.manualLottosCountToPurchase) {
            retryLottoStep { purchaseManualLotto(it) }
        }
        val automaticLottos = lottoSeller.sellAutoMaticLottos(lottoCustomer.getAutomaticLottosCountToPurchase())

        return PurchasedLottos(manualLottos + automaticLottos)
    }

    private fun purchaseManualLotto(numberOfPurchased: Int): Lotto = lottoSeller.sellManualLotto(
        InputView.requestManualLottoNumbers(numberOfPurchased)
    )

    private fun initWinningLotto(): WinningLotto = retryLottoStep {
        winningLottoGenerator.generateWinningLotto(
            InputView.requestCatchNumbers(),
            InputView.requestBonusNumber()
        )
    }

    private fun checkLottoResults(lottoCustomer: LottoCustomer, purchasedLottos: PurchasedLottos) {
        val winningLotto = initWinningLotto()
        val lottoResults = purchasedLottos.getTotalLottoResults(winningLotto)
        val profitRate = lottoCustomer.getMyProfitRate(lottoResults)

        ResultView.printLottoResults(lottoResults, profitRate)
    }

    private fun <T> retryLottoStep(lottoStep: () -> T): T {
        return runCatching(lottoStep).getOrElse { exception ->
            ResultView.printExceptionMessage(exception)
            retryLottoStep(lottoStep)
        }
    }
}
