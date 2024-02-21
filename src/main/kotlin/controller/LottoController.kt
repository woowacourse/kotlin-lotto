package controller

import model.Cashier
import model.Lotto
import model.LottoGenerator
import model.LottoNumber
import model.Money
import view.InputView
import view.OutputView

class LottoController(
    private val cashier: Cashier,
    private val lottoGenerator: LottoGenerator
) {

    fun start() {
        val money = getValidMoney()
        val quantity = cashier.toTicketQuantity(money)
        OutputView.printLottoQuantity(quantity)

        val lottoTickets = List(quantity) { lottoGenerator.make() }
        OutputView.printLottoNumbers(lottoTickets)

        val winningLotto = getValidLotto()
        val bonusNumber = getValidBonusNumber()
    }

    private fun getValidMoney(): Money {
        return try {
            Money(InputView.readPurchaseAmount())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
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
            LottoNumber(InputView.readBonusNumber())
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidBonusNumber()
        }
    }
}
