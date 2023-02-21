package view

class InputView {
    fun inputInt(): Int {
        return readln().toInt()
    }

    fun inputString(): String {
        return readln().replace("\\s".toRegex(), "")
    }
}
