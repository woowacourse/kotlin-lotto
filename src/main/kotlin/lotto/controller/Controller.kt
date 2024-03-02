package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.Lottos
import lotto.model.RandomLottoNumberGenerator
import lotto.model.UserPrize
import lotto.model.WinningNumber
import lotto.util.Constant.LOTTO_PRICE
import lotto.view.calculationOfYield
import lotto.view.insertBonusNumbers
import lotto.view.insertCostMessage
import lotto.view.insertManuallyLotto
import lotto.view.insertManuallyLottoCount
import lotto.view.insertWinNumbers
import lotto.view.outputWinningNumber
import lotto.view.printCombinedLottos
import lotto.view.purchaseCountMessage
import lotto.view.winningStatistics

class Controller {
    private val lottoGenerator = RandomLottoNumberGenerator()

    fun run() {
        val charge = enterPurchaseAmount()
        val (manualCount, autocount, manualLottos) = makeManuallyLotto(charge)
        val combinedLottos = combineManualAndAutomaticLotto(manualCount, autocount, manualLottos)
        val winningNumber = createWinningNumbers()
        val prize = combinedLottos.matchlottos(winningNumber)
        outputWinningStatistics(prize, charge)
    }

    private fun combineManualAndAutomaticLotto(
        manualCount: Int,
        autocount: Int,
        manualLottos: Lottos,
    ): Lottos {
        purchaseCountMessage(manualCount, autocount)
        val lottos = makeLottos(autocount)
        val combinedLottos = lottos.combineLottos(manualLottos, lottos)
        printCombinedLottos(combinedLottos)
        return combinedLottos
    }

    private fun createWinningNumbers(): WinningNumber {
        insertWinNumbers()
        val winning = readLottoNumber()
        insertBonusNumbers()
        val bonusNumber = LottoNumber.of(readLottoBonusNumber())
        val winningNumber = WinningNumber(lotto = winning, bonusNumber = bonusNumber)
        return winningNumber
    }

    private fun makeManuallyLotto(charge: Int): Triple<Int, Int, Lottos> {
        insertManuallyLottoCount()
        val manualCount = readHowManyManually(charge)
        val autocount = (charge / LOTTO_PRICE).toInt() - manualCount
        insertManuallyLotto()
        val manualLottos = makeManualLottos(List(manualCount) { readManualLottoNumber() })
        return Triple(manualCount, autocount, manualLottos)
    }

    private fun enterPurchaseAmount(): Int {
        insertCostMessage()
        val charge = readCharge()
        return charge
    }

    private fun outputWinningStatistics(
        prize: UserPrize,
        charge: Int,
    ) {
        winningStatistics()
        outputWinningNumber(prize)
        calculationOfYield(prize, charge.toDouble())
    }

    private fun makeLottos(count: Int): Lottos {
        return Lottos(
            List(count) {
                val lotto = lottoGenerator.generateNumbers()
                lotto
            },
        )
    }

    private fun makeManualLottos(lottos: List<Lotto>): Lottos {
        return Lottos(lottos)
    }
}
