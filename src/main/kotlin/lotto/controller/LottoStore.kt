package lotto.controller

import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoTicket
import lotto.domain.model.LottoTicketResult
import lotto.domain.model.PurchaseAmount
import lotto.domain.model.PurchaseCount
import lotto.domain.model.WinningLotto
import lotto.domain.service.LottoMachine
import lotto.domain.service.LottoResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoStore(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    fun run() {
        val (manualCount, autoCount) = getLottoCount()
        val lottoTickets = generateLottoTickets(manualCount, autoCount)
        outputView.printLotto(lottoTickets)

        val winningLotto = getWinningLotto()

        val result = calculateResult(lottoTickets, winningLotto)
        outputView.printResult(result.ranks)
        outputView.printProfit(result.calculateProfit())
    }

    private fun getLottoCount(): Pair<Int, Int> {
        val amount = PurchaseAmount(inputView.inputPurchaseAmount())
        val totalCount = amount.calculatePurchaseLottoCount()
        val manualCount = inputView.inputManualCount()
        val autoCount = PurchaseCount(totalCount, manualCount).calculateAutoCount()
        return Pair(manualCount, autoCount)
    }

    private fun generateLottoTickets(
        manualCount: Int,
        autoCount: Int,
    ): List<LottoTicket> {
        if (manualCount != 0) outputView.printManualNumbersGuide()
        val manualLottoTickets = generateManualLottoTicket(manualCount)
        val autoLottoTickets = generateAutoLottoTicket(autoCount)
        outputView.printPurchaseCount(manualCount, autoCount)
        return manualLottoTickets + autoLottoTickets
    }

    private fun generateManualLottoTicket(count: Int): List<LottoTicket> {
        val manualLottoTickets = mutableListOf<LottoTicket>()

        repeat(count) {
            while (true) {
                val result = LottoTicket.create(inputView.inputManualNumbers().map { LottoNumber(it) })
                when (result) {
                    is LottoTicketResult.Success -> {
                        manualLottoTickets.add(result.ticket)
                        break
                    }

                    is LottoTicketResult.InvalidCount -> println(ERROR_LOTTO_INVALID_COUNT)
                    is LottoTicketResult.DuplicateNumbers -> println(ERROR_LOTTO_DUPLICATE)
                }
            }
        }
        return manualLottoTickets.toList()
    }

    private fun generateAutoLottoTicket(count: Int): List<LottoTicket> {
        val autoLottoTickets = LottoMachine().generateAutoTicket(count)
        return autoLottoTickets
    }

    private fun getWinningLotto(): WinningLotto {
        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumber = LottoNumber(inputView.inputBonusNumber())
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        return winningLotto
    }

    private fun calculateResult(
        lottoTickets: List<LottoTicket>,
        winningLotto: WinningLotto,
    ): LottoResult = LottoResult.calculateResult(lottoTickets, winningLotto)

    companion object {
        private const val ERROR_LOTTO_INVALID_COUNT = "로또 번호는 6개여야 합니다. 다시 입력해주세요."
        private const val ERROR_LOTTO_DUPLICATE = "로또 번호는 서로 중복되면 안 됩니다. 다시 입력해주세요."
    }
}
