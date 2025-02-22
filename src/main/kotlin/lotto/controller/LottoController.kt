package lotto.controller

import lotto.domain.model.LottoMachine
import lotto.domain.model.Lottos
import lotto.domain.model.WinningLotto
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
        return LottoPayInfo(lottoPurchaseAmount)
    }

    private fun getLottosByPayInfo(payInfo: LottoPayInfo): Lottos {
        val lottoMachine = LottoMachine()
        return lottoMachine.generateLottos(payInfo)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoWithoutBonus = inputView.readWinningLottoWithoutBonusNumber()
        return inputView.getWinningLottoWithBonusNumber(winningLottoWithoutBonus)
    }
}
