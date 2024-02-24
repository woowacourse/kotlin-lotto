package model

class LottoPurchase(
    purchasePrice: Int,
    private val makeMethod: LottoTicketGenerator,
) {
    init {
        require(purchasePrice >= 0)
    }

    val lottoCount = purchasePrice / PRICE_OF_LOTTO_TICKET
    constructor(
        purchasePrice: Int,
    ) : this(
        purchasePrice,
        LottoTicketGenerator {
            val lottoNumbers =
                LottoNumber.RANGE.shuffled()
                    .take(LottoTicket.SIZE)
                    .map { LottoNumber(it) }
            LottoTicket(lottoNumbers)
        },
    )

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
