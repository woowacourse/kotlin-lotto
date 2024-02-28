package controller

import model.*
import view.InputView
import view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val purchasePrice = inputView.getPurchasePrice()
        val lottoCount = getLottoCount(purchasePrice)
        val lottoTickets = makeRandomLottoTicket(lottoCount)
        val lottoWinning = makeLottoWinning()
        val lottoResult = lottoWinning.makeLottoResult(lottoTickets)
        val profitRate = lottoResult.winningMoney.toFloat() / purchasePrice
        printWinningResult(lottoResult, profitRate)
    }

    private fun getLottoCount(
        purchasePrice: Int,
    ): Int {
        val lottoPurchase = LottoPurchase(1000)
        val lottoCount = lottoPurchase.calculateLottoCount(purchasePrice)
        outputView.printLottoCount(lottoCount)
        return lottoCount
    }

    private fun makeRandomLottoTicket(count: Int): List<LottoTicket> {
        val lottoFactory = LottoFactory(RandomLottoTicketGenerator)
        val lottoTickets = lottoFactory.makeLottoTickets(count)
        outputView.printLottoTickets(lottoTickets)
        return lottoTickets
    }

    private fun makeLottoWinning(): LottoWinning {
        val winningTicket = LottoTicket(inputView.getWinningTicket().map { LottoNumber.of(it) })
        val bonusNumber = LottoNumber.of(inputView.getBonusNumber())
        return LottoWinning(winningTicket, bonusNumber)
    }

    private fun printWinningResult(
        lottoResult: LottoResult,
        profitRate: Float,
    ) {
        outputView.printWinningChart(lottoResult)
        outputView.printWinningRate(profitRate)
    }
}
