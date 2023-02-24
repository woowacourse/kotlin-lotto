package view

object InputResponseView {
    private const val SEPARATOR = ", "
    const val PREFIX = "[ERROR]"

    fun inputMoney(): Long? {
        return readLine()?.toLongOrNull() // ?: throw IllegalArgumentException("$PREFIX 1000원 단위의 금액으로 입력해야합니다.")
    }

    fun inputLottoNumbers(): List<Int>? {
        return (readLine() ?: return null) // throw IllegalArgumentException("$PREFIX null값이 입력되었습니다."))
            .split(SEPARATOR)
            .map { it.toIntOrNull() ?: return null } // throw IllegalArgumentException("$PREFIX 숫자를 입력해야합니다.") }
    }

    fun inputBonusNumber(): Int? {
        return readLine()?.toIntOrNull() // ?: throw IllegalArgumentException("$PREFIX 당첨번호와 겹치지않고 1 ~ 45까지의 숫자를 입력해주세요.")
    }

    fun inputManualLottoCount(): Int? {
        return readLine()?.toIntOrNull() // ?: throw IllegalArgumentException("$PREFIX 정수를 입력해야합니다.")
    }

    fun inputManualLottoBundle(inputLottoCount: Int): List<List<Int>>? {
        val manualLottoBundle = mutableListOf<List<Int>>()
        repeat(inputLottoCount) {
            manualLottoBundle.add(inputLottoNumbers() ?: return null)
        }
        return manualLottoBundle
    }
}
