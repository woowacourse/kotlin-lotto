package lotto.controller

import lotto.model.FixedLottosGenerator
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.PurchaseBudget
import lotto.model.PurchaseDetails
import lotto.model.RandomLottosGenerator
import lotto.model.WinningBundle
import lotto.view.InputView
import lotto.view.OutputView

class GameController {
    fun start() {
        val purchaseDetails = createPurchaseDetails()
        val purchasedLottos = purchaseLottos(purchaseDetails)
        val winningBundle = createWinningBundle()
        val result = winningBundle.calculateResult(purchasedLottos)
        OutputView.printResult(result)
        OutputView.printProfitRate(result, purchaseDetails)
    }

    private fun createPurchaseDetails(): PurchaseDetails {
        val purchaseBudget = PurchaseBudget(InputView.readBudget())
        val manualPurchaseCount = InputView.readManualPurchaseCount()
        return PurchaseDetails(purchaseBudget, manualPurchaseCount)
    }

    private fun purchaseLottos(purchaseDetails: PurchaseDetails): List<Lotto> {
        val manualPurchasedLottos = purchaseManualLottos(purchaseDetails)
        val autoPurchasedLottos = purchaseAutoLottos(purchaseDetails)
        OutputView.printLottos(manualPurchasedLottos, autoPurchasedLottos)
        return manualPurchasedLottos + autoPurchasedLottos
    }

    private fun purchaseManualLottos(purchaseDetails: PurchaseDetails): List<Lotto> {
        val manualPurchaseNumbers = InputView.readManualPurchaseNumbers(purchaseDetails.manualPurchaseCount)
        val fixedLottosGenerator = FixedLottosGenerator(manualPurchaseNumbers)
        return LottoMachine.createLottos(purchaseDetails.manualPurchaseCount, fixedLottosGenerator)
    }

    private fun purchaseAutoLottos(purchaseDetails: PurchaseDetails): List<Lotto> {
        return LottoMachine.createLottos(purchaseDetails.autoPurchaseCount, RandomLottosGenerator)
    }

    private fun createWinningBundle(): WinningBundle {
        val winningNumbers = InputView.readWinningNumbers()
        val winningLotto = Lotto(LottoNumbers(winningNumbers.map { LottoNumber.of(it) }))
        val bonusLottoNumber = LottoNumber.of(InputView.readBonusNumber())

        return WinningBundle(winningLotto, bonusLottoNumber)
    }
}
