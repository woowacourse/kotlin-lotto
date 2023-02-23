package view.validator

object Validator {
    fun validateConvertInt(input: String): Int? {
        val number = input.toIntOrNull()
        if (number == null)
            println(ERROR_CONVERT_INT)
        return number
    }

    private const val ERROR_CONVERT_INT = "[ERROR] 숫자로 다시 입력해주세요."
}
