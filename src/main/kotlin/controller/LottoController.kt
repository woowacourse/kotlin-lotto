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
        val money = initializeMoney()
        val ticket = makeTicket(money)

        val winningLotto = makeWinningLotto()
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.compareTicket(ticket)
        val profit = lottoStatistics.calculateProfit(result)
        val profitRatio = calculateProfitRatio(profit, money)

        resultView.printResult(result, profitRatio)
    }

    private fun makeManualTicket(): Ticket {
        val count = inputView.getManualLottoCount()
        val numbers = inputView.getManualLottos(count)
        return lottoSeller.sellManualLottos(numbers)
    }

    private fun makeAutoTicket(count: Int): Ticket {
        return runCatching {
            lottoSeller.sellRandomLottos(count)
        }.getOrElse { error ->
            println(error.message)
            makeAutoTicket(count)
        }
    }

    private fun makeTicket(money: Int): Ticket {
        val manualTicket = makeManualTicket()
        val autoTicket = makeAutoTicket((money / EACH_LOTTO_PRICE) - manualTicket.size)
        resultView.printTicket(manualTicket, autoTicket)
        return manualTicket.concatenateTicket(autoTicket)
    }

    private fun calculateProfitRatio(profit: Int, totalMoney: Int): String {
        return floor((profit / totalMoney).toDouble()).toString()
    }

    private fun initializeMoney(): Int {
        val money = inputView.getMoney()
        resultView.printCount(money / EACH_LOTTO_PRICE)
        return money
    }

    private fun makeWinningLotto(): WinningLotto {
        return runCatching {
            val winningNumbers = initializeWinningLotto()
            val bonusNumber = inputView.getBonusNumber()
            WinningLotto(winningNumbers, LottoNumber(bonusNumber))
        }.getOrElse { error ->
            println(error)
            makeWinningLotto()
        }
    }

    private fun initializeWinningLotto(): Lotto {
        return runCatching {
            Lotto(inputView.getWinningLotto().map { number -> LottoNumber(number) }.toSet())
        }.getOrElse { error ->
            println(error)
            initializeWinningLotto()
        }
    }

    companion object {
        private const val EACH_LOTTO_PRICE = 1000
    }
}
