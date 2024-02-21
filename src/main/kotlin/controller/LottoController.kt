package controller

import model.Money
import view.InputView

class LottoController {

    fun start() {
        getValidMoney()
    }

    private fun getValidMoney(): Money {
        return try {
            val money = InputView.readPurchaseAmount()
            Money(money)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            getValidMoney()
        }
    }
}
