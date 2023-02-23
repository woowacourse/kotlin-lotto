package controller

import view.OutputView

object InputValueConverter {

    inline fun <reified T, E> convert(getValue: () -> T?, change: (T) -> E): E {
        while (true) {
            runCatching { return change(getNotNullValue(getValue)) }
                .onFailure { OutputView.printErrorMessage(it) }
        }
    }

    inline fun <reified T> getNotNullValue(getValue: () -> T?): T {
        var value = getValue()
        while (value == null) {
            OutputView.printMessage("입력한 값이 ${T::class}타입이 아닙니다.")
            value = getValue()
        }
        return value
    }
}
