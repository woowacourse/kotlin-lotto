package view

object InputView {

    fun readPurchaseAmount(): Int {
        return try {
            println("구입금액을 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("입력값은 정수여야 합니다.")
        }
    }

    fun readWinningNumbers(): List<Int> {
        return try {
            println("지난 주 당첨 번호를 입력해 주세요.")
            readln().split(",").map { it.trim().toInt() }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("입력값은 정수여야 합니다.")
        }
    }

    fun readBonusNumber(): Int {
        return try {
            println("보너스 볼을 입력해 주세요.")
            readln().toInt()
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("입력값은 정수여야 합니다.")
        }
    }
}
