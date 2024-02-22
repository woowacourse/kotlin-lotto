package controller

import domain.Cashier
import domain.LottoDrawingMachine
import domain.LottoGenerator
import domain.MarginCalculator
import domain.model.Lotto
import domain.model.LottoDrawingResult
import domain.model.LottoNumber
import domain.model.Money
import domain.model.WinningLotto
import view.InputView
import view.OutputView

class LottoController(
    private val cashier: Cashier,
    private val lottoDrawingMachine: LottoDrawingMachine
) {

    fun start() {
        val (money, quantity) = receiveMoney()
        val lottoTickets = makeLottoTicket(quantity)
        val result = drawLotto(lottoTickets)
        showResult(result, money)
    }

    private fun receiveMoney(): Pair<Money, Int> {
        val money = getValidMoney()
        val quantity = cashier.toTicketQuantity(money)
        OutputView.printLottoQuantity(quantity)
        return Pair(money, quantity)
    }

    private fun getValidMoney(): Money {
        return try {
            Money(InputView.readPurchaseAmount())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
    }

    private fun makeLottoTicket(quantity: Int): List<Lotto> {
        val randomNumbers = LottoGenerator.makeRandomNumber()
        val lottoTickets = List(quantity) { LottoGenerator.makeLotto(randomNumbers) }
        OutputView.printLottoNumbers(lottoTickets)
        return lottoTickets
    }

    private fun drawLotto(lottoTickets: List<Lotto>): LottoDrawingResult {
        val winningLotto = getValidLotto()
        val winningNumber = getValidWinningLotto(winningLotto)
        val result = lottoDrawingMachine.countRank(lottoTickets, winningNumber)
        OutputView.printLottoResult(result)
        return result
    }

    private fun getValidLotto(): Lotto {
        return try {
            Lotto(InputView.readWinningNumbers().map { LottoNumber(it) })
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
        return LottoNumber(InputView.readBonusNumber())
    }

    private fun showResult(result: LottoDrawingResult, money: Money) {
        val totalPrize = MarginCalculator.calculateTotalPrize(result)
        val marginRate = MarginCalculator.calculateMarginRate(totalPrize, money)
        OutputView.printMargin(marginRate)
    }
}
