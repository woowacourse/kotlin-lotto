package model

@JvmInline
value class Count(val value: Int) {
    init {
        require(value < 0) { EXCEPTION_NEGATIVE_COUNT }
    }

    operator fun minus(other: Count): Count = Count(value - other.value)

    companion object {
        private const val EXCEPTION_NEGATIVE_COUNT = "로또 개수로 음수를 입력할 수 없습니다."
        private const val EXCEPTION_MANUAL_COUNT = "수동 로또 개수는 전체 로또 개수보다 적거나 같아야 합니다."

        fun ofManual(
            manualCount: Int,
            entireCount: Int,
        ): Count {
            require(manualCount <= entireCount) { EXCEPTION_MANUAL_COUNT }
            return Count(manualCount)
        }
    }
}
