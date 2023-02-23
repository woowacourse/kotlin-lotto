package controller

import domain.LottoMaker
import domain.Lottos
import domain.Money
import domain.WinningNumber
import domain.WinningResult
import view.InputView
import view.OutputView

class LottoGame {

    private val input by lazy { InputView() }
    private val output by lazy { OutputView() }

    fun run() {
        val money = getMoney()
        val lottos = makeLottos(money)
        val winningResult = startGame(lottos)
        endGame(winningResult, money)
    }

    private fun getMoney(): Money {
        return runCatching {
            val money = input.inputMoney()
            return money
        }.onFailure {
            println(INPUT_MONEY_ERROR_MESSAGE)
        }.getOrDefault(getMoney())
    }

    private fun makeLottos(money: Money): Lottos {
        return runCatching {
            val count = input.InputManualLottoCount()
            val manualLottos = LottoMaker().makeManualLottos(input.inputManualLottoNumber(count).map { LottoMaker().wrapLotto(it) })
            val autoLottos = LottoMaker().makeAutoLottos(money.lottoCount() - count)
            output.outputLottoSizeMessage(count, money.lottoCount() - count)
            output.outputLottos(Lottos(manualLottos + autoLottos))
            return Lottos(manualLottos + autoLottos)
        }.onFailure {
            println(INPUT_LOTTO_ERROR_MESSAGE)
        }.getOrDefault(makeLottos(money))
    }

    private fun startGame(lottos: Lottos): WinningResult {
        return runCatching {
            val winningNumber = WinningNumber(LottoMaker().wrapLotto(input.inputWinningLotto()), input.inputBonusNumber())
            return lottos.matchLottos(winningNumber)
        }.onFailure {
            println(INPUT_LOTTO_ERROR_MESSAGE)
        }.getOrDefault(startGame(lottos))
    }

    private fun endGame(winningResult: WinningResult, money: Money) {
        output.outputWinningResult(winningResult)
        output.outputYield(winningResult.calculateYield(money))
    }

    companion object {
        private const val INPUT_MONEY_ERROR_MESSAGE = "[ERROR] 금액은 1000원으로 나누어떨어지는 양수여야 합니다."
        private const val INPUT_LOTTO_ERROR_MESSAGE = "[ERROR] 로또 번호는 중복되지 않은 6개의 수여야합니다."
    }
}
