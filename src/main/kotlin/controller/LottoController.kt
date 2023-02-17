package controller

import domain.*
import view.InputViewInterface
import view.ResultViewInterface

class LottoController(
    private val inputView: InputViewInterface,
    private val resultView: ResultViewInterface
) {
    private val lottoSeller: LottoSeller by lazy { LottoSeller() }

    fun run() {
        val ticket = initializeTicket()
        val winningLotto = initializeWinningLotto()

        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.matchTicket(ticket)
        val profit = lottoStatistics.yield(result)
        resultView.printResult(result, profit)
    }

    private fun initializeTicket(): Ticket {
        while (true) {
            runCatching { inputView.getMoney() }
                .onSuccess { return lottoSeller.sellLottos(it) }
        }
    }

    private fun initializeWinningLotto(): WinningLotto {
        while (true) {
            val winningNumbers = makeWinningNumbers()
            val bonusNumber = makeBonusNumber()
            runCatching { WinningLotto(winningNumbers, bonusNumber) }
                .onSuccess { return it }
        }
    }

    private fun makeWinningNumbers(): Lotto {
        while (true) {
            runCatching { Lotto(inputView.getNumbers().map { LottoNumber.from(it) }.toSet()) }
                .onSuccess { return it }
        }
    }

    private fun makeBonusNumber(): LottoNumber {
        while (true) {
            val bonusNumber = inputView.getBonusNumber()
            runCatching { LottoNumber.from(bonusNumber) }
                .onSuccess { return it }
        }
    }
}
