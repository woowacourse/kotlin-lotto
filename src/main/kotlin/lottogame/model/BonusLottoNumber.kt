package lottogame.model

@JvmInline
value class BonusLottoNumber private constructor(private val lottoNumber: LottoNumber) : LottoNumber by lottoNumber {
    companion object {
        private const val EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT = "보너스 번호는 우승 로또 번호와 중복될 수 없습니다."

        @JvmStatic
        fun of(
            lottoNumber: LottoNumber,
            winningNumbers: List<LottoNumber>,
        ): BonusLottoNumber {
            require(lottoNumber !in winningNumbers) { EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT }
            return BonusLottoNumber(lottoNumber)
        }
    }
}
