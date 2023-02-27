package lotto.domain

fun interface LotteryTicketsMachine {
    fun generate(): List<Lottery>
}
