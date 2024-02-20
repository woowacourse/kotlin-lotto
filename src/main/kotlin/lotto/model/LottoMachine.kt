package lotto.model

object LottoMachine {
    private const val TICKET_PRICE = 1_000
    
    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE
}
