package lotto.controller

import lotto.domain.model.Lotto
import lotto.domain.model.Lottos
import lotto.domain.model.WinningLotto
import lotto.domain.model.lottomachine.AutoLottoMachine
import lotto.domain.model.lottomachine.ManualLottoMachine
import lotto.domain.value.LottoNumber
import lotto.domain.value.LottoPayInfo
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun safeRunLotto() =
        runCatching {
            runLotto()
        }.fold(
            onSuccess = { it },
            onFailure = { e ->
                when (e) {
                    is IllegalArgumentException,
                    is IllegalStateException,
                    -> throw e

                    else -> null
                }
            },
        )

    private fun runLotto() {
        val lottoPayInfo = getPayInfo()
        val lottos: Lottos = getLottosByPayInfo(lottoPayInfo)
        outputView.printLottoPurchaseQuantity(lottoPayInfo)
        outputView.printTicketsByLottos(lottos)
        val lottoWinningStats = lottos.getLottoWinningStats(getWinningLotto())
        outputView.printLottoStats(lottoWinningStats)
        outputView.printLottoEarningRate(lottoWinningStats.getEarningInfo())
    }

    private fun getPayInfo(): LottoPayInfo {
        val lottoPurchaseAmount = inputView.readLottoPurchaseAmount()
        val manualLottoQuantity = inputView.readManualLottoQuantity()
        return LottoPayInfo(lottoPurchaseAmount, manualLottoQuantity)
    }

    private fun getLottosByPayInfo(payInfo: LottoPayInfo): Lottos {
        val manualLottoTickets = getManualLottoTickets(payInfo)
        val autoLottoTickets = getAutoLottoTickets(payInfo)
        return Lottos(manualLottoTickets + autoLottoTickets)
    }

    private fun getManualLottoTickets(payInfo: LottoPayInfo): List<Lotto> {
        val manualLottoMachine = ManualLottoMachine()
        val manualTicketsNumbersInput =
            if (payInfo.manualLottoQuantity > 0) inputView.readManualLottoNumbers(payInfo) else null
        val manualTicketsNumbers =
            manualTicketsNumbersInput?.map { singleLottoInput ->
                singleLottoInput.map { LottoNumber(it) }
            } ?: emptyList()
        return manualLottoMachine.generateLottoBundle(payInfo, manualTicketsNumbers)
    }

    private fun getAutoLottoTickets(payInfo: LottoPayInfo): List<Lotto> {
        val autoLottoMachine = AutoLottoMachine()
        return autoLottoMachine.generateLottoBundle(payInfo)
    }

    private fun getWinningLotto(): WinningLotto {
        val winningLottoNumbersWithoutBonusInput = inputView.readWinningLottoNumbersWithoutBonus()
        val winningLottoNumbersWithoutBonus = winningLottoNumbersWithoutBonusInput.map { LottoNumber(it) }
        val winningLottoWithoutBonus = Lotto.createSelfByManualLottoNumbers(winningLottoNumbersWithoutBonus)
        val bonusNumberText = inputView.readBonusNumber()
        val bonusNumber = LottoNumber(bonusNumberText)

        return WinningLotto(winningLottoWithoutBonus, bonusNumber)
    }
}
