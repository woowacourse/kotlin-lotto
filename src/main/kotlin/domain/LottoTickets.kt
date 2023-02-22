package domain

class LottoTickets(private val lottoTickets: List<Lotto> = emptyList()) {
    val size: Int
        get() = lottoTickets.size

    init {
        require(lottoTickets.size in MINIMUM_COUNT..MAXIMUM_COUNT) { ERROR_INVALID_COUNT.format(lottoTickets.size) }
    }

    constructor(vararg lottoTickets: Lotto) : this(lottoTickets.toList())

    fun <R> map(transform: (Lotto) -> R): List<R> = lottoTickets.map(transform)

    fun forEach(transform: (Lotto) -> Unit) = lottoTickets.forEach(transform)

    fun asSequence(): Sequence<Lotto> = lottoTickets.asSequence()

    operator fun plus(lotto: LottoTickets): LottoTickets = LottoTickets(lottoTickets + lotto.lottoTickets)

    operator fun get(index: Int): Lotto = lottoTickets[index]

    companion object {
        private const val MINIMUM_COUNT = 0
        private const val MAXIMUM_COUNT = 100
        private const val ERROR_INVALID_COUNT = "로또 티켓은 0장 이상 100장 이하이어야 합니다.\n잘못된 값: %d"
    }
}
