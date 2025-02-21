package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoCalculator
import lotto.domain.service.LottoMachine
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoMachine = LottoMachine()
        val lottoCalculator = LottoCalculator()

        val purchaseAmount = getPurchaseAmount()
        val lottos = lottoMachine.generate(purchaseAmount)
        outputView.printPurchaseDetail(purchaseAmount, lottos)

        val winningLotto = getWinningLotto()
        val lottoStats = lottoCalculator.calculate(winningLotto, lottos)
        val earningRate = lottoCalculator.calculateEarningRate(lottoStats, purchaseAmount)
        outputView.printLottoResult(lottoStats, earningRate)
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val amount = inputView.readPurchaseAmount()
        return PurchaseAmount(amount)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = getWinningNumbers()
        val bonusNumber = getBonusNumber()
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun getWinningNumbers(): Lotto {
        val winningNumbers = inputView.readWinningNumbers().map { LottoNumber(it) }
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.readBonusNumber()
        return LottoNumber(bonusNumber)
    }
}
