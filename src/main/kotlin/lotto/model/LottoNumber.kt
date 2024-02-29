package lotto.model

@JvmInline
value class LottoNumber(val number: Int) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER)
    }

    companion object {
        private val lottoNumbers: MutableMap<Int, LottoNumber> = mutableMapOf()

        fun makeLottoNumber(realNum: Int): LottoNumber {
            if (!lottoNumbers.contains(realNum)) {
                lottoNumbers[realNum] = LottoNumber(realNum)
            }
            return lottoNumbers[realNum] ?: throw IllegalArgumentException("로또 번호가 잘못됨")
        }

        const val MIN_LOTTO_NUMBER = 1
        const val MAX_LOTTO_NUMBER = 45
    }
}
