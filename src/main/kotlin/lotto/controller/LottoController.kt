package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoOrder
import lotto.domain.model.WinningLotto
import lotto.domain.service.AutomaticLottoMachine
import lotto.domain.service.LottoCalculator
import lotto.domain.service.LottoStore
import lotto.domain.service.ManualLottoMachine
import lotto.domain.value.LottoCount
import lotto.domain.value.LottoNumber
import lotto.domain.value.PurchaseAmount
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val store = LottoStore()
        val manualLottoMachine = ManualLottoMachine()
        val automaticLottoMachine = AutomaticLottoMachine()

        val purchaseAmount = getPurchaseAmount()
        val totalLottoCount = purchaseAmount.getPurchaseQuantity()

        val manualLottoCount = getManualLottoCount()
        val manualLottoNumbers = getManualLottoNumbers(manualLottoCount)

        val lottoOrder = LottoOrder(totalLottoCount, manualLottoCount, manualLottoNumbers)

        val automaticLottoCount = lottoOrder.getAutomaticLottoCount()

        val manualLottos = store.purchase(manualLottoMachine, manualLottoCount, manualLottoNumbers)
        val automaticLottos = store.purchase(automaticLottoMachine, automaticLottoCount)

        val totalLottos = manualLottos + automaticLottos
        outputView.printPurchaseDetail(manualLottoCount, automaticLottoCount, totalLottos)

        val lottoCalculator = LottoCalculator()

        val winningLotto = getWinningLotto()
        val lottoStats = lottoCalculator.calculate(winningLotto, totalLottos)
        val earningRate = lottoCalculator.calculateEarningRate(lottoStats, purchaseAmount)
        outputView.printLottoResult(lottoStats, earningRate)
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val amount = inputView.readPurchaseAmount()
        return PurchaseAmount(amount)
    }

    private fun getManualLottoCount(): LottoCount {
        val count = inputView.readManualLottoCount()
        return LottoCount(count)
    }

    private fun getManualLottoNumbers(lottoCount: LottoCount): List<List<Int>> {
        val lottoNumbers = inputView.readManualLottoNumbers(lottoCount.count)
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
