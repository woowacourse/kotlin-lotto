package lotto.domain

class LottoNumber(val number: Int) : Comparable<LottoNumber> {
    companion object {
        const val LOTTO_FIRST_NUMBER = 1
        const val LOTTO_LAST_NUMBER = 45

        private val lottoNumbers: List<LottoNumber> = (LOTTO_FIRST_NUMBER..LOTTO_LAST_NUMBER).map { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            return lottoNumbers[number - 1]
        }
    }

    override fun compareTo(other: LottoNumber): Int {
        return this.number - other.number
    }
}
