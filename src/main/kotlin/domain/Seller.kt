package domain

class Seller(private val money: Int) {


    fun getTicketCount(): Int = money / ONE_TICKET_PRICE
    fun getTicket(): List<Lotto> {
        val lotteries: MutableList<Lotto> = mutableListOf()
        repeat(getTicketCount()) {
            lotteries.add(Lotto())
        }
        return lotteries
    }

    companion object {
        private const val ONE_TICKET_PRICE = 1000
    }

}
