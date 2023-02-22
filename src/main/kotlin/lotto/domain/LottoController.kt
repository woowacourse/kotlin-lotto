package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Money
import lotto.model.UserLotto
import lotto.model.WinningLotto
import lotto.model.generator.LottoGenerator
import lotto.view.ERROR_INSERT_AGAIN
import lotto.view.ERROR_OUT_OF_LOTTO_NUMBER
import lotto.view.InputView
import lotto.view.OutputView.printInsertBonusNumber
import lotto.view.OutputView.printInsertManualLotto
import lotto.view.OutputView.printInsertManualNumber
import lotto.view.OutputView.printInsertMoneyMessage
import lotto.view.OutputView.printInsertWinningNumber
import lotto.view.OutputView.printPurchase
import lotto.view.OutputView.printResult
import lotto.view.OutputView.printUserLotto

class LottoController(
    private val generator: LottoGenerator = LottoGenerator(),
) {
    fun start() {
        val money = getMoney()
        val numberOfLotto = money.getNumberOfLotto()
        val manualNumberOfLotto = getNumberOfManualLotto(numberOfLotto)
        val autoNumberOfLotto = numberOfLotto - manualNumberOfLotto
        printPurchase(manualNumberOfLotto, autoNumberOfLotto)
        val myLotto = getUserLotto(getManualLotto(manualNumberOfLotto), autoNumberOfLotto)
        printUserLotto(myLotto)
        wrapUpResult(myLotto, money)
    }

    private fun getMoney(): Money {
        printInsertMoneyMessage()
        return validateInput {
            InputView.getNumber()?.let { Money(it) }
        } ?: getMoney()
    }

    private fun getNumberOfManualLotto(numberOfLotto: Int): Int {
        printInsertManualNumber()
        val number = InputView.getNumber() ?: getNumberOfManualLotto(numberOfLotto)
        val result = runCatching {
            require(numberOfLotto >= number) { ERROR_OUT_OF_LOTTO_NUMBER }
        }.onFailure { println(it.message) }.isSuccess

        return if (result) number else getNumberOfManualLotto(numberOfLotto)
    }

    private fun getManualLotto(numberOfLotto: Int): List<Lotto> {
        val manualLotto = mutableListOf<Lotto>()
        printInsertManualLotto()
        repeat(numberOfLotto) {
            manualLotto.add(getLottoNumber())
        }
        return manualLotto
    }

    fun getUserLotto(manualLotto: List<Lotto>, number: Int): UserLotto {
        val lotto = mutableListOf<Lotto>()
        lotto += manualLotto
        repeat(number) {
            lotto.add(Lotto(*generator.generate().toIntArray()))
        }

        return UserLotto(lotto)
    }

    private fun wrapUpResult(myLotto: UserLotto, money: Money) {
        printInsertWinningNumber()
        val winningLotto = getWinningLotto(getLottoNumber())
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        printResult(ranks, rates)
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return validateInput {
            WinningLotto(lotto, getBonusNumber())
        } ?: getWinningLotto(lotto)
    }

    private fun getBonusNumber(): LottoNumber {
        printInsertBonusNumber()
        return validateInput {
            InputView.getNumber()?.let { LottoNumber.from(it) }
        } ?: getBonusNumber()
    }

    private fun getLottoNumber(): Lotto {
        return validateInput {
            InputView.getNumberList()?.sortedBy { it }?.let { number -> Lotto(number.map { LottoNumber.from(it) }) }
        } ?: getLottoNumber()
    }

    private fun <T> validateInput(create: () -> T): T? {
        return runCatching {
            create()
        }.onSuccess {
            if (it == null)
                println(ERROR_INSERT_AGAIN)
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}
