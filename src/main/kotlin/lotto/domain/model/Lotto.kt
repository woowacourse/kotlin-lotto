package lotto.domain.model

data class Lotto(private val _numbers: Set<LottoNumber>) {
    val numbers get() = _numbers.map { it.number }

    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(_numbers.size == LOTTO_NUMBER_SIZE) {
            INVALID_LOTTO_NUMBER_SIZE_MESSAGE.format(this)
        }
    }

    fun getMatchCount(winningLotto: Lotto) = _numbers.count { number -> winningLotto._numbers.contains(number) }

    fun hasLottoNumber(number: LottoNumber) = _numbers.contains(number)

    override fun toString(): String {
        return this._numbers.toString()
    }

    companion object {
        const val LOTTO_PRICE = 1000
        const val LOTTO_NUMBER_SIZE = 6
        private const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "%s 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다."
    }
}
