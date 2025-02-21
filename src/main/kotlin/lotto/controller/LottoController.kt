package lotto.controller

import lotto.constants.ErrorMessages
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
        val input = inputView.readPurchaseAmount()
        val amount =
            runCatching {
                input.toInt()
            }.getOrElse { throw IllegalArgumentException(ErrorMessages.INVALID_NUMBER_FORMAT) }
        return PurchaseAmount(amount)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = getWinningNumbers()
        val bonusNumber = getBonusNumber()
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private fun getWinningNumbers(): Lotto {
        val input = inputView.readWinningNumbers()
        val lottoNumbers =
            runCatching {
                input.map { LottoNumber(it.toInt()) }
            }.getOrElse { throw IllegalArgumentException(ErrorMessages.INVALID_NUMBER_FORMAT) }
        return Lotto(lottoNumbers)
    }

    private fun getBonusNumber(): LottoNumber {
        val input = inputView.readBonusNumber()
        val bonusNumber =
            runCatching {
                input.toInt()
            }.getOrElse { throw IllegalArgumentException(ErrorMessages.INVALID_NUMBER_FORMAT) }
        return LottoNumber(bonusNumber)
    }
}
