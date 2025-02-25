package lotto.util

fun <T> retryWhenException(
    action: () -> T,
    onError: (String?) -> Unit,
): T {
    while (true) {
        runCatching {
            return action()
        }.onFailure { e ->
            onError(e.message)
        }
    }
}
