package lotto.misc

fun <T> tryAndRerun(target: () -> T): T {
    return runCatching(target).onSuccess {
        return it
    }.getOrElse {
        println("잘못된 입력 : " + it.message)
        return tryAndRerun(target)
    }
}
