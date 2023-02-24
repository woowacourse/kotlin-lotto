package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.UserLotto
import lotto.model.UserLottoCount
import lotto.model.WinningLotto
import lotto.model.generator.LottosGenerator
import lotto.model.generator.RandomLottoGenerator
import lotto.view.InputInterface
import lotto.view.InputView
import lotto.view.OutputInterface
import lotto.view.OutputView

class LottoController(
    private val inputView: InputInterface = InputView(),
    private val outputView: OutputInterface = OutputView(),
    private val generator: LottosGenerator = RandomLottoGenerator()
) {
    fun start() {
        val money = getMoney()
        val myLottoCount = getUserLottoCount(money)
        val myLotto = getMyLotto(myLottoCount)
        wrapUpResult(myLotto, money)
    }

    private fun getMoney(): Int {
        return validateInput { inputView.getPurchaseMoney() } ?: getMoney()
    }

    private fun getManualLottoCount(): Int {
        return validateInput { inputView.getManualLottoCount() } ?: getManualLottoCount()
    }

    private fun getUserLottoCount(money: Int): UserLottoCount {
        val manualLottoCount = getManualLottoCount()
        return validateInput { UserLottoCount(money, manualLottoCount) } ?: getUserLottoCount(money)
    }

    private fun getMyLotto(lottoCount: UserLottoCount): UserLotto {
        val manualLottos = getManualLottos(lottoCount.manual)
        val autoLottos = generator.generate(lottoCount.auto)
        val myLottos = UserLotto(manualLottos + autoLottos)
        outputView.printPurchaseCounts(lottoCount)
        outputView.printUserLottos(myLottos)
        return myLottos
    }

    private fun getManualLottos(count: Int): List<Lotto> {
        val lottos = mutableListOf<Lotto>()
        repeat(count) {
            lottos.add(getEachOfManualLotto())
        }
        return lottos
    }

    private fun getEachOfManualLotto(): Lotto {
        return validateInput { Lotto.create(inputView.getManualLottoNumbers()) } ?: getEachOfManualLotto()
    }

    private fun getWinningLotto(winningNumbers: Lotto): WinningLotto {
        return validateInput { WinningLotto(winningNumbers, getBonusNumber()) } ?: getWinningLotto(winningNumbers)
    }

    private fun getWinningNumbers(): Lotto =
        validateInput { Lotto.create(inputView.getWinningLottoNumbers()) } ?: getWinningNumbers()

    private fun getBonusNumber(): LottoNumber =
        validateInput { LottoNumber.create(inputView.getWinningBonusNumber()) } ?: getBonusNumber()

    private fun wrapUpResult(myLotto: UserLotto, money: Int) {
        val winningLotto = getWinningLotto(getWinningNumbers())
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        outputView.printResult(ranks, rates)
    }

    private fun <T> validateInput(create: () -> T): T? {
        return runCatching {
            create()
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }
}
