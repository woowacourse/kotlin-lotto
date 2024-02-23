package model

class LottoPurchase(
    private val purchasePrice: Int,
    private val buildMethod: LottoTicketBuildMethod,
) {
    constructor(
        purchasePrice: Int,
    ) : this(
        purchasePrice,
        LottoTicketBuildMethod {
            LottoTicket((1..45).shuffled().take(6))
        },
    )

    fun makeLottoCount(): Int {
        return purchasePrice / PRICE_OF_LOTTO_TICKET
    }

    fun makeUserTickets(): List<LottoTicket> {
        val userTickets: MutableList<LottoTicket> = mutableListOf()
        repeat(makeLottoCount()) {
            userTickets.add(buildMethod.build())
        }
        return userTickets
    }

    companion object {
        const val PRICE_OF_LOTTO_TICKET = 1000
    }
}
