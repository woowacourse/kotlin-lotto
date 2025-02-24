package lotto.model

class LottoMachine {
    fun purchase(count: Int): List<LottoTicket> = List(count) { LottoTicket() }
}
