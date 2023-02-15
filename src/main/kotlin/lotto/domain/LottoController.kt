package lotto.domain

import lotto.model.Lotto
import lotto.model.generator.LottoGenerator
import lotto.view.ERROR_NOT_DIVIDED
import lotto.view.ERROR_NOT_POSITIVE_NUMBER
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun start() {
        val money = getMoney()
        val numberOfLotto = getNumberOfLotto(money)
        outputView.printPurchase(numberOfLotto)
    }

    fun getMoney(): Int {
        outputView.printInsertMoneyMessage()
        val money = inputView.getNumber()
        val result = validateInput {
            require(money.isNumber()) { ERROR_NOT_POSITIVE_NUMBER }
            require(isDivided(money.toInt())) { ERROR_NOT_DIVIDED }
        }
        return if (result) money.toInt() else getMoney()
    }

    private fun validateInput(validate: () -> Unit): Boolean {
        return runCatching {
            validate()
        }.onFailure {
            println(it.message)
        }.isSuccess
    }

    fun getNumberOfLotto(money: Int): Int {
        return money / LOTTO_PRICE
    }

    fun generateLotto(number: Int): List<Lotto> {
        val lotto = mutableListOf<Lotto>()
        repeat(number) {
            lotto.add(Lotto(LottoGenerator().generate()))
        }
        return lotto
    }

    fun isDivided(money: Int): Boolean {
        return money % LOTTO_PRICE == 0
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}

fun String.isNumber() = this.chars().allMatch { Character.isDigit(it) }
