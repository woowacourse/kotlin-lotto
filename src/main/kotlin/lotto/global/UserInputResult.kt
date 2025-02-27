package lotto.global

import kotlin.system.exitProcess

sealed class UserInputResult<T> {
    data class Success<T>(
        val value: T,
    ) : UserInputResult<T>()

    data class Failure<T>(
        val errorMessage: Message,
    ) : UserInputResult<T>()

    fun get(onFailure: (Message) -> Unit): T {
        when (this) {
            is Failure -> {
                onFailure(this.errorMessage)
                exitProcess(0)
            }
            is Success -> {
                return this.value
            }
        }
    }
}
