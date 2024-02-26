package lotto.controller

import lotto.model.AutoLottoMachine
import lotto.model.FixedLottoNumbersGenerator
import lotto.model.Lotto
import lotto.model.LottoBuyBudget
import lotto.model.LottoNumber
import lotto.model.LottoWinningBundle
import lotto.model.ManualLottoMachine
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
        val lottoBuyBudget = LottoBuyBudget(InputView.readAvailableFund())
        val manualBuyedLottos = buyManualLottos(lottoBuyBudget)
        val autoBuyedLottos = buyAutoLottos(lottoBuyBudget)
        OutputView.printLottos(manualBuyedLottos, autoBuyedLottos)
        return manualBuyedLottos + autoBuyedLottos
    }

    private fun buyManualLottos(lottoBuyBudget: LottoBuyBudget): List<Lotto> {
        val manualLottoBuyCount = InputView.readManualLottoBuyCount()
        val manualLottoMachine = ManualLottoMachine(manualLottoBuyCount, lottoBuyBudget)
        val manualLottoBuyNumbers = InputView.readManualLottoBuyNumbers(manualLottoBuyCount)
        val fixedLottoNumbersGenerator = FixedLottoNumbersGenerator(manualLottoBuyNumbers)
        return manualLottoMachine.createLottos(fixedLottoNumbersGenerator)
    }

    private fun buyAutoLottos(lottoBuyBudget: LottoBuyBudget): List<Lotto> {
        return AutoLottoMachine.createLottos(lottoBuyBudget, RandomLottoNumbersGenerator)
    }

    private fun createLottoWinningBundle(): LottoWinningBundle {
        val lottoWinningNumbers = InputView.readLottoWinningNumbers()
        val winningLotto = Lotto(lottoWinningNumbers.map { LottoNumber.of(it) })
        val lottoBonusNumber = InputView.readLottoBonusNumber()

        return LottoWinningBundle(winningLotto, LottoNumber.of(lottoBonusNumber))
    }
}
