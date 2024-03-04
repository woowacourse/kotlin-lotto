package lottogame.model

@JvmInline
value class BonusLottoNumber private constructor(private val lottoNumber: LottoNumber) : LottoNumber by lottoNumber {
    constructor(
        lottoNumber: LottoNumber,
        winningNumbers: List<LottoNumber>,
    ) : this(lottoNumber) {
        require(lottoNumber !in winningNumbers) { EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT.format(this) }
    }

    companion object {
        private const val EXCEPTION_DUPLICATE_BONUS_NUMBER_FORMAT = "%s : 보너스 번호는 우승 로또 번호와 중복될 수 없습니다."
    }
}
