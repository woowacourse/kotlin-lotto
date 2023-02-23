package controller

import view.OutputView

object TypeConverter {

    inline fun <reified T, E> convert(getSource: () -> T?, export: (T) -> E): E {
        while (true) {
            runCatching { return export(getNotNullSource(getSource)) }
                .onFailure { OutputView.printErrorMessage(it) }
        }
    }

    inline fun <reified T> getNotNullSource(getSource: () -> T?): T {
        var source = getSource()
        while (source == null) {
            OutputView.printMessage("입력한 값이 ${T::class}타입이 아닙니다.")
            source = getSource()
        }
        return source
    }
}
