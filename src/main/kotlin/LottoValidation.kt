import domain.Lotto
import domain.LottoNumber

class LottoValidation {
    fun checkLottoValidation(input: String?): Result<Lotto> {
        if (input.isNullOrBlank()) return Result.failure(IllegalArgumentException(INPUT_IS_EMPTY_ERROR_MESSAGE))
        val numbers = splitLottoNumbers(input)
        if (numbers.size != LOTTO_NUMBERS_SIZE) return Result.failure(
            IllegalArgumentException(LOTTO_NUMBERS_COUNT_ERROR)
        )

        val lottoNumbers = numbers.map { input ->
            val lottoNumber = input.toIntOrNull() ?: return Result.failure(IllegalArgumentException(NOT_INTEGER_ERROR))
            if (lottoNumber > MAXIMUM_LOTTO_NUMBER || lottoNumber < MINIMUM_LOTTO_NUMBER) return Result.failure(
                IllegalArgumentException(LOTTO_NUMBER_RANGE_ERROR_MESSAGE)
            )
            LottoNumber(lottoNumber)
        }

        return Result.success(Lotto(lottoNumbers))
    }

    fun splitLottoNumbers(input: String): List<String> {
        return input.split(SPLIT_CHARACTER)
    }

    companion object {
        const val NOT_INTEGER_ERROR = "정수만 입력해 주세요!!"
        const val LOTTO_NUMBERS_COUNT_ERROR = "로또의 번호가 6개가 아닙니다."
        const val LOTTO_NUMBERS_SIZE = 6
        const val INPUT_IS_EMPTY_ERROR_MESSAGE = "아무것도 입력하지 않았습니다."
        const val SPLIT_CHARACTER = ","
        const val MAXIMUM_LOTTO_NUMBER = 45
        const val MINIMUM_LOTTO_NUMBER = 1
        const val LOTTO_NUMBER_RANGE_ERROR_MESSAGE = "로또 번호는 1부터 45까지만 허용됩니다."
    }
}
