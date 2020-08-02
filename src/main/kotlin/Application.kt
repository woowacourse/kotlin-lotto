import lotto.domain.*
import view.InputView
import view.OutputView

fun main() {
    val purchaseAmount = PurchaseAmount(InputView.receivePurchaseAmount())
    val lottoFactory = LottoFactory(RandomStrategy())

    val lottoTickets: LottoTickets = lottoFactory.publishLottoTickets(purchaseAmount)
    OutputView.printLottoTickets(lottoTickets.lottoTickets)

    val winningLottoTicket = LottoTicket(InputView.receiveWinningLottoNumbers().map { LottoNumber.from(it) })
    val winningLotto = WinningLotto(winningLottoTicket)
    val lottoResult = LottoResult.of(lottoTickets, winningLotto)
    OutputView.printLottoResult(lottoResult.result, lottoResult.calculateProfit(purchaseAmount))
}
