package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Money
import lotto.model.UserLotto
import lotto.model.WinningLotto
import lotto.model.generator.LottoGenerator
import lotto.view.ERROR_OUT_OF_LOTTO_NUMBER
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val generator: LottoGenerator = LottoGenerator()
) {
    fun start() {
        val money = getMoney()
        val numberOfLotto = money.getNumberOfLotto()
        val manualNumberOfLotto = getNumberOfManualLotto(numberOfLotto)

        outputView.printPurchase(numberOfLotto)
        val myLotto = getUserLotto(numberOfLotto)
        outputView.printUserLotto(myLotto)

        wrapUpResult(myLotto, money)
    }

    private fun getNumberOfManualLotto(numberOfLotto: Int): Int {
        outputView.printInsertManualNumber()
        val number = inputView.getNumber()
        val result = runCatching {
            require(numberOfLotto >= number) { ERROR_OUT_OF_LOTTO_NUMBER }
        }.onFailure { println(it.message) }.isSuccess

        return if (result) number else getNumberOfManualLotto(numberOfLotto)
    }

    private fun wrapUpResult(myLotto: UserLotto, money: Money) {
        val winningLotto = getWinningLotto(getWinningNumber())
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        outputView.printResult(ranks, rates)
    }

    fun getUserLotto(number: Int): UserLotto {
        val lotto = mutableListOf<Lotto>()
        repeat(number) {
            lotto.add(Lotto(*generator.generate().toIntArray()))
        }

        return UserLotto(lotto)
    }

    private fun getMoney(): Money {
        outputView.printInsertMoneyMessage()
        return validateInput {
            Money(inputView.getNumber())
        } ?: getMoney()
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return validateInput {
            WinningLotto(lotto, getBonusNumber())
        } ?: getWinningLotto(lotto)
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printInsertBonusNumber()

        return validateInput {
            LottoNumber.from(inputView.getNumber())
        } ?: getBonusNumber()
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInsertWinningNumber()

        return validateInput {
            Lotto(inputView.getNumberList())
        } ?: getWinningNumber()
    }

    private fun <T> validateInput(create: () -> T): T? {
        return runCatching {
            create()
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}
