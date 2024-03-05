package lotto.model

class Lotto(val numbers: Set<LottoNumber>) {
    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber(it) }.toSet())

    init {
        require(numbers.size == LOTTO_SIZE) {
            INVALID_LOTTO_SIZE
        }
    }

    companion object {
        const val LOTTO_SIZE = 6
        private const val INVALID_LOTTO_SIZE =
            ("로또 번호의 개수가 ${LOTTO_SIZE}가 아니면 예외처리한다.")
    }
}
