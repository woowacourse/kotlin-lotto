package lotto.controller

import lotto.domain.Cashier
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
    private val randomLottoGenerator: RandomLottoGenerator
) {

    fun start() {
        val money = getValidMoney()
        val quantity = getLottoQuantity(money)
        val lottoTickets = makeLottoTicket(quantity)
        val winningLotto = getWinningLotto()
        val result = getLottoDrawingResult(lottoTickets, winningLotto)
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

    private fun getWinningLotto(): WinningLotto {
        val winningNumber = getValidLotto()
        val bonusNumber = getValidBonusNumber()
        return WinningLotto(winningNumber, bonusNumber)
    }

    private fun getValidLotto(): Lotto {
        return try {
            Lotto(InputView.readWinningNumbers().map { LottoNumber(it) })
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidLotto()
        }
    }

    private fun getValidBonusNumber(): LottoNumber {
        return try {
            return LottoNumber(InputView.readBonusNumber())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidBonusNumber()
        }
    }

    private fun getLottoDrawingResult(lottoTickets: List<Lotto>, winningLotto: WinningLotto): LottoDrawingResult {
        val ranks = lottoTickets.map { targetLotto -> winningLotto.getRank(targetLotto) }
        val lottoResult = LottoDrawingResult.from(ranks)
        OutputView.printLottoResult(lottoResult)
        return lottoResult
    }

    private fun showResult(result: LottoDrawingResult, money: Money) {
        val totalPrize = MarginCalculator.calculateTotalPrize(result)
        val marginRate = MarginCalculator.calculateMarginRate(totalPrize, money)
        OutputView.printMargin(marginRate)
    }

    companion object {
        const val LOTTO_PRICE = 1000
    }
}
