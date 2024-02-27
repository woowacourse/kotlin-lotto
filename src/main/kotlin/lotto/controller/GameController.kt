package lotto.controller

import lotto.model.Budget
import lotto.model.FixedLottoNumbersGenerator
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.LottoWinningBundle
import lotto.model.PurchaseDetails
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

    private fun createPurchaseDetails(): PurchaseDetails {
        val budget = Budget(InputView.readTotalBudget())
        val manualPurchaseCount = InputView.readManualPurchaseCount()
        return PurchaseDetails(budget, manualPurchaseCount)
    }

    private fun purchaseLottos(purchaseDetails: PurchaseDetails): List<Lotto> {
        val manualPurchasedLottos = purchaseManualLottos(purchaseDetails)
        val autoPurchasedLottos = purchaseAutoLottos(purchaseDetails)
        OutputView.printLottos(manualPurchasedLottos, autoPurchasedLottos)
        return manualPurchasedLottos + autoPurchasedLottos
    }

    private fun purchaseManualLottos(purchasePlan: PurchaseDetails): List<Lotto> {
        val manualLottoBuyNumbers = InputView.readManualPurchaseNumbers(purchasePlan.manualPurchaseCount)
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(manualLottoBuyNumbers)
        return LottoMachine.createLottos(purchasePlan.manualPurchaseCount, fixedLottoNumbersGenerator)
    }

    private fun purchaseAutoLottos(purchasePlan: PurchaseDetails): List<Lotto> {
        return LottoMachine.createLottos(purchasePlan.autoPurchaseCount, RandomLottoNumbersGenerator)
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(LottoNumbers(lottoWinningNumbers.map { LottoNumber.of(it) }))
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
