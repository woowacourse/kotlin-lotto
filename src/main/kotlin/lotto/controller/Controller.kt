package lotto.controller

import lotto.model.LottoGenerator
import lotto.model.Lottos
import lotto.model.wallet.Wallet
import lotto.model.winning.WinningNumber
import lotto.view.inputBonusNumber
import lotto.view.inputCostMessage
import lotto.view.inputLottoNumbers
import lotto.view.inputManualLottoNumbers
import lotto.view.inputManualNumber
import lotto.view.inputWinningLottoNumbers
import lotto.view.outputCalculationOfYield
import lotto.view.outputLottos
import lotto.view.outputNewLine
import lotto.view.outputPurchaseCount
import lotto.view.outputWinningNumber
import lotto.view.outputWinningStatistics

class Controller {
    private lateinit var wallet: Wallet
    private val lottos = Lottos()
    private lateinit var winningNumber: WinningNumber

    fun purchaseLottos() {
        wallet = inputCostMessage()
        val lottoCount = wallet.getLottoCount()

        val manualNumber = inputManualNumber(lottoCount)
        val manualCount = manualNumber.getNumber()
        val autoLottoCount = lottoCount - manualCount

        outputNewLine()
        if (manualCount > 0) {
            inputManualLottoNumbers()
            val manualLottos = List(manualCount) {
                inputLottoNumbers()
            }
            lottos.purchaseLottos(manualLottos)
        }

        val autoLottos = List(autoLottoCount) {
            LottoGenerator.generateLotto()
        }
        lottos.purchaseLottos(autoLottos)

        outputPurchaseCount(manualCount, autoLottoCount)
        outputLottos(lottos.getLottos())
    }

    fun enterWinningNumber() {
        outputNewLine()
        inputWinningLottoNumbers()
        val winningLottos = inputLottoNumbers()
        outputNewLine()
        val bonusNumber = inputBonusNumber(winningLottos)
        winningNumber = WinningNumber(
            lotto = winningLottos,
            bonusNumber = bonusNumber
        )
    }

    fun checkResult() {
        val prize = lottos.matchLottos(winningNumber)
        outputWinningStatistics()
        outputWinningNumber(prize.getUserPrize())
        val prizeCalculate = prize.prizeRateCalculate(
            prize = prize.prizeCalculate(),
            charge = wallet.getPrice().toDouble()
        )
        prizeCalculate?.let {
            outputCalculationOfYield(it)
        }
    }
}
