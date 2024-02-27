package lotto.controller

import lotto.model.FixedLottoNumbersGenerator
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoPurchaseBudget
import lotto.model.LottoPurchasePlan
import lotto.model.LottoWinningBundle
import lotto.model.RandomLottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoGameController {
    fun start() {
        val buyedLottos = buyLottos()
        val lottoWinningBundle = createLottoWinningBundle()
        val lottoResult = lottoWinningBundle.calculateResult(buyedLottos)
        OutputView.printResult(lottoResult)
    }

    private fun buyLottos(): List<Lotto> {
        val lottoPurchaseBudget = LottoPurchaseBudget(InputView.readTotalBudget())
        val lottoPurchasePlan = LottoPurchasePlan(lottoPurchaseBudget, InputView.readManualLottoBuyCount())

        val manualBuyedLottos = buyManualLottos(lottoPurchasePlan)
        val autoBuyedLottos = buyAutoLottos(lottoPurchasePlan)
        OutputView.printLottos(manualBuyedLottos, autoBuyedLottos)
        return manualBuyedLottos + autoBuyedLottos
    }

    private fun buyManualLottos(lottoPurchasePlan: LottoPurchasePlan): List<Lotto> {
        val manualLottoBuyNumbers = InputView.readManualLottoBuyNumbers(lottoPurchasePlan.manualPurchaseCount)
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(manualLottoBuyNumbers)
        return LottoMachine.createLottos(lottoPurchasePlan.manualPurchaseCount, fixedLottoNumbersGenerator)
    }

    private fun buyAutoLottos(lottoPurchasePlan: LottoPurchasePlan): List<Lotto> {
        return LottoMachine.createLottos(lottoPurchasePlan.autoPurchaseCount, RandomLottoNumbersGenerator)
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(lottoWinningNumbers.map { LottoNumber.of(it) })
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
