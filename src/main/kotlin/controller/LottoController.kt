package controller

import domain.* // ktlint-disable no-wildcard-imports
import view.InputView
import view.OutputView

class LottoController {

    fun run() {
        val money = askMoney(InputView.inputMoney())
        val lottoCount = askManualLottoCount(InputView.inputManualLottoCount(), money)
        val manualLottos = askManualLottos(InputView.inputManualLottos(lottoCount.count), lottoCount.count)
        val autoLottos = LottoMachine(RandomLottoGenerator()).generateAutoLottos(lottoCount.getAutoLottoCount())
        OutputView.outputLottoSizeMessage(lottoCount.count, lottoCount.getAutoLottoCount())
        OutputView.outputLottos(manualLottos + autoLottos)
        val winningResult = getWinningResult(manualLottos, autoLottos)
        OutputView.outputWinningResult(winningResult)
        OutputView.outputYield(winningResult.calculateYield(money))
    }
    private fun getWinningResult(manualLottos: List<Lotto>, autoLottos: List<Lotto>): WinningResult {
        val winningLotto =
            WinningLotto(askWinningLotto(InputView.inputWinningLotto()), askBonusNumber(InputView.inputBonusNumber()))
        return WinningResult(matchLottos(manualLottos + autoLottos, winningLotto))
    }
    private fun askMoney(input: Int): Money {
        return runCatching {
            Money(input)
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askMoney(InputView.inputMoney())
    }

    private fun askManualLottoCount(input: Int?, money: Money): Count {
        return runCatching {
            Count(money, input!!)
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askManualLottoCount(InputView.inputManualLottoCount(), money)
    }

    private fun askManualLottos(input: List<IntArray>, count: Int): List<Lotto> {
        return runCatching {
            val lottoMachine = LottoMachine(RandomLottoGenerator())
            lottoMachine.generateManualLottos(input)
        }.onFailure { OutputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askManualLottos(InputView.inputManualLottos(count), count)
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

    fun matchLottos(lottos: List<Lotto>, winningLotto: WinningLotto): Map<Rank, Int> {
        return mutableMapOf<Rank, Int>().getMatchLotto(lottos, winningLotto)
    }

    private fun MutableMap<Rank, Int>.getMatchLotto(lottos: List<Lotto>, winningLotto: WinningLotto): Map<Rank, Int> {
        lottos.forEach {
            val rank = winningLotto.matchLotto(it)
            if (!rank.equals(Rank.MISS)) this.put(rank, this.getOrDefault(rank, 0) + 1)
        }
        return this
    }
}
