package domain

@JvmInline
value class LottoNumber private constructor(val number: Int) {

    companion object {
        private const val LOTTO_NUMBER_MINIMUM = 1
        private const val LOTTO_NUMBER_MAXIMUM = 45
        private val LOTTO_NUMBER_RANGE = LOTTO_NUMBER_MINIMUM..LOTTO_NUMBER_MAXIMUM
        private val cashedLottoNumbers: Map<Int, LottoNumber> = LOTTO_NUMBER_RANGE.associateWith { LottoNumber(it) }

        fun of(number: Int): LottoNumber {
            return cashedLottoNumbers[number] ?: throw IllegalArgumentException("[Error] 로또 넘버는 1~45여야합니다.")
        }

        fun of(number: String): LottoNumber {
            val convertedNumber = number.toIntOrNull() ?: throw NumberFormatException("[Error] 숫자로 입력해주세요.")
            return cashedLottoNumbers[convertedNumber] ?: throw IllegalArgumentException("[Error] 로또 넘버는 1~45여야합니다.")
        }
    }
}
