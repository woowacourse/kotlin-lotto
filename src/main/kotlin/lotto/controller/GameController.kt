package lotto.controller

import lotto.model.FixedLottoNumbersGenerator
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoNumbers
import lotto.model.LottoPurchaseBudget
import lotto.model.LottoPurchasePlan
import lotto.model.LottoWinningBundle
import lotto.model.RandomLottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class GameController {
    fun start() {
        val purchasedLottos = purchaseLottos()
        val lottoWinningBundle = createLottoWinningBundle()
        val lottoResult = lottoWinningBundle.calculateResult(purchasedLottos)
        OutputView.printResult(lottoResult)
    }

    private fun purchaseLottos(): List<Lotto> {
        val purchasePlan = determinePurchasePlan()

        val manualPurchasedLottos = purchaseManualLottos(purchasePlan)
        val autoPurchasedLottos = purchaseAutoLottos(purchasePlan)
        OutputView.printLottos(manualPurchasedLottos, autoPurchasedLottos)
        return manualPurchasedLottos + autoPurchasedLottos
    }

    private fun determinePurchasePlan(): LottoPurchasePlan {
        val lottoPurchaseBudget = LottoPurchaseBudget(InputView.readTotalBudget())
        return LottoPurchasePlan(lottoPurchaseBudget, InputView.readManualPurchaseCount())
    }

    private fun purchaseManualLottos(purchasePlan: LottoPurchasePlan): List<Lotto> {
        val manualLottoBuyNumbers = InputView.readManualPurchaseNumbers(purchasePlan.manualPurchaseCount)
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(manualLottoBuyNumbers)
        return LottoMachine.createLottos(purchasePlan.manualPurchaseCount, fixedLottoNumbersGenerator)
    }

    private fun purchaseAutoLottos(purchasePlan: LottoPurchasePlan): List<Lotto> {
        return LottoMachine.createLottos(purchasePlan.autoPurchaseCount, RandomLottoNumbersGenerator)
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(LottoNumbers(lottoWinningNumbers.map { LottoNumber.of(it) }))
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
