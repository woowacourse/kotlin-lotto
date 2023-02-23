package controller

import domain.Amount
import domain.Count
import domain.Lotto
import domain.LottoCalculator
import domain.LottoNumber
import domain.LottoResult
import domain.LottoStore
import domain.WinningLotto
import view.InputView
import view.OutputView

class LottoController(private val inputView: InputView, private val outputView: OutputView) {
    fun run() {
        runCatching {
            val amount = askAmount()
            val manualCount = askManualCount(amount)
            val manualLottos = buyManualLotto(manualCount)
            val autoLottos = buyAutoLotto(LottoCalculator(LOTTO_PRICE).getAutoCount(amount, manualCount))
            outputView.outputLottos(manualLottos, autoLottos)
            val winningLotto = askWinningInfo()
            outputView.outputResult(LottoResult.of(manualLottos + autoLottos, winningLotto))
        }.onFailure {
            outputView.outputError(it.message)
        }
    }

    private fun askAmount(): Amount {
        outputView.outputGetAmount()
        return Amount(inputView.inputInt())
    }

    private fun askManualCount(amount: Amount): Count {
        outputView.outputGetCount()
        return Count(inputView.inputInt())
    }

    private fun buyManualLotto(count: Count): List<Lotto> {
        outputView.outputGetManualLottos()
        val store = LottoStore()
        val lottos = (1..count.toInt()).map {
            inputView.inputString()
        }
        return store.buyManualLotto(lottos)
    }

    private fun buyAutoLotto(count: Count): List<Lotto> {
        val store = LottoStore()
        return store.buyAutoLotto(count)
    }

    private fun askWinningInfo(): WinningLotto = WinningLotto(askWinningNumbers(), askBonusNumber())

    private fun askWinningNumbers(): Lotto {
        outputView.outputGetWinningNumbers()
        return Lotto.create(inputView.inputString().split(",").map { it.toInt() })
    }

    private fun askBonusNumber(): LottoNumber {
        outputView.outputGetBonusNumber()
        return LottoNumber(inputView.inputInt())
    }

    companion object {
        private val LOTTO_PRICE = 1000
    }
}
