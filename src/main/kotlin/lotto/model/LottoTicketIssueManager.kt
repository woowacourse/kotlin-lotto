package lotto.model

// 수동이랑 자동 로또 계산해주는 매니저, 로또 티켓까지 주는 역할 담당
class LottoTicketIssueManager (
    customerWantBuyLottoTicketCount: Int,
    customerWantToBuyManualLottoTicketCount: Int,
    manualLottoNumbers: List<List<Int>>,
) {
    private val customerWantBuyLottoTicketCount = LottoTicketCount(customerWantBuyLottoTicketCount)
    private val customerWantToBuyManualLottoTicketCount = LottoTicketCount(customerWantToBuyManualLottoTicketCount)
    private val customerWantToBuyAutoLottoTicketCount = LottoTicketCount(customerWantBuyLottoTicketCount - customerWantToBuyManualLottoTicketCount)

    // 발행가능한 개수보다 수동으로 로또 발행받고 싶은 개수가 크면 안된다.
    init {
        require(customerWantBuyLottoTicketCount >= customerWantToBuyManualLottoTicketCount) { "전체 발행 가능한 로또 개수보다 수동로또 발행 개수가 더 많습니다." }
        require(customerWantToBuyManualLottoTicketCount == manualLottoNumbers.size) { LOTTO_COUNT_NOT_MATCH_LOTTO_NUMBERS }
    }

    // 로또를 발행해서 준다.
    fun getLottoTickets(manualLottoNumbers: List<List<Int>>): List<LottoTicket> = LottoMachine().issueLottoTickets(customerWantToBuyAutoLottoTicketCount, manualLottoNumbers)


    companion object {
        private const val LOTTO_COUNT_NOT_MATCH_LOTTO_NUMBERS = "수동 구매 개수와 수동 번호 입력 개수가 일치하지 않습니다."
    }
}