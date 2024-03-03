package lotto.model.manual

import lotto.model.user.UserException

class ManualLottoCount(
    private val count: Int
) {

    fun getNumber(): Int {
        return count
    }

    companion object {
        fun checkManualLottoCount(
            input: Int,
            lottoCount: Int
        ): ManualEvent {
            return runCatching {
                if (input !in 0..lottoCount) throw ManualEvent.checkManual(ManualEvent.InvalidManualCount)
                ManualEvent.Success(ManualLottoCount(input))
            }.getOrElse { exception ->
                when (exception) {
                    is UserException.ManualException -> exception.manualEvent
                    else -> ManualEvent.UnknownError
                }

            }
        }
    }
}
