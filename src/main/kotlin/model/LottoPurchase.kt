package model

class LottoPurchase(
    val purchasePrice: Int
) {
    fun makeLottoCount(): Int {
        return purchasePrice / PRICE_OF_LOTTO_TICKET
    }

    private fun makeUserTicket(): LottoTicket {
        return LottoTicket((1..45).shuffled().take(6))
    }

    fun makeUserTickets(): List<LottoTicket> {
        val userTickets: MutableList<LottoTicket> = mutableListOf()
        repeat(makeLottoCount()) {
            userTickets.add(makeUserTicket())
        }
        return userTickets
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
