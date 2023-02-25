package controller

import domain.* // ktlint-disable no-wildcard-imports
import view.InputView
import view.OutputView

class LottoController {

    fun run() {
        val money = askMoney()
        val lottos = buyLottos(money)
        val winningResult = getWinningResult(lottos)
        OutputView.outputWinningResult(winningResult)
        OutputView.outputYield(winningResult.calculateYield(money))
    }

    private fun getWinningResult(lottos: List<Lotto>): WinningResult {
        OutputView.outputLottos(lottos)
        val winningLotto =
            WinningLotto(askWinningLotto(InputView.inputWinningLotto()), askBonusNumber(InputView.inputBonusNumber()))
        return winningLotto.matchLottos(lottos)
    }

    private fun askMoney(): Money {
        return runCatching {
            Money(InputView.inputMoney())
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askMoney()
    }

    private fun buyLottos(money: Money): List<Lotto> {
        val manualLottoCount = InputView.inputManualLottoCount()
        val manualLottos = askManualLottos(manualLottoCount)
        val autoLottoCount = money.leftMoney(manualLottoCount).getLottoCount()
        val autoLottos = LottoMachine.generateAutoLottos(autoLottoCount, RandomLottoGenerator())
        OutputView.outputLottoSizeMessage(manualLottoCount, autoLottoCount)
        return manualLottos + autoLottos
    }

    private fun askManualLottos(count: Int): List<Lotto> {
        return runCatching {
            LottoMachine.generateManualLottos(InputView.inputManualLottos(count))
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askManualLottos(count)
    }

    private fun askWinningLotto(input: IntArray): Lotto {
        return runCatching {
            Lotto(*input)
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askWinningLotto(InputView.inputWinningLotto())
    }

    private fun askBonusNumber(input: Int): LottoNumber {
        return runCatching {
            LottoNumber.from(input)
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askBonusNumber(InputView.inputBonusNumber())
    }
}
