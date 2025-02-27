package lotto.model

class LottoMachine (
    private val manualLottoCount : LottoTicketCount,
    private val autoLottoCount : LottoTicketCount,
    private val manualLottoNumbers: List<List<Int>>,
) {
    init {
        require(manualLottoCount.toInt() == manualLottoNumbers.size) { LOTTO_COUNT_NOT_MATCH_LOTTO_NUMBERS }
    }

    fun issueLottoTickets(): List<LottoTicket> = purchaseManualLottoTicket() + purchaseAutoLottoTickets()

    private fun purchaseAutoLottoTickets(): List<LottoTicket> = List(autoLottoCount.toInt()) { AutoLottoGenerator().generateLotto() }

    private fun purchaseManualLottoTicket(): List<LottoTicket> = manualLottoNumbers.map { numbers -> ManualLottoGenerator(numbers).generateLotto() }

    companion object {
        private const val LOTTO_COUNT_NOT_MATCH_LOTTO_NUMBERS = "수동 구매 개수와 수동 번호 입력 개수가 일치하지 않습니다."
    }
}
