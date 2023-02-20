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
        val purchaseLottoMoney = inputView.getMoney()
        val ticket = lottoSeller.sellLottos(purchaseLottoMoney)
        val winningLotto = initializeWinningLotto()

        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.matchTicket(ticket)
        val profit = lottoStatistics.yield(result, purchaseLottoMoney)
        resultView.printResult(result, profit)
    }

    private fun initializeWinningLotto(): WinningLotto {
        while (true) {
            val winningNumbers = inputView.getWinningNumbers()
            val bonusNumber = inputView.getBonusNumber()
            runCatching { WinningLotto(winningNumbers, bonusNumber) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }
}
