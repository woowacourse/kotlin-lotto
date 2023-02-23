import domain.Count
import domain.LottoCount

class ManualLottoCountValidation {
    fun checkManualLottoCountValidation(input: String?, totalCount: Count): Result<LottoCount> {
        if (input == null) return Result.failure(IllegalStateException(NULL_ERROR))
        val manualCount = Count(input.toIntOrNull() ?: return Result.failure(IllegalStateException(NOT_INTEGER_ERROR)))
        if (manualCount.value > totalCount.value) return Result.failure(
            IllegalStateException(
                BIGGER_THAN_TOTAL_COUNT_ERROR
            )
        )
        return Result.success(LottoCount(manualCount, totalCount))
    }

    companion object {
        private const val BIGGER_THAN_TOTAL_COUNT_ERROR = "수동 로또 수가 총 로또 수보다 큽니다!! 더 작은 수를 입력하세요!!"
        private const val NOT_INTEGER_ERROR = "정수만 입력해주세요!!"
        private const val NULL_ERROR = "아무것도 입력하지 않았습니다!!"
    }
}
