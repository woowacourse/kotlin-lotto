package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.UserLotto
import lotto.model.UserLottoCount
import lotto.model.WinningLotto
import lotto.model.generator.LottoGenerator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val generator: LottoGenerator = LottoGenerator()
) {
    fun start() {
        val money = getMoney()
        val myLottoCount = getUserLottoCount(getNumberOfLotto(money))
        val myLotto = getUserLotto(myLottoCount)
        outputView.printUserLotto(myLotto)
        wrapUp(myLotto, money)
    }

    private fun getMoney(): Int {
        return inputView.getNumber { outputView.printInsertMoneyMessage() }
    }

    fun getNumberOfLotto(money: Int): Int {
        return money / LOTTO_PRICE
    }

    private fun getUserLottoCount(numberOfLotto: Int): UserLottoCount {
        val numberOfPassiveLotto = getNumberOfPassiveLotto()
        return validateInput {
            UserLottoCount.create(numberOfLotto, numberOfPassiveLotto)
        } ?: getUserLottoCount(numberOfLotto)
    }

    private fun getNumberOfPassiveLotto(): Int {
        return inputView.getNumber { outputView.printInsertPassiveLottoNumber() }
    }

    private fun getUserLotto(lottoCount: UserLottoCount): UserLotto {
        outputView.printPurchase(lottoCount.passive, lottoCount.auto)

        val lotto = mutableListOf<Lotto>()
        getPassiveLotto(lottoCount.passive, lotto)
        getAutoLotto(lottoCount.auto, lotto)

        return UserLotto(lotto)
    }

    private fun getPassiveLotto(number: Int, lotto: MutableList<Lotto>) {
        outputView.printInsertPassiveLotto()

        repeat(number) {
            lotto.add(getLottoNumber { })
        }
    }

    private fun getAutoLotto(number: Int, lotto: MutableList<Lotto>) {
        repeat(number) {
            lotto.add(Lotto.create(generator.generate()))
        }
    }

    private fun wrapUp(myLotto: UserLotto, money: Int) {
        val winningLotto = getWinningLotto(getLottoNumber { outputView.printInsertWinningNumber() })
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        outputView.printResult(ranks, rates)
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return validateInput {
            WinningLotto(lotto, getBonusNumber())
        } ?: getWinningLotto(lotto)
    }

    private fun getLottoNumber(guideMessage: () -> Unit): Lotto {
        return validateInput {
            Lotto.create(inputView.getNumberList { guideMessage() })
        } ?: getLottoNumber { guideMessage() }
    }

    private fun getBonusNumber(): LottoNumber {
        return validateInput {
            LottoNumber.create(inputView.getNumber { outputView.printInsertBonusNumber() })
        } ?: getBonusNumber()
    }

    private fun <T> validateInput(create: () -> T): T? {
        return runCatching {
            create()
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
