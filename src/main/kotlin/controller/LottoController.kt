package controller

import domain.Lotto
import domain.LottoCount
import domain.LottoNumber
import domain.LottoSeller
import domain.LottoStatistics
import domain.Money
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
        resultView.printCount(money.divide(EACH_LOTTO_PRICE))
        val ticket = makeTicket(money)

        val winningLotto = makeWinningLotto()
        val lottoStatistics = LottoStatistics(winningLotto)
        val result = lottoStatistics.compareTicket(ticket)
        val profit = lottoStatistics.calculateProfit(result)
        val profitRatio = calculateProfitRatio(profit, money)

        resultView.printResult(result, profitRatio)
    }

    private fun makeManualTicket(count: Int): Ticket {
        return runCatching {
            val manualCount = inputView.getManualLottoCount()
            LottoCount(count - manualCount)
            val numbers = inputView.getManualLottos(manualCount)
            lottoSeller.sellManualLottos(numbers)
        }.getOrElse { error ->
            println(error.message)
            makeManualTicket(count)
        }
    }

    private fun makeAutoTicket(count: Int): Ticket {
        return runCatching {
            lottoSeller.sellRandomLottos(count)
        }.getOrElse { error ->
            println(error.message)
            makeAutoTicket(count)
        }
    }

    private fun makeTicket(money: Money): Ticket {
        val manualTicket = makeManualTicket(money.divide(EACH_LOTTO_PRICE))
        val autoTicket = makeAutoTicket((money.divide(EACH_LOTTO_PRICE)) - manualTicket.size)
        resultView.printTicket(manualTicket, autoTicket)
        return manualTicket.concatenateTicket(autoTicket)
    }

    private fun calculateProfitRatio(profit: Int, totalMoney: Money): String {
        return floor((profit / totalMoney.money).toDouble()).toString()
    }

    private fun initializeMoney(): Money {
        return runCatching {
            Money(inputView.getMoney())
        }.getOrElse { error ->
            println(error.message)
            initializeMoney()
        }
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
