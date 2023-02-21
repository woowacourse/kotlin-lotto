package controller

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
        val profit = lottoStatistics.calculateProfit(result)
        resultView.printResult(result, profit)
    }

    private fun initializeTicket(): Ticket {
        return runCatching {
            val count = inputView.getMoney()
            lottoSeller.sellLottos(count)
        }.getOrElse { error ->
            println(error.message)
            initializeTicket()
        }
    }

    private fun initializeWinningLotto(): WinningLotto {
        return runCatching {
            val winningNumbers = makeWinningLotto()
            val bonusNumber = makeBonusNumber()
            WinningLotto(winningNumbers, LottoNumber(bonusNumber))
        }.getOrElse {
            initializeWinningLotto()
        }
    }

    private fun makeWinningLotto(): Lotto {
        return runCatching {
            Lotto(inputView.getNumbers().map { number -> LottoNumber(number) }.toSet())
        }.getOrElse {
            makeWinningLotto()
        }
    }

    private fun makeBonusNumber(): Int {
        return runCatching {
            inputView.getBonusNumber()
        }.getOrElse {
            makeBonusNumber()
        }
    }
}
