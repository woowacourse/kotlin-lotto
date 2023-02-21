package controller

import domain.Lotto
import domain.LottoNumber
import domain.LottoSeller
import domain.LottoStatistics
import domain.Ticket
import domain.WinningLotto
import view.InputViewInterface
import view.ResultViewInterface
import kotlin.math.floor

class LottoController(
    private val inputView: InputViewInterface,
    private val resultView: ResultViewInterface
) {
    private val lottoSeller: LottoSeller by lazy { LottoSeller() }

    fun run() {
        val money = inputView.getMoney()
        val ticket = initializeTicket(money / EACH_LOTTO_PRICE)
        val winningLotto = initializeWinningLotto()

        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.compareTicket(ticket)
        val profit = lottoStatistics.calculateProfit(result)
        val profitRatio = calculateProfitRatio(profit, money)
        resultView.printResult(result, profitRatio)
    }

    private fun calculateProfitRatio(profit: Int, totalMoney: Int): String {
        return floor((profit / totalMoney).toDouble()).toString()
    }

    private fun initializeTicket(count: Int): Ticket {
        return runCatching {
            lottoSeller.sellLottos(count)
        }.getOrElse { error ->
            println(error.message)
            initializeTicket(count)
        }
    }

    private fun initializeWinningLotto(): WinningLotto {
        return runCatching {
            val winningNumbers = makeWinningLotto()
            val bonusNumber = inputView.getBonusNumber()
            WinningLotto(winningNumbers, LottoNumber(bonusNumber))
        }.getOrElse { error ->
            println(error)
            initializeWinningLotto()
        }
    }

    private fun makeWinningLotto(): Lotto {
        return runCatching {
            Lotto(inputView.getNumbers().map { number -> LottoNumber(number) }.toSet())
        }.getOrElse { error ->
            println(error)
            makeWinningLotto()
        }
    }

    companion object {
        private const val EACH_LOTTO_PRICE = 1000
    }
}
