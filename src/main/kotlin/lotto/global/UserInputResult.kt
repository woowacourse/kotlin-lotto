package lotto.global

import java.lang.IllegalStateException

sealed class UserInputResult<T> {
    data class Success<T>(
        val value: T,
    ) : UserInputResult<T>()

    data class Failure<T>(
        val errorLottoException: LottoException,
    ) : UserInputResult<T>()

    fun get(): T {
        if (this is Success) return this.value
        throw IllegalStateException()
    }
}
