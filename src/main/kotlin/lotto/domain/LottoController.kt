package lotto.domain

import lotto.model.Lotto
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
        val winningLotto = getWinningLotto(getWinningNumber())
    }

    private fun getUserLotto(number: Int): UserLotto {
        val lottos = mutableListOf<Lotto>()
        repeat(number) {
            lottos.add(Lotto(generator.generate()))
        }

        return UserLotto(lottos)
    }

    private fun getMoney(): Int {
        outputView.printInsertMoneyMessage()
        val money = inputView.getNumber()
        val result = validateInput {
            require(isDivided(money)) { ERROR_NOT_DIVIDED }
        }
        return if (result) money else getMoney()
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return runCatching {
            WinningLotto(lotto, getBonusNumber())
        }.onFailure {
            println(it.message)
        }.getOrNull() ?: getWinningLotto(lotto)
    }

    private fun getBonusNumber(): Int {
        outputView.printInsertBonusNumber()
        return inputView.getNumber()
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInsertWinningNumber()
        return runCatching {
            Lotto(inputView.getNumberList())
        }.onFailure {
            println(it.message)
        }.getOrNull() ?: getWinningNumber()
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
