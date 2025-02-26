package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.service.AutomaticLottoMachine
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
    private val store = LottoStore()
    private val manualLottoMachine = ManualLottoMachine()
    private val automaticLottoMachine = AutomaticLottoMachine()

    fun run() {
        val purchaseAmount = getPurchaseAmount()
        val totalLottoCount = purchaseAmount.getPurchaseQuantity()

        val manualLottoCount = getManualLottoCount()
        val automaticLottoCount = totalLottoCount.subtract(manualLottoCount)

        val totalLottos = getTotalLottos(manualLottoCount, automaticLottoCount)
        outputView.printPurchaseDetail(manualLottoCount, automaticLottoCount, totalLottos)

        val winningLotto = getWinningLotto()
        val lottoResult = winningLotto.getLottoResult(totalLottos)
        val earningRate = lottoResult.getEarningRate(purchaseAmount)
        outputView.printLottoResult(lottoResult, earningRate)
    }

    private fun getPurchaseAmount(): PurchaseAmount {
        val amount = inputView.readPurchaseAmount()
        return PurchaseAmount(amount)
    }

    private fun getManualLottoCount(): LottoCount {
        val count = inputView.readManualLottoCount()
        return LottoCount(count)
    }

    private fun getTotalLottos(
        manualLottoCount: LottoCount,
        automaticLottoCount: LottoCount,
    ): List<Lotto> {
        val manualLottoNumbers = getManualLottoNumbers(manualLottoCount)
        val manualLottos =
            store.publish(manualLottoMachine, manualLottoCount, manualLottoNumbers)
        val automaticLottos = store.publish(automaticLottoMachine, automaticLottoCount)
        return manualLottos + automaticLottos
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
        val winningNumbers = inputView.readWinningNumbers()
        return Lotto.of(winningNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        val bonusNumber = inputView.readBonusNumber()
        return LottoNumber.from(bonusNumber)
    }
}
