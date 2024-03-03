package view

import model.LottoResult
import model.LottoTicket

interface OutputView {
    fun printLottoCount(
        manualCount: Int,
        autoCount: Int,
    )

    fun printLottoTickets(lottoTickets: List<LottoTicket>)

    fun printWinningChart(lottoResult: LottoResult)

    fun printWinningRate(winningRate: Float)
}
