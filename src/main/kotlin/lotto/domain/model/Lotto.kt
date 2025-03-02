package lotto.domain.model

sealed class LottoResult {
    fun getSuccessOrThrow(): Lotto {
        require(this is Success) { "${this::class.simpleName} 문제가 발생 했습니다." }
        return lotto
    }

    data class Success(val lotto: Lotto) : LottoResult()

    data object InvalidNumbersNull : LottoResult()

    data object InvalidNumberNull : LottoResult()

    data class InvalidNumberRange(val lottoNumber: Int) : LottoResult()

    data class InvalidNumbersSize(val lottoNumbers: Set<LottoNumber>) : LottoResult()
}

data class Lotto private constructor(private val _numbers: Set<LottoNumber>) {
    val numbers get() = _numbers.map { it.number }

    fun getSameNumberCount(other: Lotto) = _numbers.count { number -> other._numbers.contains(number) }

    fun hasLottoNumber(number: LottoNumber) = _numbers.contains(number)

    override fun toString(): String {
        return this._numbers.toString()
    }

    companion object {
        fun from(numbers: List<Int>?): LottoResult {
            numbers ?: return LottoResult.InvalidNumbersNull
            val lottoNumbers =
                numbers.map {
                    when (val lottoNumberResult = LottoNumber.from(it)) {
                        LottoNumberResult.InvalidNumberNull -> return LottoResult.InvalidNumberNull
                        is LottoNumberResult.InvalidNumberRange -> return LottoResult.InvalidNumberRange(
                            lottoNumberResult.number,
                        )

                        is LottoNumberResult.Success -> lottoNumberResult.lottoNumber
                    }
                }.toSortedSet()
            if (lottoNumbers.size != LOTTO_NUMBER_SIZE) return LottoResult.InvalidNumbersSize(lottoNumbers)
            return LottoResult.Success(Lotto(lottoNumbers))
        }

        const val LOTTO_PRICE = 1000
        const val LOTTO_NUMBER_SIZE = 6
    }
}
