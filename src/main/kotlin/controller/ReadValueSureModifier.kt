package controller

import view.OutputView

object ReadValueSureModifier {

    fun <T, E> tryToReadValueAndModifyToTargetUntilNoErrorOccur(
        readValue: () -> T?,
        modifyToTarget: (T) -> E,
    ): E {
        while (true) {
            runCatching { return modifyToTarget(executeUntilGetCorrectType(readValue)) }
                .onFailure { OutputView.printErrorMessage(it) }
        }
    }

    private fun <T> executeUntilGetCorrectType(getValue: () -> T?): T {
        var value = getValue()
        while (value == null) {
            OutputView.printMessage("입력한 값이 올바른 타입이 아닙니다.")
            value = getValue()
        }
        return value
    }
}
