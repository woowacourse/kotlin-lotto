package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.UserLotto
import lotto.model.WinningLotto
import lotto.model.generator.LottoGenerator
import lotto.view.ERROR_NOT_DIVIDED
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val generator: LottoGenerator = LottoGenerator()
) {
    fun start() {
        val money = getMoney()
        val numberOfLotto = getNumberOfLotto(money)
        outputView.printPurchase(numberOfLotto)
        val myLotto = getUserLotto(numberOfLotto)
        outputView.printUserLotto(myLotto)

        wrapUp(myLotto, money)
    }

    private fun wrapUp(myLotto: UserLotto, money: Int) {
        val winningLotto = getWinningLotto(getWinningNumber())
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        outputView.printResult(ranks, rates)
    }

    fun getUserLotto(number: Int): UserLotto {
        val lotto = mutableListOf<Lotto>()
        repeat(number) {
            lotto.add(Lotto.create(generator.generate()))
        }

        return UserLotto(lotto)
    }

    private fun getMoney(): Int {
        outputView.printInsertMoneyMessage()
        val money = inputView.getNumber()
        val result = runCatching {
            require(isDivided(money)) { println(ERROR_NOT_DIVIDED) }
        }.isSuccess

        return if (result) money else getMoney()
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return validateInput {
            WinningLotto(lotto, getBonusNumber())
        } ?: getWinningLotto(lotto)
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printInsertBonusNumber()

        return validateInput {
            LottoNumber(inputView.getNumber())
        } ?: getBonusNumber()
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInsertWinningNumber()

        return validateInput {
            Lotto.create(inputView.getNumberList())
        } ?: getWinningNumber()
    }

    private fun <T> validateInput(create: () -> T): T? {
        return runCatching {
            create()
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }

    fun getNumberOfLotto(money: Int): Int {
        return money / LOTTO_PRICE
    }

    fun isDivided(money: Int): Boolean {
        return money % LOTTO_PRICE == 0
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
