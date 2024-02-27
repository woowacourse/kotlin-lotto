package lotto.controller

import lotto.model.FixedLottoNumbersGenerator
import lotto.model.Lotto
import lotto.model.LottoBudget
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.LottoPurchaseDetails
import lotto.model.LottoWinningBundle
import lotto.model.RandomLottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class GameController {
    fun start() {
        val purchaseDetails = createPurchaseDetails()
        val purchasedLottos = purchaseLottos(purchaseDetails)
        val lottoWinningBundle = createLottoWinningBundle()
        val lottoResult = lottoWinningBundle.calculateResult(purchasedLottos)
        OutputView.printResult(lottoResult)
        OutputView.printProfitRate(lottoResult, purchaseDetails)
    }

    private fun createPurchaseDetails(): LottoPurchaseDetails {
        val lottoBudget = LottoBudget(InputView.readTotalBudget())
        val manualPurchaseCount = InputView.readManualPurchaseCount()
        return LottoPurchaseDetails(lottoBudget, manualPurchaseCount)
    }

    private fun purchaseLottos(purchaseDetails: LottoPurchaseDetails): List<Lotto> {
        val manualPurchasedLottos = purchaseManualLottos(purchaseDetails)
        val autoPurchasedLottos = purchaseAutoLottos(purchaseDetails)
        OutputView.printLottos(manualPurchasedLottos, autoPurchasedLottos)
        return manualPurchasedLottos + autoPurchasedLottos
    }

    private fun purchaseManualLottos(purchasePlan: LottoPurchaseDetails): List<Lotto> {
        val manualLottoBuyNumbers = InputView.readManualPurchaseNumbers(purchasePlan.manualPurchaseCount)
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(manualLottoBuyNumbers)
        return LottoMachine.createLottos(purchasePlan.manualPurchaseCount, fixedLottoNumbersGenerator)
    }

    private fun purchaseAutoLottos(purchasePlan: LottoPurchaseDetails): List<Lotto> {
        return LottoMachine.createLottos(purchasePlan.autoPurchaseCount, RandomLottoNumbersGenerator)
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(LottoNumbers(lottoWinningNumbers.map { LottoNumber.of(it) }))
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
