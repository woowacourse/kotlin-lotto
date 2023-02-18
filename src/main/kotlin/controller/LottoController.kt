package controller

import common.MAXIMUM_LOTTO_RANGE
import common.MINIMUM_LOTTO_RANGE
import domain.Lotto
import domain.LottoNumber
import domain.LottoSeller
import domain.LottoStatistics
import domain.Ticket
import domain.WinningLotto
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
        val result = lottoStatistics.compareTicket(ticket)
        val profit = lottoStatistics.calculateProfitToString(result)
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
            val winningNumbers = makeWinningLotto()
            val bonusNumber = makeBonusNumber()
            runCatching { WinningLotto(winningNumbers, LottoNumber(bonusNumber)) }
                .onSuccess { return it }
        }
    }

    private fun makeWinningLotto(): Lotto {
        while (true) {
            runCatching { Lotto(inputView.getNumbers().map { number -> LottoNumber(number) }.toSet()) }
                .onSuccess { return it }
        }
    }

    private fun makeBonusNumber(): Int {
        while (true) {
            val bonusNumber = inputView.getBonusNumber()
            runCatching { validate(bonusNumber) }
                .onSuccess { return bonusNumber }
        }
    }

    private fun validate(bonus: Int) {
        require(bonus in MINIMUM_LOTTO_RANGE..MAXIMUM_LOTTO_RANGE)
    }
}
