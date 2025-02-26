package lotto.model

interface LottoMachine {
    fun publishLottoTickets(lottoPurchaseInfo: LottoPurchaseInfo): List<Lotto>
}
