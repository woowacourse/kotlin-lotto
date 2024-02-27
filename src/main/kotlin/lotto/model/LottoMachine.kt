package lotto.model

import lotto.util.NumberGenerate

object LottoMachine {
    const val TICKET_PRICE = 1_000
    private val randomNumberGenerate =
        NumberGenerate { LottoNumber.NUMBER_RANGE.shuffled().take(Lotto.NUMBER_COUNT).sorted() }

    fun getNumberOfTicket(cash: Int): Int = cash / TICKET_PRICE

    fun getNumberOfManual(
        manualCounts: Int,
        numberOfTickets: Int,
    ) {
        require(
            manualCounts <= numberOfTickets,
        ) { "수동으로 발행할 로또 개수는 구매한 개수를 넘길 수 없습니다." }
    }

    fun issueTickets(
        count: Int,
        numberGenerate: NumberGenerate = randomNumberGenerate,
    ): List<Lotto> {
        val tickets = mutableListOf<Lotto>()
        repeat(count) { idx ->
            tickets.add(
                Lotto(numberGenerate.get(idx).map { LottoNumber(it) }),
            )
        }
        return tickets
    }
}
