package model

class LottoPurchase(
    purchasePrice: Int,
    private val makeMethod: LottoTicketGenerator,
) {
    init {
        require(purchasePrice >= 0)
    }

    val lottoCount = purchasePrice / PRICE_OF_LOTTO_TICKET

    fun makeUserTickets(): List<LottoTicket> {
        val userTickets: MutableList<LottoTicket> = mutableListOf()
        repeat(lottoCount) {
            userTickets.add(makeMethod.generate())
        }
        return userTickets
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
