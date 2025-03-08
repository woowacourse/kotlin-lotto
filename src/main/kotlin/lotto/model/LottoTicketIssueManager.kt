package lotto.model

class LottoTicketIssueManager(
    totalCount: Int,
    manualCount: Int,
    manualLottoNumbers: List<List<Int>>,
) {
    private val autoLottoTicketCount =
        LottoTicketCount(totalCount - manualCount)

    init {
        require(totalCount >= manualCount) { EXCEEDED_MANUAL_LOTTO_COUNT }
        require(manualCount == manualLottoNumbers.size) { LOTTO_COUNT_NOT_MATCH_LOTTO_NUMBERS }
    }

    fun getLottoTickets(manualLottoNumbers: List<List<Int>>): List<LottoTicket> =
        LottoMachine().issueLottoTickets(autoLottoTicketCount, manualLottoNumbers)

    fun getAutoLottoTicketCount(): Int = autoLottoTicketCount.toInt()

    companion object {
        private const val LOTTO_COUNT_NOT_MATCH_LOTTO_NUMBERS = "수동 구매 개수와 수동 번호 입력 개수가 일치하지 않습니다."
        private const val EXCEEDED_MANUAL_LOTTO_COUNT = "전체 발행 가능한 로또 개수보다 수동로또 발행 개수가 더 많습니다."
    }
}
