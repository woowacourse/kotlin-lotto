package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.LottoMachine
import lotto.domain.model.Lottos
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import lotto.domain.value.LottoPayInfo
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun runLotto() {
        val lottoPayInfo = getPayInfo()
        outputView.printLottoPurchaseQuantity(lottoPayInfo)
        val lottos: Lottos = getLottosByPayInfo(lottoPayInfo)
        outputView.printTicketsByLottos(lottos)
        val lottoWinningStats = lottos.getLottoWinningStats(getWinningLotto())
        outputView.printLottoStats(lottoWinningStats)
        outputView.printLottoEarningRate(lottoWinningStats.getEarningRate())
    }

    private fun getPayInfo(): LottoPayInfo {
        val lottoPurchaseAmount = inputView.readLottoPurchaseAmount()
        val manualLottoQuantity = inputView.readManualLottoQuantity()
        return LottoPayInfo(lottoPurchaseAmount, manualLottoQuantity)
    }

    private fun getLottosByPayInfo(payInfo: LottoPayInfo): Lottos {
        val lottoMachine = LottoMachine()
        return lottoMachine.generateLottos(payInfo)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbersWithoutBonus = inputView.readWinningLottoNumbersWithoutBonus()
        val winningLottoWithoutBonus = Lotto.createManual(winningLottoNumbersWithoutBonus.toSet())
        val bonusNumberText = inputView.readBonusNumber()
        val bonusNumber = LottoNumber(bonusNumberText)

        return WinningLotto(winningLottoWithoutBonus, bonusNumber)
    }
}
