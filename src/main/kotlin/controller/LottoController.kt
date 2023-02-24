package controller

import domain.* // ktlint-disable no-wildcard-imports
import view.InputView
import view.OutputView

class LottoController(val inputView: InputView, val outputView: OutputView) {

    fun run() {
        val money = askMoney(inputView.inputMoney())
        val lottoCount = askManualLottoCount(inputView.inputManualLottoCount(), money)
        val manualLottos = askManualLottos(inputView.inputManualLottos(lottoCount.count), lottoCount.count)
        val autoLottos = LottoMachine(RandomLottoGenerator()).generateAutoLottos(lottoCount.getAutoLottoCount())
        outputView.outputLottoSizeMessage(lottoCount.count, lottoCount.getAutoLottoCount())
        outputView.outputLottos(manualLottos + autoLottos)
        val winningResult = getWinningResult(manualLottos, autoLottos)
        outputView.outputWinningResult(winningResult)
        outputView.outputYield(winningResult.calculateYield(money))
    }
    private fun getWinningResult(manualLottos: List<Lotto>, autoLottos: List<Lotto>): WinningResult {
        val winningLotto =
            WinningLotto(askWinningLotto(inputView.inputWinningLotto()), askBonusNumber(inputView.inputBonusNumber()))
        return WinningResult(matchLottos(manualLottos + autoLottos, winningLotto))
    }
    private fun askMoney(input: Int): Money {
        return runCatching {
            Money(input)
        }.onFailure { outputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askMoney(inputView.inputMoney())
    }

    private fun askManualLottoCount(input: Int?, money: Money): Count {
        return runCatching {
            Count(money, input!!)
        }.onFailure { outputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askManualLottoCount(inputView.inputManualLottoCount(), money)
    }

    private fun askManualLottos(input: List<IntArray>, count: Int): List<Lotto> {
        return runCatching {
            val lottoMachine = LottoMachine(RandomLottoGenerator())
            lottoMachine.generateManualLottos(input)
        }.onFailure { outputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askManualLottos(inputView.inputManualLottos(count), count)
    }

    private fun askWinningLotto(input: IntArray): Lotto {
        return runCatching {
            Lotto(*input)
        }.onFailure { outputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askWinningLotto(inputView.inputWinningLotto())
    }

    private fun askBonusNumber(input: Int): LottoNumber {
        return runCatching {
            LottoNumber.from(input)
        }.onFailure { outputView.outputErrorMessage(it.message!!) }
            .getOrNull() ?: askBonusNumber(inputView.inputBonusNumber())
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
