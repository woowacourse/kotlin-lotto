package lotto.model

class LottoMachine(
    private val money: Money,
    val manual: Int
) {
    private val auto by lazy { money.amount / 1000 - manual }
    private val _lottoes: MutableList<Lotto> = mutableListOf()
    val lottoes: List<Lotto>
        get() = _lottoes

    init {
        require(money >= Money(LOTTO_PRICE)) { "구입금액은 ${money.amount}보다 큰 ${LOTTO_PRICE}원 이상이어야 합니다." }
        require(money.amount - manual * LOTTO_PRICE >= 0) { "수동 로또 ${manual}개를 ${money.amount}원으로 살 수 없습니다." }
    }

    fun makeManualLotto(lottoNumbers: Lotto) {
        _lottoes.add(lottoNumbers)
    }

    fun makeRandomLotto() {
        repeat(auto.toInt()) {
            val randomNumbers = (1..45).shuffled().take(6).sorted()
            _lottoes.add(Lotto(randomNumbers))
        }
    }

    companion object {
        private const val LOTTO_PRICE = 1000L
    }
}
