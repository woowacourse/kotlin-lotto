package controller

import domain.Lotto
import domain.LottoMachine
import domain.MakeLottoStrategy
import domain.WinningLotto
import domain.model.LottoDrawingResult
import domain.model.LottoNumber
import domain.model.Money
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView
) {
    fun start() {
        val lottoMachine = runLottoMachine()
        val countOfTicket = getTicket(lottoMachine)
        val lottoTickets = makeLottoTickets(countOfTicket)
        val result = drawLotto(lottoTickets)
        showResult(lottoMachine, result)
    }

    private fun runLottoMachine(): LottoMachine {
        return try {
            val money = getMoney()
            return LottoMachine(money)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            runLottoMachine()
        }
    }

    private fun getMoney(): Money {
        return try {
            Money(inputView.readPurchaseAmount())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getMoney()
        }
    }

    private fun getTicket(lottoMachine: LottoMachine): Int {
        val countOfTicket = lottoMachine.countTicket()
        outputView.printNumberOfTicket(countOfTicket)
        return countOfTicket
    }

    private fun makeLottoTickets(number: Int): List<Lotto> {
        val lottoTickets = List(number) { MakeLottoStrategy.MakeSortedRandomLotto(1..45).makeLotto() }
        outputView.printLottoNumbers(lottoTickets)
        return lottoTickets
    }

    private fun drawLotto(lottoTickets: List<Lotto>): LottoDrawingResult {
        val winningLotto = getValidWinningLotto(getValidLotto())
        val result = winningLotto.countRank(lottoTickets)
        outputView.printLottoResult(result)
        return result
    }

    private fun getValidLotto(): Lotto {
        return try {
            Lotto(*inputView.readWinningNumbers().toIntArray())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidLotto()
        }
    }

    private fun getValidWinningLotto(winningLotto: Lotto): WinningLotto {
        return try {
            val bonusNumber = getBonusNumber()
            WinningLotto(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidWinningLotto(winningLotto)
        }
    }

    private fun getBonusNumber(): LottoNumber {
        return LottoNumber(inputView.readBonusNumber())
    }

    private fun showResult(lottoMachine: LottoMachine, result: LottoDrawingResult) {
        val totalPrize = lottoMachine.calculateTotalPrize(result)
        val marginRate = lottoMachine.calculateMargin(totalPrize)
        outputView.printMargin(marginRate)
    }
}
