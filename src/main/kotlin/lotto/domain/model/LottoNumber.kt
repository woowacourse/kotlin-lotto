package lotto.domain.model

sealed class LottoNumberResult {
    data class Success(val lottoNumber: LottoNumber) : LottoNumberResult()

    data class InvalidNumberRange(val number: Int) : LottoNumberResult()

    data object InvalidNumberNull : LottoNumberResult()
}

data class LottoNumber private constructor(val number: Int) : Comparable<LottoNumber> {
    override fun compareTo(other: LottoNumber): Int {
        return this.number - other.number
    }

    override fun toString(): String {
        return this.number.toString()
    }

    companion object {
        fun from(number: Int?): LottoNumberResult {
            number ?: return LottoNumberResult.InvalidNumberNull
            if (number !in LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER) return LottoNumberResult.InvalidNumberRange(number)
            return LottoNumberResult.Success(LottoNumber(number))
        }

        const val LOTTO_MIN_NUMBER = 1
        const val LOTTO_MAX_NUMBER = 45
    }
}
