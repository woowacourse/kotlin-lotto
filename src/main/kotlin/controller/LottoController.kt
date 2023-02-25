package controller

import domain.Lotto
import domain.LottoCount
import domain.LottoNumber
import domain.LottoSeller
import domain.LottoStatistics
import domain.ManualLottoMachine
import domain.Money
import domain.RandomLottoMachine
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
        val money = makeMoney()
        resultView.printCount(money.makeCount())
        val ticket = makeTicket(money)

        val winningLotto = makeWinningLotto()
        val lottoStatistics = LottoStatistics(winningLotto, ticket)

        val winningCountBy = lottoStatistics.getWinningCountBy()
        val profitRatio = lottoStatistics.getProfitRatio(money)
        resultView.printResult(winningCountBy, profitRatio)
    }

    private fun makeManualTicket(count: Int): Ticket {
        return runCatching {
            val manualCount = inputView.getManualLottoCount()
            LottoCount(count - manualCount)
            val numbers = inputView.getManualLottos(manualCount)
            lottoSeller.sellTicket(manualCount, ManualLottoMachine(numbers))
        }.getOrElse { error ->
            println(error.message)
            makeManualTicket(count)
        }
    }

    private fun makeAutoTicket(count: Int): Ticket {
        return runCatching {
            lottoSeller.sellTicket(count, RandomLottoMachine())
        }.getOrElse { error ->
            println(error.message)
            makeAutoTicket(count)
        }
    }

    private fun makeTicket(money: Money): Ticket {
        val manualTicket = makeManualTicket(money.makeCount())
        val autoTicket = makeAutoTicket((money.makeCount()) - manualTicket.size())
        resultView.printTicket(manualTicket, autoTicket)
        return manualTicket.concatenateTicket(autoTicket)
    }

    private fun makeMoney(): Money {
        return runCatching {
            Money(inputView.getMoney())
        }.getOrElse { error ->
            println(error.message)
            makeMoney()
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
}
