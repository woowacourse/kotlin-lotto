package lotto.controller

import lotto.domain.Cashier
import lotto.domain.LottoDrawingMachine
import lotto.domain.MarginCalculator
import lotto.domain.RandomLottoGenerator
import lotto.domain.model.Lotto
import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Money
import lotto.domain.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val cashier: Cashier,
    private val randomLottoGenerator: RandomLottoGenerator,
    private val lottoDrawingMachine: LottoDrawingMachine
) {

    fun start() {
        val money = getValidMoney()
        val quantity = getLottoQuantity(money)
        val lottoTickets = makeLottoTicket(quantity)
        val result = drawLotto(lottoTickets)
        showResult(result, money)
    }

    private fun getValidMoney(): Money {
        return try {
            Money(InputView.readPurchaseAmount())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
    }

    private fun getLottoQuantity(money: Money): Int {
        val quantity = cashier.toTicketQuantity(money)
        OutputView.printLottoQuantity(quantity)
        return quantity
    }

    private fun makeLottoTicket(quantity: Int): List<Lotto> {
        val lottoTickets = List(quantity) {
            randomLottoGenerator.make()
        }
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
