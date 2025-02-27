package lotto.model

class LottoTicketCount(
    private val count : Int,
) {
    init {
        require(count >= 0) { LOTTO_TICKET_COUNT_NOT_NEGATIVE }
    }

    fun toInt() = count

    companion object {
        private const val LOTTO_TICKET_COUNT_NOT_NEGATIVE = "로또 구매 장 수는 음수가 될수 없습니다."
    }
}