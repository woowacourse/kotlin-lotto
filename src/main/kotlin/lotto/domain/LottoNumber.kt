package lotto.domain

const val LOTTO_FIRST_NUMBER = 1
const val LOTTO_LAST_NUMBER = 45

class LottoNumber(val number: Int) : Comparable<LottoNumber> {
    companion object {
        private val lottoNumbers: List<LottoNumber> = (LOTTO_FIRST_NUMBER..LOTTO_LAST_NUMBER).map { LottoNumber(it) }

        fun from(number: Int): LottoNumber {
            return lottoNumbers[number - 1]
        }
    }

    override fun compareTo(other: LottoNumber): Int {
        return this.number - other.number
    }
}
