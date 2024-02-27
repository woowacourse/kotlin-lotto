package lotto.controller

import lotto.domain.Cashier
import lotto.domain.MarginCalculator
import lotto.domain.RandomLottoGenerator
import lotto.domain.model.Lotto
import lotto.domain.model.LottoDrawingResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Money
import lotto.domain.model.WinningNumbers
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val cashier: Cashier,
    private val randomLottoGenerator: RandomLottoGenerator
) {

    fun start() {
        val money = getValidMoney()
        val quantity = getLottoQuantity(money)
        val lottoTickets = makeLottoTicket(quantity)
        val winningLotto = getValidWinningLotto()
        val winningNumbers = getValidWinningNumbers(winningLotto)
        val result = getLottoDrawingResult(winningNumbers, lottoTickets)
        showResult(result, money)
    }

    private fun getValidMoney(): Money {
        return try {
            Money(InputView.readPurchaseAmount(LOTTO_PRICE))
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
    }

    private fun getLottoQuantity(money: Money): Int {
        val quantity = cashier.calculateQuantity(money, LOTTO_PRICE)
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

    private fun getValidWinningLotto(): Lotto {
        return try {
            Lotto(InputView.readWinningNumbers().map { LottoNumber(it) })
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidWinningLotto()
        }
    }

    private fun getValidWinningNumbers(winningLotto: Lotto): WinningNumbers {
        return try {
            val bonusNumber = LottoNumber(InputView.readBonusNumber())
            WinningNumbers(winningLotto, bonusNumber)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidWinningNumbers(winningLotto)
        }
    }

    private fun getLottoDrawingResult(winningLotto: WinningNumbers, lottoTickets: List<Lotto>): LottoDrawingResult {
        val lottoResult = winningLotto.getResult(lottoTickets)
        OutputView.printLottoResult(lottoResult)
        return lottoResult
    }

    private fun showResult(result: LottoDrawingResult, money: Money) {
        val totalPrize = result.calculateTotalPrize()
        val marginRate = MarginCalculator.calculateMarginRate(totalPrize, money)
        OutputView.printMargin(marginRate)
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
