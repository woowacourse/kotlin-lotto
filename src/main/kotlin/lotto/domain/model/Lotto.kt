package lotto.domain.model

data class Lotto(private val _numbers: Set<LottoNumber>) {
    constructor(numbers: List<Int>) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(_numbers.size == LOTTO_NUMBER_SIZE) { INVALID_LOTTO_NUMBER_SIZE_MESSAGE.format(numbers) }
    }

    val numbers get() = _numbers.map { it.number }

    fun getSameNumberCount(other: Lotto) = _numbers.count { number -> other._numbers.contains(number) }

    fun hasLottoNumber(number: LottoNumber) = _numbers.contains(number)

    override fun toString(): String {
        return this._numbers.toString()
    }

    companion object {
        private const val INVALID_LOTTO_NUMBER_SIZE_MESSAGE = "%s 중복을 제외한 로또 번호 입니다. 로또 번호는 6개여야 합니다."
        const val LOTTO_PRICE = 1000
        const val LOTTO_NUMBER_SIZE = 6
    }
}
