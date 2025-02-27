package lotto.model

class LottoTicketCountManager(
    private val counts: LottoTicketCount,
    val manualLottoCount: LottoTicketCount
) {
    init {
        require(counts.toInt() >= manualLottoCount.toInt()) { "전체 발행 가능한 로또 개수보다 수동로또 발행 개수가 더 많습니다." }
    }

    // 자동 로또랑 수동 로또 몇개 발행하겠습니가?
    val LottoTicketCount = counts.toInt() - manualLottoCount.toInt()
}