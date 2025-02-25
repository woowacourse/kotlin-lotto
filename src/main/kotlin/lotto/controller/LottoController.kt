package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder
import lotto.domain.model.WinningLotto
import lotto.domain.service.AutomaticLottoMachine
import lotto.domain.service.LottoCalculator
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoMachine = AutomaticLottoMachine()
        val lottoCalculator = LottoCalculator()

        val purchaseAmount = getPurchaseAmount()
        val quantity = purchaseAmount.getPurchaseQuantity()

        val manualLottoCount = getManualLottoCount()
        val manualLottoNumbers = getManualLottoNumbers(manualLottoCount)

        val manualLottoOrder = LottoOrder(manualLottoCount, manualLottoNumbers)

        val lottoOrder = LottoOrder(quantity)
        val lottos = lottoMachine.generate(lottoOrder)
        outputView.printPurchaseDetail(lottos)

        val winningLotto = getWinningLotto()
        val lottoStats = lottoCalculator.calculate(winningLotto, lottos)
        val earningRate = lottoCalculator.calculateEarningRate(lottoStats, purchaseAmount)
        outputView.printLottoResult(lottoStats, earningRate)
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val amount = inputView.readPurchaseAmount()
        return PurchaseAmount(amount)
    }

    private fun getManualLottoCount(): Int {
        val count = inputView.readManualLottoCount()
        return count
    }

    private fun getManualLottoNumbers(count: Int): List<List<Int>> {
        val lottoNumbers = inputView.readManualLottoNumbers(count)
        return lottoNumbers
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = getWinningNumbers()
        val bonusNumber = getBonusNumber()
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun getWinningNumbers(): Lotto {
        val winningNumbers = inputView.readWinningNumbers().map { LottoNumber.from(it) }
        return Lotto(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.readBonusNumber()
        return LottoNumber.from(bonusNumber)
    }
}
